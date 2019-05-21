package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Materia;

public class MateriaDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idmateria", "nombre", "codigo_materia", "uvs"};

    public MateriaDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Materia materia) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", materia.getNombre());
        contentValues.put("codigo_materia", materia.getCodigoMateria());
        contentValues.put("uvs", materia.getUvs());



        db = dbHelper.getWritableDatabase();
        contador = db.insert("materia", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Materia consultar(String codigo_materia){
        String[] id  = {codigo_materia};
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("materia", campos, "codigo_materia=?", id, null, null, null);
        if (cursor.moveToFirst()){
            Materia materia = new Materia();
            materia.setIdMateria(cursor.getInt(0));
            materia.setCodigoMateria(cursor.getString(1));
            materia.setNombre(cursor.getString(2));
            materia.setUvs(cursor.getInt(3));

            dbHelper.close();
            return materia;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Materia materia){
        int contador = 0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(materia.getCodigoMateria())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", materia.getNombre());
        contentValues.put("codigo_materia", materia.getCodigoMateria());
        contentValues.put("uvs", materia.getUvs());
        contador = db.update("materia", contentValues, "codigo_materia=?", id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con codigo materia: " + materia.getCodigoMateria() + "no existe";
    }

    public String eliminar(Materia materia){
        String reg = "filas afectadas";
        int con = 0;

        try {
            db = dbHelper.getWritableDatabase();
            con += db.delete("materia", "codigo_materia='" + materia.getCodigoMateria()+ "'", null);
            reg += con;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return reg;
    }

    public List<Materia> getMaterias(){
        String[] camposTry = {"idmateria", "nombre"};

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("materia", camposTry, null, null, null, null, null);
        List<Materia> materias = new ArrayList<Materia>();
        if (cursor.moveToFirst()){
            do {
                Materia materia = new Materia();
                materia.setIdMateria(cursor.getInt(0));
                materia.setNombre(cursor.getString(1));
                materias.add(materia);
            } while (cursor.moveToNext());
        }
        dbHelper.close();
        return materias;
    }
}

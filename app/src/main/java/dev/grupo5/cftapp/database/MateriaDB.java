package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Materia;

public class MateriaDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idmateria", "nombre", "codigomateria", "uvs"};

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

    public Materia consultar(String idmateria){
        String[] id  = {idmateria};
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("materia", campos, "idmateria=?", id, null, null, null);
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

        String[] id = {String.valueOf(materia.getIdMateria())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", materia.getNombre());
        contentValues.put("codigomateria", materia.getCodigoMateria());
        contentValues.put("uvs", materia.getUvs());
        contador = db.update("materia", contentValues, "idmateria=?", id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con id local: " + materia.getIdMateria() + "no existe";
    }

    public String eliminar(Materia materia){
        String reg = "filas afectadas";
        int con = 0;

        try {
            db = dbHelper.getWritableDatabase();
            con += db.delete("materia", "codigomateria='" + materia.getCodigoMateria()+ "'", null);
            reg += con;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return reg;
    }
}

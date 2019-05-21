package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idmatciclo", "idciclo", "idmateria"};

    public MateriaCicloDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(MateriaCiclo materiaCiclo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idmateria", materiaCiclo.getIdMateria());
        contentValues.put("idciclo", materiaCiclo.getIdCiclo());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("materiaciclo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public MateriaCiclo consultar(String idmatciclo){
        db = dbHelper.getWritableDatabase();
        String[] id = {idmatciclo};

        Cursor cursor = db.query("materiaciclo", campos, "idmatciclo=?", id, null, null, null);
        if (cursor.moveToFirst()){
            MateriaCiclo materiaCiclo = new MateriaCiclo();
            materiaCiclo.setIdMatCiclo(cursor.getInt(0));
            materiaCiclo.setIdCiclo(cursor.getInt(1));
            materiaCiclo.setIdMateria(cursor.getInt(2));

            dbHelper.close();
            return materiaCiclo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(MateriaCiclo materiaCiclo) {
        int count;
        String[] id = {String.valueOf(materiaCiclo.getIdMatCiclo())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCiclo", materiaCiclo.getIdCiclo());
        contentValues.put("idMateria", materiaCiclo.getIdMateria());
        db = dbHelper.getWritableDatabase();
        count = db.update("MateriaCiclo", contentValues, "idmatciclo=?", id);
        dbHelper.close();
        if (count > 0)
                return "Regitro actualizado correctamente";
        else
            return "Registro con idMatCiclo" + materiaCiclo.getIdMatCiclo() + "no existe";
    }

    public String eliminar(MateriaCiclo materiaCiclo){
        String regafectadas = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("materiaciclo", "idmatciclo='" + materiaCiclo.getIdMatCiclo() + "'",null);
            regafectadas += contador;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regafectadas;
    }

    public HashMap<Integer,String> getMateriasCiclos(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("materiaciclo", campos, null, null, null, null, null);
        Cursor materia;

        HashMap<Integer,String> map = new HashMap<Integer, String>();
        String informacion;
        if (cursor.moveToFirst()){
            do {
                informacion = " ";
                informacion = cursor.getString(0);
                informacion += " - ";

                //grupo
                materia = db.query("materia", new String[]{"nombre"},"idmateria=?",
                        new String[]{String.valueOf(cursor.getInt(1))}, null, null, null);

                if (materia.moveToFirst()){
                    informacion += materia.getString(0);
                }
                informacion += " - ";

                //has
                map.put(cursor.getInt(3), informacion);
            } while (cursor.moveToNext());
        }

        dbHelper.close();
        return map;
    }
}

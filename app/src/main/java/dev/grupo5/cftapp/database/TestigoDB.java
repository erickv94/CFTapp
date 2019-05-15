package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.modelos.Testigo;

public class TestigoDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TestigoDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }
    private String [] campos={"idtramite","idestudiante","justificacion","idtestigo"};

    public String insertar(Testigo testigo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",testigo.getIdTramite());
        contentValues.put("idestudiante", testigo.getIdEstudiante());
        contentValues.put("justificacion", testigo.getJustificacion());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("testigo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Testigo consultar(String estudiante,String tramite){
        db = dbHelper.getWritableDatabase();
        String[] id = {estudiante,tramite};
        Cursor c = db.query("testigo", campos, "idestudiante=? and idtramite=?", id, null, null, null);
        if (c.moveToFirst()) {

            Testigo testigo = new Testigo();

            testigo.setIdTramite(c.getInt(0));
            testigo.setIdEstudiante(c.getInt(1));
            testigo.setJustificacion(c.getString(2));
            testigo.setIdTestigo(c.getInt(3));


            dbHelper.close();
            return testigo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Testigo testigo){


        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(testigo.getIdTestigo())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idtramite",testigo.getIdTramite());
        contentValues.put("idestudiante",testigo.getIdTramite());
        contentValues.put("justificacion",testigo.getJustificacion());


        contador = db.update("testigo", contentValues, "idtestigo=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con id : " + testigo.getIdTestigo() + " no existe";


    }




    public String eliminar(Testigo testigo){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("testigo", "idtestigo='" +testigo.getIdTestigo()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }




}

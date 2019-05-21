package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.EstadoEvaluacion;

public class EstadoEvaluacionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos={"idevaluacion","idestudiante","nota","idestado"};
    public EstadoEvaluacionDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(EstadoEvaluacion estadoEvaluacion) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idevaluacion",estadoEvaluacion.getIdEvaluacion());
        contentValues.put("idestudiante", estadoEvaluacion.getIdEstudiante());
        contentValues.put("nota", estadoEvaluacion.getNota());






        db = dbHelper.getWritableDatabase();
        contador = db.insert("estadoevaluacion", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public EstadoEvaluacion consultar(String estudiante,String evaluacion){
        db = dbHelper.getWritableDatabase();
        String[] id = {estudiante,evaluacion};
        Cursor c = db.query("estadoevaluacion", campos, "idestudiante=? and idevaluacion=?", id, null, null, null);
        if (c.moveToFirst()) {

            EstadoEvaluacion estadoEvaluacion = new EstadoEvaluacion();

           estadoEvaluacion.setIdEvaluacion(c.getInt(0));
           estadoEvaluacion.setIdEstudiante(c.getInt(1));
           estadoEvaluacion.setNota(c.getDouble(2));
           estadoEvaluacion.setIdEstado(c.getInt(3));

            dbHelper.close();
            return estadoEvaluacion;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(EstadoEvaluacion estadoEvaluacion){


        int contador=0;
        db = dbHelper.getWritableDatabase();

/*
        String[] id = {String.valueOf(testigo.getIdTestigo())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idtramite",testigo.getIdTramite());
        contentValues.put("idestudiante",testigo.getIdTramite());
        contentValues.put("justificacion",testigo.getJustificacion());


        contador = db.update("testigo", contentValues, "idtestigo=?", id);
        dbHelper.close();
*/

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con id : " + estadoEvaluacion.getIdEstado() + " no existe";


    }




    public String eliminar(EstadoEvaluacion estadoEvaluacion){


        String regAfectados = "filas afectadas";
        int contador = 0;

/*
        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("testigo", "idtestigo='" +testigo.getIdTestigo()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
*/
        return regAfectados;
    }





}

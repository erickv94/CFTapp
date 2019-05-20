package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleSolicitud;

public class DetalleSolicitudDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DetalleSolicitudDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    private String [] campos={"idtramite","idestudiante","motivo","esrechazado"};

    public String insertar(DetalleSolicitud detalleSolicitud) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",detalleSolicitud.getIdTramite());
        contentValues.put("idestudiante", detalleSolicitud.getIdEstudiante());
        contentValues.put("motivo", detalleSolicitud.getMotivo());
        contentValues.put("esrechazado", detalleSolicitud.getEsRechazado());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("detallesolicitud", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public DetalleSolicitud consultar(String estudiante,String tramite){
        db = dbHelper.getWritableDatabase();
        String[] id = {estudiante,tramite};
        Cursor c = db.query("detallesolicitud", campos, "idestudiante=? and idtramite=?", id, null, null, null);
        if (c.moveToFirst()) {

            DetalleSolicitud detalleSolicitud = new DetalleSolicitud();

            detalleSolicitud.setIdTramite(c.getInt(0));
            detalleSolicitud.setIdEstudiante(c.getInt(1));
            detalleSolicitud.setMotivo(c.getString(2));
            detalleSolicitud.setEsRechazado(Boolean.valueOf(c.getString(3)));


            dbHelper.close();
            return detalleSolicitud;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(DetalleSolicitud detalleSolicitud){


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
            return "Registro con id : " + detalleSolicitud.getMotivo() + " no existe";


    }




    public String eliminar(DetalleSolicitud detalleSolicitud){


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

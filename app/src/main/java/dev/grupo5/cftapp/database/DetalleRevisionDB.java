package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleRevision;

public class DetalleRevisionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DetalleRevisionDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }


    private String [] campos={"idtramite","idestudiante","motivo","asistencia"};

    public String insertar(DetalleRevision detalleRevision) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",detalleRevision.getIdTramite());
        contentValues.put("idestudiante", detalleRevision.getIdEstudiante());
        contentValues.put("resultado", detalleRevision.getResultado());
        contentValues.put("motivo", detalleRevision.getMotivo());
        contentValues.put("asistencia", detalleRevision.getAsistencia());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("detallerevision", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }



    public DetalleRevision consultar(String estudiante,String tramite){
        db = dbHelper.getWritableDatabase();
        String[] id = {estudiante,tramite};
        Cursor c = db.query("detallerevision", campos, "idestudiante=? and idtramite=?", id, null, null, null);
        if (c.moveToFirst()) {

            DetalleRevision detalleRevision = new DetalleRevision();

            detalleRevision.setIdTramite(c.getInt(0));
            detalleRevision.setIdEstudiante(c.getInt(1));
            detalleRevision.setMotivo(c.getString(2));
            detalleRevision.setAsistencia(c.getInt(3)==1?true:false);


            dbHelper.close();
            return detalleRevision;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(DetalleRevision detalleRevision){


        int contador=0;
        db = dbHelper.getWritableDatabase();


        String[] id = {String.valueOf(detalleRevision.getIdTramite()),String.valueOf(detalleRevision.getIdEstudiante())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("motivo",detalleRevision.getMotivo());
        contentValues.put("asistencia",detalleRevision.getAsistencia());


        contador = db.update("detallerevision", contentValues, "idtramite=? and idestudiante=?", id);
        dbHelper.close();


        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con id : " + detalleRevision.getMotivo() + " no existe";


    }




    public String eliminar(DetalleRevision detalleRevision){


        String regAfectados = "filas afectadas";
        int contador = 0;


        try {
            db = dbHelper.getWritableDatabase();

            contador += db.delete("detallesolicitud",
                    "idestudiante='" +detalleRevision.getIdEstudiante()+
                            "' and idtramite='"+detalleRevision.getIdTramite()+"'"
                    , null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }



}

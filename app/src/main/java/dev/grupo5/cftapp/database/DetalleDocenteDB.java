package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleDocente;

public class DetalleDocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos={"idrol","iddocente","idtramite","asistencia","motivoinasistencia"};
    public DetalleDocenteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(DetalleDocente detalleDocente) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idrol", detalleDocente.getIdRol());
        contentValues.put("iddocente", detalleDocente.getIdDocente());
        contentValues.put("idtramite", detalleDocente.getIdTramite());
        contentValues.put("asistencia", detalleDocente.getAsistencia());
        contentValues.put("motivoinasistencia", detalleDocente.getMotivoInasistencia());

        db = dbHelper.getWritableDatabase();
        contador = db.insertWithOnConflict("detalledocente", null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        regInsertados += contador;


        return regInsertados;
    }




    public DetalleDocente consultar(String docente,String tramite,String rol){
        db = dbHelper.getWritableDatabase();
        String[] id = {docente,tramite,rol};

        Cursor c = db.query("detalledocente", campos, "iddocente=? and idtramite=? and idrol=?", id, null, null, null);
        if (c.moveToFirst()) {

            DetalleDocente detalleDocente = new DetalleDocente();

            detalleDocente.setIdRol(c.getInt(0));
            detalleDocente.setIdDocente(c.getInt(1));
            detalleDocente.setIdTramite(c.getInt(2));
            detalleDocente.setAsistencia(c.getInt(3)==1?true:false);
            detalleDocente.setMotivoInasistencia(c.getString(4));


            dbHelper.close();
            return detalleDocente;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(DetalleDocente detalleDocente){


        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(detalleDocente.getIdDocente())
                ,String.valueOf(detalleDocente.getIdRol()),
                String.valueOf(detalleDocente.getIdTramite())};

        ContentValues contentValues = new ContentValues();

        contentValues.put("asistencia", detalleDocente.getAsistencia());
        contentValues.put("motivoinasistencia",detalleDocente.getMotivoInasistencia());

        contador = db.update("detalledocente", contentValues,
                "iddocente=?  and idrol=? and idtramite=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con id : " + detalleDocente.getIdDocente() + " no existe";


    }




    public String eliminar(DetalleDocente detalleDocente){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("detalledocente", "iddocente='" +detalleDocente.getIdDocente()+
                    "' and idtramite='"+detalleDocente.getIdTramite()+"' and idrol='"+detalleDocente.getIdRol()+"'"
                    , null)
            ;
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }


}
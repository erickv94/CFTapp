package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.modelos.Tramite;

public class TramiteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos={"fechasolicitud","idlocal","idtipotramite","idtramite"};

    public TramiteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Tramite tramite) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ContentValues contentValues = new ContentValues();

        contentValues.put("fechasolicitud", sdf.format(tramite.getFechaSolicitud()));
        contentValues.put("idlocal", tramite.getIdLocal());
        contentValues.put("idtipotramite", tramite.getIdTipoTramite());

        db = dbHelper.getWritableDatabase();
        contador = db.insert("tramite", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Tramite consultar(String idlocal, String fecha) throws ParseException {
        db = dbHelper.getWritableDatabase();
        String[] id = {idlocal,fecha};

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");

        Cursor c = db.query("tramite", campos, "idlocal=? and fechasolicitud=?", id,
                null, null, null);
        if (c.moveToFirst()) {
            Tramite tramite = new Tramite();

            tramite.setFechaSolicitud(simpleDateFormat.parse(c.getString(0)));
            tramite.setIdLocal(c.getInt(1));
            tramite.setIdTipoTramite(c.getInt(2));
            tramite.setIdTramite(c.getInt(3));


            dbHelper.close();
            return tramite;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Tramite tramite){

        int contador=0;
        db = dbHelper.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String[] id = {String.valueOf(tramite.getIdTramite())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("idtipotramite",tramite.getIdTipoTramite());
        contentValues.put("fechasolicitud", sdf.format(tramite.getFechaSolicitud()));
        contentValues.put("idlocal",tramite.getIdLocal());


        contador = db.update("tramite", contentValues, "idtramite=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo local: " + tramite.getIdLocal() + " no existe";


    }


    public String eliminar(Tramite tramite){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("tramite", "idtramite='" +tramite.getIdTramite()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }

    public HashMap<Integer,String> getTramites() {
        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("tramite",campos,null,null,null,null,null);
        Cursor tipo, local;

        HashMap<Integer,String> mapeo= new HashMap<Integer, String>();
        String informacion;

        if (c.moveToFirst()) {
            do {
                Tramite tramite = new Tramite();
                informacion="";
                informacion=c.getString(0);
                informacion+=" - ";


                //local
                local=db.query("local",new String[]{"nombrelocal"},"idlocal=?"
                        ,new String[]{String.valueOf(c.getInt(1))},null,null,null);

                if(local.moveToFirst()){
                    informacion+=local.getString(0);
                }
                informacion+=" - ";
                //tipo tramite
                tipo=db.query("tipotramite",new String[]{"nombre"},"idtipotramite=?"
                        ,new String[]{String.valueOf(c.getInt(2))},null,null,null);

                if(tipo.moveToFirst()){
                    informacion+=tipo.getString(0);
                }

                //has
                mapeo.put(c.getInt(3),informacion);

            } while (c.moveToNext());

        }

        dbHelper.close();

        return mapeo;


     }


}
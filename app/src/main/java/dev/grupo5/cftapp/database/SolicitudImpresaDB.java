package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos={"idsolicitudimp ","iddocente","cantidadimpresiones","asunto ","justificacion   ","aprobado ","fechasolicitud ","paginasanexas","codigoimpresion"};

    public SolicitudImpresaDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(SolicitudImpresa solicitudImpresa) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        ContentValues contentValues = new ContentValues();

        contentValues.put("iddocente",solicitudImpresa.getIdDocente());
        contentValues.put("cantidadimpresiones", solicitudImpresa.getCantidadImpresiones());
        contentValues.put("asunto", solicitudImpresa.getAsunto());
        contentValues.put("justificacion", solicitudImpresa.getJustificacion());
        contentValues.put("aprobado", solicitudImpresa.getAprobado());
        contentValues.put("paginasanexas", solicitudImpresa.getPaginasAnexas());
        contentValues.put("codigoimpresion", solicitudImpresa.getCodigoImpresion());
        contentValues.put("fechasolicitud", simpleDateFormat.format(solicitudImpresa.getFechasolicitud()));


        db = dbHelper.getWritableDatabase();
        contador = db.insert("solicitudimpresa", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public HashMap<Integer,String> getSolicitudesImpresas() {
        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("solicitudimpresa",campos,null,null,null,null,null);
        Cursor idDocente;

        HashMap<Integer,String> mapeo= new HashMap<Integer, String>();
        String informacion;

        if (c.moveToFirst()) {
            do {
                SolicitudImpresa solicitudImpresa = new SolicitudImpresa();
                informacion="";
                informacion=c.getString(0);
                informacion+=" - ";


                //Docente
                idDocente=db.query("docente",new String[]{"nombre"},"iddocente=?"
                        ,new String[]{String.valueOf(c.getInt(1))},null,null,null);

                if(idDocente.moveToFirst()){
                    informacion+=idDocente.getString(0);
                }

                //has
                mapeo.put(c.getInt(2),informacion);

            } while (c.moveToNext());

        }

        dbHelper.close();

        return mapeo;


    }



}

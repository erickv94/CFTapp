package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
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
        //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    public String actualizar(SolicitudImpresa solicitudImpresa){

        int contador=0;
        db = dbHelper.getWritableDatabase();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = {String.valueOf(solicitudImpresa.getIdSolicitudImp())};
        ContentValues contentValues = new ContentValues();

        //contentValues.put("idsolicitudimp",solicitudImpresa.getIdSolicitudImp());
        contentValues.put("iddocente",solicitudImpresa.getIdDocente());
        contentValues.put("cantidadimpresiones", solicitudImpresa.getCantidadImpresiones());
        contentValues.put("asunto", solicitudImpresa.getAsunto());
        contentValues.put("justificacion", solicitudImpresa.getJustificacion());
        contentValues.put("aprobado", solicitudImpresa.getAprobado());
        contentValues.put("paginasanexas", solicitudImpresa.getPaginasAnexas());
        contentValues.put("codigoimpresion", solicitudImpresa.getCodigoImpresion());
        contentValues.put("fechasolicitud", simpleDateFormat.format(solicitudImpresa.getFechasolicitud()));


        contador = db.update("solicitudimpresa", contentValues, "idsolicitudimp=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo de solicitud: " + solicitudImpresa.getIdSolicitudImp() + " no existe";


    }

    public SolicitudImpresa consultar(String idsolicitudimp) throws ParseException {
        db=dbHelper.getWritableDatabase();
        String[] id = {idsolicitudimp};
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Cursor cursor = db.query("solicitudimpresa",campos , "idsolicitudimp =? ", id,
                null, null, null);
        if(cursor.moveToFirst()){
            SolicitudImpresa solicitudImpresa= new SolicitudImpresa();

            solicitudImpresa.setIdSolicitudImp(cursor.getInt(0));
            solicitudImpresa.setIdDocente(cursor.getInt(1));
            solicitudImpresa.setCantidadImpresiones(cursor.getInt(2));
            solicitudImpresa.setAsunto(cursor.getString(3));
            solicitudImpresa.setJustificacion(cursor.getString(4));
            solicitudImpresa.setAprobado(cursor.getInt(5)==1?true:false);
            solicitudImpresa.setPaginasAnexas(cursor.getInt(7));
            solicitudImpresa.setCodigoImpresion(cursor.getString(8));
            //solicitudImpresa.setFechasolicitud(simpleDateFormat.parse(cursor.getString(6)));

            dbHelper.close();
            return solicitudImpresa;
        }else{
            dbHelper.close();
            return null;
        }
    }


    public String eliminar(SolicitudImpresa solicitudImpresa){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("solicitudimpresa", "idsolicitudimp='" +solicitudImpresa.getIdSolicitudImp()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
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


                //SolicitudImpresa
                idDocente=db.query("solicitudImpresa",new String[]{"nombre"},"iddocente=?"
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

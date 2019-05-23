package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasNoHabilesDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos={"id_dias","idciclo","nombre","descripcion","fecha"};

    public DiasNoHabilesDB(Context context) {

        this.dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(DiasNoHabiles diasNoHabiles) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;
        //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues contentValues = new ContentValues();

        contentValues.put("idciclo",diasNoHabiles.getIdCiclo());
        contentValues.put("nombre", diasNoHabiles.getNombre());
        contentValues.put("descripcion", diasNoHabiles.getDescripcion());
        contentValues.put("fecha", simpleDateFormat.format(diasNoHabiles.getFecha()));


        db = dbHelper.getWritableDatabase();
        contador = db.insert("diasnohabiles", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public String actualizar(DiasNoHabiles diasNoHabiles){

        int contador=0;
        db = dbHelper.getWritableDatabase();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = {String.valueOf(diasNoHabiles.getIdDias())};
        ContentValues contentValues = new ContentValues();

        //contentValues.put("idsolicitudimp",diasNoHabiles.getIdSolicitudImp());
        contentValues.put("idciclo",diasNoHabiles.getIdCiclo());
        contentValues.put("nombre", diasNoHabiles.getNombre());
        contentValues.put("descripcion", diasNoHabiles.getDescripcion());
        contentValues.put("fecha", simpleDateFormat.format(diasNoHabiles.getFecha()));


        contador = db.update("diasnohabiles", contentValues, "id_dias=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo de solicitud: " + diasNoHabiles.getIdDias() + " no existe";


    }

    public DiasNoHabiles consultar(String id_dias) throws ParseException {
        db=dbHelper.getWritableDatabase();
        String[] id = {id_dias};
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Cursor cursor = db.query("diasnohabiles",campos , "id_dias =? ", id,
                null, null, null);
        if(cursor.moveToFirst()){
            DiasNoHabiles diasNoHabiles= new DiasNoHabiles();

            diasNoHabiles.setIdDias(cursor.getInt(0));
            diasNoHabiles.setIdCiclo(cursor.getInt(1));
            diasNoHabiles.setNombre(cursor.getString(2));
            diasNoHabiles.setDescripcion(cursor.getString(3));
            diasNoHabiles.setFecha(simpleDateFormat.parse(cursor.getString(4)));

            dbHelper.close();
            return diasNoHabiles;
        }else{
            dbHelper.close();
            return null;
        }
    }
    public String eliminar(DiasNoHabiles diasNoHabiles){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("diasnohabiles", "id_dias='" +diasNoHabiles.getIdDias()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }


    public HashMap<Integer,String> getDiasNoHabiles() {
        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("diasnohabiles",campos,null,null,null,null,null);
        Cursor tipo;

        HashMap<Integer,String> mapeo= new HashMap<Integer, String>();
        String informacion;

        if (c.moveToFirst()) {
            do {
                DiasNoHabiles diasNoHabiles = new DiasNoHabiles();
                informacion="";
                informacion=c.getString(0);
                informacion+=" - ";


                //tipo diasNoHabiles
                tipo=db.query("ciclo",new String[]{"camposCiclo"},"idciclo=?"
                        ,new String[]{String.valueOf(c.getInt(1))},null,null,null);

                if(tipo.moveToFirst()){
                    informacion+=tipo.getString(0);
                }

                //has
                mapeo.put(c.getInt(2),informacion);

            } while (c.moveToNext());

        }

        dbHelper.close();

        return mapeo;


    }
}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import dev.grupo5.cftapp.modelos.DetalleLocal;

public class DetalleLocalDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idlocal", "idevaluacion", "cantidadalumnos"};

    public DetalleLocalDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(DetalleLocal detalleLocal) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idevaluacion", detalleLocal.getIdEvaluacion());
        contentValues.put("idlocal", detalleLocal.getIdLocal());
        contentValues.put("cantidadalumnos", detalleLocal.getCantidadAlumnos());

        db = dbHelper.getWritableDatabase();
        contador = db.insert("detallelocal", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public DetalleLocal consultar(String idlocal, String idevaluacion){
        db = dbHelper.getWritableDatabase();
        String[] id = {idlocal, idevaluacion};

        Cursor cursor = db.query("detallelocal", campos, "idlocal=? and idevaluacion=?",id,null,null,null);
        if (cursor.moveToFirst()){
            DetalleLocal detalleLocal = new DetalleLocal();
            detalleLocal.setIdLocal(cursor.getInt(0));
            detalleLocal.setIdEvaluacion(cursor.getInt(1));
            detalleLocal.setCantidadAlumnos(cursor.getInt(2));

            dbHelper.close();
            return detalleLocal;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(DetalleLocal detalleLocal){
        int contador = 0;
        db = dbHelper.getWritableDatabase();
        String[] id = {String.valueOf(detalleLocal.getIdLocal())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("idevaluacion",detalleLocal.getIdEvaluacion());
        contentValues.put("cantidadalumnos",detalleLocal.getCantidadAlumnos());

        contador = db.update("detallelocal", contentValues,"idlocal=?",id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con id local: " + detalleLocal.getIdLocal() + " no existe";
    }

    public String eliminar(DetalleLocal detalleLocal){
        String regafectadas = "filas afectadas";
        int contador = 0;
        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("detallelocal","idlocal=' and idevaluacion='" + detalleLocal.getIdLocal() + detalleLocal.getIdEvaluacion() + "'" + "'",null);
            regafectadas += contador;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regafectadas;
    }

    public HashMap<Integer,String> getDetalleLocales(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("detallelocal", campos, null, null, null, null, null);
        Cursor local, evaluacion;

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        String informacion;
        if (cursor.moveToFirst()){
            do {
                DetalleLocal detalleLocal = new DetalleLocal();
                informacion = "";
                informacion = cursor.getString(0);
                informacion += " - ";

                //local
                local = db.query("local", new String[]{"nombrelocal"},"idlocal=?",
                        new String[]{String.valueOf(cursor.getInt(1))}, null, null, null);

                if (local.moveToFirst()){
                    informacion += local.getString(0);
                }
                informacion += " - ";
                //evaluacion
                evaluacion = db.query("evaluacion", new String[]{"nombreevaluacion"}, "idevaluacion=?",
                        new String[]{String.valueOf(cursor.getInt(2))}, null, null, null);

                if (evaluacion.moveToFirst()){
                    informacion += evaluacion.getString(0);
                }

                //has
                map.put(cursor.getInt(3), informacion);
            } while (cursor.moveToNext());
        }

        dbHelper.close();
        return map;
    }
}

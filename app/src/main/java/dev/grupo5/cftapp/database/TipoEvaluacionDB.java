package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class TipoEvaluacionDB {
    private String[]camposTipoEvaluacion = {"idtipoevaluacion","nombre","descripcion"};
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TipoEvaluacionDB(Context context) {

        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(TipoEvaluacion tipoEvaluacion) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", tipoEvaluacion.getNombre());
        contentValues.put("descripcion", tipoEvaluacion.getDescripcion());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("tipoevaluacion", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public TipoEvaluacion consultar(String idtipoevaluacion){
        db = dbHelper.getWritableDatabase();
        String[] id = {idtipoevaluacion};
        Cursor c = db.query("tipoevaluacion", camposTipoEvaluacion, "idtipoevaluacion=?", id, null, null,null);
        if (c.moveToFirst()) {
            TipoEvaluacion tipoEvaluacion = new TipoEvaluacion();

            tipoEvaluacion.setIdTipoEvaluacion(c.getInt(0));
            tipoEvaluacion.setNombre(c.getString(1));
            tipoEvaluacion.setDescripcion(c.getString(2));
            dbHelper.close();
            return tipoEvaluacion;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(TipoEvaluacion tipoEvaluacion){

        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(tipoEvaluacion.getIdTipoEvaluacion())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idtipoevaluacion",tipoEvaluacion.getIdTipoEvaluacion());
        contentValues.put("nombre",tipoEvaluacion.getNombre());
        contentValues.put("descripcion",tipoEvaluacion.getDescripcion());

        contador = db.update("tipoevaluacion", contentValues, "idtipoevaluacion=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo tipo evaluacion: " + tipoEvaluacion.getIdTipoEvaluacion() + " no existe";
    }

    public String eliminar(TipoEvaluacion tipoEvaluacion){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("tipoevaluacion", "idtipoevaluacion='" +tipoEvaluacion.getIdTipoEvaluacion()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }

    public List<TipoEvaluacion> getTipoEvaluaciones(){

        db=dbHelper.getWritableDatabase();
        Cursor c= db.query("tipoevaluacion",camposTipoEvaluacion,null,null,null,null,null);
        List<TipoEvaluacion> tipoEvaluacionList = new ArrayList<TipoEvaluacion>();
        if (c.moveToFirst()) {
            do {
                TipoEvaluacion tipoEvaluacion = new TipoEvaluacion();
                tipoEvaluacion.setIdTipoEvaluacion(c.getInt(0));
                tipoEvaluacion.setNombre(c.getString(1));
                tipoEvaluacion.setDescripcion(c.getString(2));

                tipoEvaluacionList.add(tipoEvaluacion);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return tipoEvaluacionList;

    }

}

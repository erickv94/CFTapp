package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class TipoEvaluacionDB {
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

    public List<TipoEvaluacion> getTiposEvaluaciones(){
        String[] camposTry = {"idtipoevaluacion", "nombre"};

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("tipoevaluacion", camposTry, null, null, null, null, null);
        List<TipoEvaluacion> tipoEvaluaciones = new ArrayList<TipoEvaluacion>();
        if (cursor.moveToFirst()){
            do {
                TipoEvaluacion tipoEvaluacion = new TipoEvaluacion();
                tipoEvaluacion.setIdTipoEvaluacion(cursor.getInt(0));
                tipoEvaluacion.setNombre(cursor.getString(1));
                tipoEvaluaciones.add(tipoEvaluacion);
            } while (cursor.moveToNext());


        }
        dbHelper.close();
        return tipoEvaluaciones;
    }

}

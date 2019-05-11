package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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


}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.EstadoEvaluacion;

public class EstadoEvaluacionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public EstadoEvaluacionDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(EstadoEvaluacion estadoEvaluacion) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idevaluacion",estadoEvaluacion.getIdEvaluacion());
        contentValues.put("idestudiante", estadoEvaluacion.getIdEstudiante());
        contentValues.put("nota", estadoEvaluacion.getNota());






        db = dbHelper.getWritableDatabase();
        contador = db.insert("estadoevaluacion", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

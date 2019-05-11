package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleLocal;

public class DetalleLocalDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

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

}

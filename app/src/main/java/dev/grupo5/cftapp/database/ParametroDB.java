package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Parametro;

public class ParametroDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ParametroDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Parametro parametro) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("parametro",parametro.getParametro());
        contentValues.put("diashabiles", parametro.getDiasHabiles());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("parametro", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Testigo;

public class TestigoDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TestigoDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Testigo testigo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",testigo.getIdTramite());
        contentValues.put("idestudiante", testigo.getIdEstudiante());
        contentValues.put("justificacion", testigo.getJustificacion());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("testigo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

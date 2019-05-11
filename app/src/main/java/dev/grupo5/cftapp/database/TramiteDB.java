package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import dev.grupo5.cftapp.modelos.Tramite;

public class TramiteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TramiteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Tramite tramite) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ContentValues contentValues = new ContentValues();

        contentValues.put("fechasolicitud", sdf.format(tramite.getFechaSolicitud()));
        contentValues.put("idlocal", tramite.getIdLocal());
        contentValues.put("idtipotramite", tramite.getIdTipoTramite());

        db = dbHelper.getWritableDatabase();
        contador = db.insert("tramite", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}
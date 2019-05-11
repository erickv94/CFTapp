package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleRevision;

public class DetalleRevisionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DetalleRevisionDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(DetalleRevision detalleRevision) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",detalleRevision.getIdTramite());
        contentValues.put("idestudiante", detalleRevision.getIdEstudiante());
        contentValues.put("resultado", detalleRevision.getResultado());
        contentValues.put("motivo", detalleRevision.getMotivo());
        contentValues.put("asistencia", detalleRevision.getAsistencia());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("detallerevision", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

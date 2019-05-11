package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleSolicitud;

public class DetalleSolicitudDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DetalleSolicitudDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(DetalleSolicitud detalleSolicitud) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idtramite",detalleSolicitud.getIdTramite());
        contentValues.put("idestudiante", detalleSolicitud.getIdEstudiante());
        contentValues.put("motivo", detalleSolicitud.getMotivo());
        contentValues.put("esrechazado", detalleSolicitud.getEsRechazado());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("detallesolicitud", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

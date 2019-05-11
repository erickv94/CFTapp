package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public SolicitudImpresaDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(SolicitudImpresa solicitudImpresa) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        ContentValues contentValues = new ContentValues();

        contentValues.put("iddocente",solicitudImpresa.getIdDocente());
        contentValues.put("cantidadimpresiones", solicitudImpresa.getCantidadImpresiones());
        contentValues.put("asunto", solicitudImpresa.getAsunto());
        contentValues.put("justificacion", solicitudImpresa.getJustificacion());
        contentValues.put("aprobado", solicitudImpresa.getAprobado());
        contentValues.put("paginasanexas", solicitudImpresa.getPaginasAnexas());
        contentValues.put("codigoimpresion", solicitudImpresa.getCodigoImpresion());
        contentValues.put("fechasolicitud", simpleDateFormat.format(solicitudImpresa.getFechasolicitud()));


        db = dbHelper.getWritableDatabase();
        contador = db.insert("solicitudimpresa", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

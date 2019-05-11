package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.DetalleDocente;

public class DetalleDocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DetalleDocenteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(DetalleDocente detalleDocente) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idrol", detalleDocente.getIdRol());
        contentValues.put("iddocente", detalleDocente.getIdDocente());
        contentValues.put("idtramite", detalleDocente.getIdTramite());
        contentValues.put("asistencia", detalleDocente.getAsistencia());
        contentValues.put("motivoinasistencia", detalleDocente.getMotivoInasistencia());

        db = dbHelper.getWritableDatabase();
        contador = db.insertWithOnConflict("detalledocente", null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        regInsertados += contador;


        return regInsertados;
    }
}
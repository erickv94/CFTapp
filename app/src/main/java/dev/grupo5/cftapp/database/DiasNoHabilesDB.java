package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasNoHabilesDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DiasNoHabilesDB(Context context) {
        this.dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(DiasNoHabiles diasNoHabiles) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        ContentValues contentValues = new ContentValues();

        contentValues.put("idciclo",diasNoHabiles.getIdCiclo());
        contentValues.put("nombre", diasNoHabiles.getNombre());
        contentValues.put("descripcion", diasNoHabiles.getDescripcion());
        contentValues.put("fecha", simpleDateFormat.format(diasNoHabiles.getFecha()));





        db = dbHelper.getWritableDatabase();
        contador = db.insert("diasnohabiles", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Materia;

public class MateriaDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public MateriaDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Materia materia) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", materia.getNombre());
        contentValues.put("codigo_materia", materia.getCodigoMateria());
        contentValues.put("uvs", materia.getUvs());



        db = dbHelper.getWritableDatabase();
        contador = db.insert("materia", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

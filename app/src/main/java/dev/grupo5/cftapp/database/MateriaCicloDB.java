package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public MateriaCicloDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(MateriaCiclo materiaCiclo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idmateria", materiaCiclo.getIdMateria());
        contentValues.put("idciclo", materiaCiclo.getIdCiclo());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("materiaciclo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

}

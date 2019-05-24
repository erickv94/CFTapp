package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.OpcionCrud;
import dev.grupo5.cftapp.modelos.Usuario;

public class OpcionCrudDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public OpcionCrudDB(Context context) {

        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(OpcionCrud opcionCrud) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("descripcion", opcionCrud.getDescripcion());
        contentValues.put("numcrud", opcionCrud.getNumCrud());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("opcioncrud", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

}

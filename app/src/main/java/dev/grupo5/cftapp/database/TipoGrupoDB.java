package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TipoGrupoDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(TipoGrupo tipoGrupo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", tipoGrupo.getNombre());

        db = dbHelper.getWritableDatabase();
        contador = db.insert("tipogrupo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

}

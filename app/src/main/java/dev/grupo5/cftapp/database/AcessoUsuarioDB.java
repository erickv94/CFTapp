package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.AccesoUsuario;
import dev.grupo5.cftapp.modelos.OpcionCrud;

public class AcessoUsuarioDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public AcessoUsuarioDB(Context context) {

        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(AccesoUsuario accesoUsuario) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idopcion", accesoUsuario.getIdOpcionCrud());
        contentValues.put("idusuario", accesoUsuario.getIdUsuario());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("accesousuario", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }



}

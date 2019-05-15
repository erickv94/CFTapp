package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Parametro;

public class ParametroDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"parametro", "diashabiles"};

    public ParametroDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Parametro parametro) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("parametro",parametro.getParametro());
        contentValues.put("diashabiles", parametro.getDiasHabiles());





        db = dbHelper.getWritableDatabase();
        contador = db.insert("parametro", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Parametro consultar(String param){
        db = dbHelper.getWritableDatabase();
        String[] id = {param};
        Cursor cursor = db.query("parametro", campos, "parametro=?", id, null, null, null);
        if (cursor.moveToFirst()){
            Parametro parametro = new Parametro();
            parametro.setParametro(cursor.getString(0));
            parametro.setDiasHabiles(cursor.getInt(1));

            dbHelper.close();
            return parametro;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Parametro parametro){
        int con = 0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(parametro.getParametro())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("parametro", parametro.getParametro());
        contentValues.put("diashabiles", parametro.getDiasHabiles());

        con = db.update("parametro", contentValues, "parametro=?", id);
        dbHelper.close();

        if (con > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con parametro" + parametro.getParametro() + "no existe";
    }
}

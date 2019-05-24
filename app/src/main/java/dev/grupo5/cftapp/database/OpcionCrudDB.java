package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public OpcionCrud getOpcion(int idOpcion){
        db = dbHelper.getWritableDatabase();
        String[] id = {String.valueOf(idOpcion)};
        Cursor c = db.query("opcioncrud", new String[]{"numcrud","descripcion"}, "numcrud=?", id, null, null, null);

        if (c.moveToFirst()) {
                OpcionCrud opcionCrud= new OpcionCrud();
                opcionCrud.setNumCrud(c.getInt(0));
                opcionCrud.setDescripcion(c.getString(1));

                return  opcionCrud;
        }else {
            return null;
        }

    }

}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Local;

public class LocalDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public LocalDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Local local){
        String regInsertados="Registro Insertado No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombrelocal",local.getNombreLocal());
        contentValues.put("codigolocal",local.getCodigoLocal());
        contentValues.put("codigoedificio",local.getCodigoEdificio());
        contentValues.put("capacidad",local.getCapacidad());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("local",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }

}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public CicloDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Ciclo ciclo){
        String regInsertados="Registro Insertado No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("ciclo",ciclo.getCiclo());
        contentValues.put("anio",ciclo.getAnio());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("ciclo",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }
}

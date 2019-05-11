package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.TipoDocente;


public class TipoDocenteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TipoDocenteDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(TipoDocente tipoDocente){
        String regInsertados="Registros Insertados No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",tipoDocente.getNombre());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("tipodocente",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }

}
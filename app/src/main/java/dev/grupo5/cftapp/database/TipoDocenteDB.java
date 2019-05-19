package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dev.grupo5.cftapp.modelos.TipoDocente;



public class TipoDocenteDB {
    private String[]camposTipoDocente = {"idTipoDocente","nombre"};

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
        regInsertados=regInsertados+contador;

        return regInsertados;



    }

}
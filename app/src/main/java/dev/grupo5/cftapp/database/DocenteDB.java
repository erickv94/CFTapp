package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Docente;

public class DocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DocenteDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Docente docente){
        String regInsertados="Registro Insertado No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",docente.getNombre());
        contentValues.put("apellidos",docente.getApellidos());
        contentValues.put("cod_docente",docente.getCodDocente());
        contentValues.put("sexo",docente.getSexo());
        contentValues.put("idtipodocente",docente.getIdTipoDocente());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("docente",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }
}

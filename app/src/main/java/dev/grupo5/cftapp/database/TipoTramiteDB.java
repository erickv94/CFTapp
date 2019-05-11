package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TipoTramiteDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(TipoTramite tipoTramite){
        String regInsertados="Registro Insertado No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",tipoTramite.getNombre());
        contentValues.put("descripcion",tipoTramite.getDescripcion());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("tipotramite",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }
}

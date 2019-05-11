package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public RolRevisionDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(RolRevision rolRevision){
        String regInsertados="Registros Insertados No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",rolRevision.getNombre());
        contentValues.put("descripcion",rolRevision.getDescripcion());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("rolrevision",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }

}

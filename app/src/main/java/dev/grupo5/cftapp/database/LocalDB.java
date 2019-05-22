package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Local;

public class LocalDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idlocal", "codigoedificio", "nombrelocal","codigolocal","capacidad"};

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

    public List<Local> getLocales(){

        db=dbHelper.getWritableDatabase();
        Cursor c= db.query("local",campos,null,null,null,null,null);
        List<Local> localList = new ArrayList<Local>();
        if (c.moveToFirst()) {
            do {
                Local local = new Local();
                local.setIdLocal(c.getInt(0));
                local.setCodigoEdificio(c.getString(1));
                local.setNombreLocal(c.getString(2));
                local.setCodigoLocal(c.getString(3));
                local.setCapacidad(c.getInt(4));

                localList.add(local);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return localList;

    }

}

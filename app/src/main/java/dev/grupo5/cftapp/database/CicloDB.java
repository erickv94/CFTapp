package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] camposCiclo = {"idciclo","ciclo","anio"};

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

    public List<Ciclo> getCiclos(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("ciclo",camposCiclo,null,null,null,null,null);
        List<Ciclo> cicloList = new ArrayList<Ciclo>();
        if (c.moveToFirst()){
            do {
                Ciclo ciclo = new Ciclo();
                ciclo.setIdCiclo(c.getInt(0));
                ciclo.setCiclo(c.getInt(1));
                cicloList.add(ciclo);
            } while (c.moveToNext());
        }
        dbHelper.close();
        return cicloList;
    }
}

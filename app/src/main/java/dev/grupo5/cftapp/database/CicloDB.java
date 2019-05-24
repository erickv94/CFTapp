package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[]camposCiclo = {"idciclo","ciclo","anio"};


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

    public Ciclo consultar(String idciclo){
        db = dbHelper.getWritableDatabase();
        String[] id = {idciclo};
        Cursor c = db.query("ciclo", camposCiclo, "idciclo=?", id, null, null,null);
        if (c.moveToFirst()) {
            Ciclo ciclo = new Ciclo();

            ciclo.setIdCiclo(c.getInt(0));
            ciclo.setCiclo(c.getInt(1));
            ciclo.setAnio(c.getInt(2));
            dbHelper.close();
            return ciclo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Ciclo ciclo){

        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(ciclo.getIdCiclo())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idciclo",ciclo.getIdCiclo());
        contentValues.put("ciclo",ciclo.getCiclo());
        contentValues.put("anio",ciclo.getAnio());

        contador = db.update("ciclo", contentValues, "idciclo=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo de ciclo: " + ciclo.getIdCiclo() + " no existe";


    }

    public String eliminar(Ciclo ciclo){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("ciclo", "idciclo'" +ciclo.getIdCiclo()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
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

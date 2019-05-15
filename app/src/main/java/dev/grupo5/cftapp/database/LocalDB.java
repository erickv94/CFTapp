package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
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

    public Local consultar(String codigoLocal){
        db = dbHelper.getWritableDatabase();
        String[] id = {codigoLocal};
        Cursor c = db.query("local", campos, "codigolocal=?", id, null, null, null);
        if (c.moveToFirst()) {
            Local local = new Local();

            local.setIdLocal(c.getInt(0));
            local.setCodigoEdificio(c.getString(1));
            local.setNombreLocal(c.getString(2));
            local.setCodigoLocal(c.getString(3));
            local.setCapacidad(c.getInt(4));

            dbHelper.close();
            return local;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Local local){

            int contador=0;
            db = dbHelper.getWritableDatabase();

            String[] id = {String.valueOf(local.getCodigoLocal())};

            ContentValues contentValues = new ContentValues();
            contentValues.put("codigoedificio",local.getCodigoEdificio());
            contentValues.put("nombrelocal",local.getNombreLocal());
            contentValues.put("codigolocal",local.getCodigoLocal());
            contentValues.put("capacidad",local.getCapacidad());


            contador = db.update("local", contentValues, "codigolocal=?", id);
            dbHelper.close();

            if(contador > 0)
                return "Registro Actualizado Correctamente";
            else
                return "Registro con codigo local: " + local.getCodigoLocal() + " no existe";


        }



    public String eliminar(Local local){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("local", "codigolocal='" +local.getCodigoLocal()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
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

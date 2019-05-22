package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.TipoDocente;



public class TipoDocenteDB {
    private String[]camposTipoDocente = {"idtipodocente","nombre"};

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

    public TipoDocente consultar(String idtipodocente){
        db = dbHelper.getWritableDatabase();
        String[] id = {idtipodocente};
        Cursor c = db.query("tipodocente", camposTipoDocente, "idtipodocente=?", id, null, null,null);
        if (c.moveToFirst()) {
            TipoDocente tipoDocente = new TipoDocente();

            tipoDocente.setIdTipoDocente(c.getInt(0));
            tipoDocente.setNombre(c.getString(1));
            dbHelper.close();
            return tipoDocente;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String eliminar(TipoDocente tipoDocente){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("tipodocente", "idtipodocente='" +tipoDocente.getIdTipoDocente()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }

    public String actualizar(TipoDocente tipoDocente){

        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(tipoDocente.getIdTipoDocente())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idtipodocente",tipoDocente.getIdTipoDocente());
        contentValues.put("nombre",tipoDocente.getNombre());

        contador = db.update("tipodocente", contentValues, "idtipodocente=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo tipo docente: " + tipoDocente.getIdTipoDocente() + " no existe";


    }



    public List<TipoDocente> getTipoDocentes(){

        db=dbHelper.getWritableDatabase();
        Cursor c= db.query("tipodocente",camposTipoDocente,null,null,null,null,null);
        List<TipoDocente> tipoDocenteList = new ArrayList<TipoDocente>();
        if (c.moveToFirst()) {
            do {
                TipoDocente tipoDocente = new TipoDocente();
                tipoDocente.setIdTipoDocente(c.getInt(0));
                tipoDocente.setNombre(c.getString(1));

                tipoDocenteList.add(tipoDocente);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return tipoDocenteList;

    }
}
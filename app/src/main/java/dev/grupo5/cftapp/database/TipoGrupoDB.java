package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoDB {
    private String[]camposTipoGrupo = {"idtipogrupo","nombre"};
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public TipoGrupoDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(TipoGrupo tipoGrupo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", tipoGrupo.getNombre());

        db = dbHelper.getWritableDatabase();
        contador = db.insert("tipogrupo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }


    public TipoGrupo consultar(String idtipogrupo){
        db = dbHelper.getWritableDatabase();
        String[] id = {idtipogrupo};
        Cursor c = db.query("tipogrupo", camposTipoGrupo, "idtipogrupo=?", id, null, null,null);
        if (c.moveToFirst()) {
            TipoGrupo tipoGrupo = new TipoGrupo();

            tipoGrupo.setIdTipoGrupo(c.getInt(0));
            tipoGrupo.setNombre(c.getString(1));
            dbHelper.close();
            return tipoGrupo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(TipoGrupo tipoGrupo){

        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(tipoGrupo.getIdTipoGrupo())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("idtipogrupo",tipoGrupo.getIdTipoGrupo());
        contentValues.put("nombre",tipoGrupo.getNombre());

        contador = db.update("tipogrupo", contentValues, "idtipogrupo=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo tipo grupo: " + tipoGrupo.getIdTipoGrupo() + " no existe";


    }

    public String eliminar(TipoGrupo tipoGrupo){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("tipogrupo", "idtipogrupo='" +tipoGrupo.getIdTipoGrupo()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }
    
    public List<TipoGrupo> getTipoGrupos() {

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tipogrupo", camposTipoGrupo, null, null, null, null, null);
        List<TipoGrupo> tipoGrupoList = new ArrayList<TipoGrupo>();
        if (c.moveToFirst()) {
            do {
                TipoGrupo tipoGrupo = new TipoGrupo();
                tipoGrupo.setIdTipoGrupo(c.getInt(0));
                tipoGrupo.setNombre(c.getString(1));

                tipoGrupoList.add(tipoGrupo);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return tipoGrupoList;
    }


}

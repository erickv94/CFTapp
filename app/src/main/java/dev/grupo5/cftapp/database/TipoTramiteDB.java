package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] camposTipoTramite = new String[]{"id_TipoTramite", "nombre", "descripcion"};

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

    public TipoTramite consultar(String idTipoTramite){
        String[] id = {idTipoTramite};
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("TipoTramite", camposTipoTramite, "idTipoTramite", id, null, null, null);
        if (cursor.moveToFirst()) {
            TipoTramite tipoTramite = new TipoTramite();
            tipoTramite.setIdTipoTramite(cursor.getString(0));
            tipoTramite.setNombre(cursor.getString(1));
            tipoTramite.setDescripcion(cursor.getString(2));
            dbHelper.close();
            return tipoTramite;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(TipoTramite tipoTramite){
        int contador;
        String[] id = {String.valueOf(tipoTramite.getIdTipoTramite())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", tipoTramite.getNombre());
        contentValues.put("descripcion", tipoTramite.getDescripcion());
        db = dbHelper.getWritableDatabase();
        contador = db.update("TipoTramite", contentValues, "idTipoTramite=?", id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con Id" + tipoTramite.getIdTipoTramite() + "no existe";
    }
}

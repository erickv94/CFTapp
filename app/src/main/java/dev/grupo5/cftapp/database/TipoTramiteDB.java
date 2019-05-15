package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Local;
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

    public List<TipoTramite> getTiposTramites(){

        String[]camposTry= {"nombre","idtipotramite"};

        db=dbHelper.getWritableDatabase();
        Cursor c= db.query("tipotramite",camposTry,null,null,null,null,null);
        List<TipoTramite> tipoTramites = new ArrayList<TipoTramite>();

        if (c.moveToFirst()) {
            do {
                TipoTramite tipotramite= new TipoTramite();
                tipotramite.setNombre(c.getString(0));
                tipotramite.setIdTipoTramite(c.getInt(1));
                tipoTramites.add(tipotramite);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return tipoTramites;
    }

}

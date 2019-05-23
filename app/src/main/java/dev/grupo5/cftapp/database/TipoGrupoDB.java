package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoDB {

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

    public List<TipoGrupo> getTipoGrupos(){
        String[] camposTry = {"idtipogrupo", "nombre"};

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("tipogrupo", camposTry, null, null, null, null, null);
        List<TipoGrupo> tipoGrupos = new ArrayList<TipoGrupo>();
        if (cursor.moveToFirst()){
            do {
                TipoGrupo tipoGrupo = new TipoGrupo();
                tipoGrupo.setIdTipoGrupo(cursor.getInt(0));
                tipoGrupo.setNombre(cursor.getString(1));
                tipoGrupos.add(tipoGrupo);
            } while (cursor.moveToNext());
        }
        dbHelper.close();
        return tipoGrupos;
    }
}

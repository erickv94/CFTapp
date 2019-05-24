package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.AccesoUsuario;
import dev.grupo5.cftapp.modelos.OpcionCrud;

public class AcessoUsuarioDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public AcessoUsuarioDB(Context context) {

        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(AccesoUsuario accesoUsuario) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("idopcion", accesoUsuario.getIdOpcionCrud());
        contentValues.put("idusuario", accesoUsuario.getIdUsuario());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("accesousuario", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public List<AccesoUsuario> getAccesos(int idUser){
        db = dbHelper.getWritableDatabase();
        String[] id = {String.valueOf(idUser)};
        Cursor c = db.query("accesousuario", new String[]{"idopcion","idusuario"}, "idusuario=?", id, null, null, null);
        List<AccesoUsuario> permisos= new ArrayList<AccesoUsuario>();

        if (c.moveToFirst()) {
            do{
                AccesoUsuario accesoUsuario= new AccesoUsuario();
                accesoUsuario.setIdOpcionCrud(c.getInt(0));
                accesoUsuario.setIdUsuario(c.getInt(1));
                permisos.add(accesoUsuario);
            }while (c.moveToNext());

        }else {
            permisos=null;
        }

     return permisos;
    }

}

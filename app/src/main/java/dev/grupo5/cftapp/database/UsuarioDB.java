package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.TipoEvaluacion;
import dev.grupo5.cftapp.modelos.Usuario;

public class UsuarioDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public UsuarioDB(Context context) {

        dbHelper = DBHelper.getSingleton(context);
    }


    public String insertar(Usuario usuario) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", usuario.getNombre());
        contentValues.put("password", usuario.getPassword());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("usuario", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Usuario getAuth(String user, String pass){
        db = dbHelper.getWritableDatabase();
        String[] id = {user,pass};
        Cursor c = db.query("usuario", new String[]{"nombre","idusuario"}, "nombre=? and password=?", id, null, null, null);

        if (c.moveToFirst()) {

            Usuario usuario = new Usuario();

            usuario.setNombre(c.getString(0));
            usuario.setId(c.getInt(1));

            dbHelper.close();
            return usuario;
        } else {
            dbHelper.close();
            return null;
        }

    }

}

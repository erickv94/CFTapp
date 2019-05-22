package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public RolRevisionDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(RolRevision rolRevision){
        String regInsertados="Registros Insertados No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",rolRevision.getNombre());
        contentValues.put("descripcion",rolRevision.getDescripcion());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("rolrevision",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }



    public List<RolRevision> getRoles() {

        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("rolrevision",new String[]{
                "nombre","idrol"
        },null,null,null,null,null);

        List<RolRevision> roleslist = new ArrayList<RolRevision>();
        if (c.moveToFirst()) {
            do {
                RolRevision rol = new RolRevision();
                rol.setNombre(c.getString(0));
                rol.setIdRol(c.getInt(1));
                roleslist.add(rol);
            } while (c.moveToNext());

        }

        dbHelper.close();

        return roleslist;

    }
}

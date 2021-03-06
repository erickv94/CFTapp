package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] camposRolRevision = {"idrol", "nombre", "descripcion"};

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

    public RolRevision consultar(String idrol){
        String[] id = {idrol};
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("rolrevision", camposRolRevision, "idrol=?", id, null, null, null);
        if (c.moveToFirst()){
            RolRevision rolRevision = new RolRevision();
            rolRevision.setIdRol(c.getInt(0));
            rolRevision.setNombre(c.getString(1));
            rolRevision.setDescripcion(c.getString(2));

            dbHelper.close();
            return rolRevision;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(RolRevision rolRevision){
        int contador = 0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(rolRevision.getIdRol())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", rolRevision.getNombre());
        contentValues.put("descripcion", rolRevision.getDescripcion());
        contador = db.update("rolrevision", contentValues, "idrol=?", id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con nombre rol revision: " + rolRevision.getIdRol() + "no existe";
    }

    public String eliminar(RolRevision rolRevision){
        String reg = "filas afectadas";
        int con = 0;
        try {
            db = dbHelper.getWritableDatabase();
            con += db.delete("rolrevision", "idrol='" + rolRevision.getIdRol() + "'", null);
            reg += con;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return reg;
    }


}

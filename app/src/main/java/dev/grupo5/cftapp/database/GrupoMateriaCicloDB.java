package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;

public class GrupoMateriaCicloDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idgrupo","iddocente","idmatciclo","idtipogrupo","codgrupo","cantidadalumnos","capacidadalumnos"};
    public GrupoMateriaCicloDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(GrupoMateriaCiclo grupoMateriaCiclo) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("iddocente",grupoMateriaCiclo.getIdDocente());
        contentValues.put("idmatciclo", grupoMateriaCiclo.getIdMatCiclo());
        contentValues.put("idtipogrupo", grupoMateriaCiclo.getIdTipoGrupo());
        contentValues.put("codgrupo", grupoMateriaCiclo.getCodgrupo());
        contentValues.put("cantidadalumnos", grupoMateriaCiclo.getCantidadAlumnos());
        contentValues.put("capacidadalumnos", grupoMateriaCiclo.getCapacidadAlumnos());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("grupomateriaciclo", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public GrupoMateriaCiclo consultar(String tipo,String grupo){
        db = dbHelper.getWritableDatabase();
        String[] id = {tipo, grupo};

        Cursor cursor = db.query("grupomateriaciclo", campos, "idgrupo=? and idtipogrupo=?",id,null,null,null);
        if (cursor.moveToFirst()){
            GrupoMateriaCiclo grupoMateriaCiclo = new GrupoMateriaCiclo();
            grupoMateriaCiclo.setIdGrupo(cursor.getInt(0));
            grupoMateriaCiclo.setIdDocente(cursor.getInt(1));
            grupoMateriaCiclo.setIdTipoGrupo(cursor.getInt(2));
            grupoMateriaCiclo.setIdMatCiclo(cursor.getInt(3));
            grupoMateriaCiclo.setCodgrupo(cursor.getString(4));
            grupoMateriaCiclo.setCantidadAlumnos(cursor.getInt(5));
            grupoMateriaCiclo.setCapacidadAlumnos(cursor.getInt(6));

            dbHelper.close();
            return grupoMateriaCiclo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(GrupoMateriaCiclo grupoMateriaCiclo){
        int contador = 0;
        db = dbHelper.getWritableDatabase();
        String[] id = {String.valueOf(grupoMateriaCiclo.getIdGrupo())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("iddocente",grupoMateriaCiclo.getIdDocente());
        contentValues.put("idmatciclo",grupoMateriaCiclo.getIdMatCiclo());
        contentValues.put("idtipogrupo",grupoMateriaCiclo.getIdTipoGrupo());
        contentValues.put("codgrupo",grupoMateriaCiclo.getCodgrupo());
        contentValues.put("cantidadalumnos",grupoMateriaCiclo.getCantidadAlumnos());
        contentValues.put("capacidadalumnos",grupoMateriaCiclo.getCapacidadAlumnos());

        contador = db.update("grupomateriaciclo", contentValues,"idgrupo=?",id);
        dbHelper.close();

        if (contador > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con id grupo: " + grupoMateriaCiclo.getIdGrupo() + " no existe";
    }

    public String eliminar(GrupoMateriaCiclo grupoMateriaCiclo){
        String regafectadas = "filas afectadas";
        int contador = 0;
        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("grupomateriaciclo","idgrupo='" + grupoMateriaCiclo.getIdGrupo() +
                    "'",null);
            regafectadas += contador;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regafectadas;
    }


    public List<GrupoMateriaCiclo> getGruposMateriasCiclos(){
        String[] camposTry = {"idgrupo", "codgrupo"};
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("grupomateriaciclo", camposTry, null, null, null, null, null);
        List<GrupoMateriaCiclo> grupoMateriaCiclos = new ArrayList<GrupoMateriaCiclo>();

        if (cursor.moveToFirst()){
            do {
                GrupoMateriaCiclo grupoMateriaCiclo = new GrupoMateriaCiclo();
                grupoMateriaCiclo.setIdGrupo(cursor.getInt(0));
                grupoMateriaCiclo.setCodgrupo(cursor.getString(1));
                grupoMateriaCiclos.add(grupoMateriaCiclo);
            } while (cursor.moveToNext());
        }
        dbHelper.close();
        return grupoMateriaCiclos;
    }


}

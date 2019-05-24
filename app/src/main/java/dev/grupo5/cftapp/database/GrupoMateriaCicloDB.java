package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;

public class GrupoMateriaCicloDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

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

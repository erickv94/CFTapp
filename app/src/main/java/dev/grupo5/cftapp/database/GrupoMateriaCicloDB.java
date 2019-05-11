package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

}

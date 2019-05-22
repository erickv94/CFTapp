package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Docente;

public class DocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DocenteDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Docente docente){
        String regInsertados="Registro Insertado No: ";
        long contador=0;
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre",docente.getNombre());
        contentValues.put("apellidos",docente.getApellidos());
        contentValues.put("cod_docente",docente.getCodDocente());
        contentValues.put("sexo",docente.getSexo());
        contentValues.put("idtipodocente",docente.getIdTipoDocente());

        db=dbHelper.getWritableDatabase();
        contador=db.insert("docente",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }



    public List<Docente> getDocentes(){


        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("docente",new String[]{"nombre","apellidos","iddocente"},null,null,null,null,null);

        List<Docente> docentesList = new ArrayList<Docente>();
        if (c.moveToFirst()) {
            do {
                Docente docente = new Docente();

                docente.setNombre(c.getString(0));
                docente.setApellidos(c.getString(1));
                docente.setIdDocente(c.getInt(2));
                docentesList.add(docente);

            } while (c.moveToNext());

        }

        dbHelper.close();

        return docentesList;
    }
}

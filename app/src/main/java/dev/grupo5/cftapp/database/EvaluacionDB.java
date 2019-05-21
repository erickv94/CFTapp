package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dev.grupo5.cftapp.modelos.Estudiante;
import dev.grupo5.cftapp.modelos.Evaluacion;

public class EvaluacionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public EvaluacionDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Evaluacion evaluacion) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");

        ContentValues contentValues = new ContentValues();

        contentValues.put("idgrupo", evaluacion.getIdGrupo());
        contentValues.put("idtipoevaluacion", evaluacion.getIdTipoEvaluacion());
        contentValues.put("nombreevaluacion", evaluacion.getNombreEvaluacion());
        contentValues.put("fecha",simpleDateFormat.format(evaluacion.getFecha()));


        db = dbHelper.getWritableDatabase();
        contador = db.insert("evaluacion", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }


    public List<Evaluacion> getEvaluaciones(){
        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("evaluacion",new String[]{"nombreevaluacion","idevaluacion"},null,null,null,null,null);

        List<Evaluacion> evaluacionList = new ArrayList<Evaluacion>();
        if (c.moveToFirst()) {
            do {
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setNombreEvaluacion(c.getString(0));
                evaluacion.setIdEvaluacion(c.getInt(1));
                evaluacionList.add(evaluacion);

            } while (c.moveToNext());

        }

        dbHelper.close();

        return evaluacionList;

    }
}

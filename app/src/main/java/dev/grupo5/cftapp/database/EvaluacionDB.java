package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

}

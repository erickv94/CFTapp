package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Estudiante;

public class EstudianteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public EstudianteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Estudiante estudiante) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombres", estudiante.getNombres());
        contentValues.put("apellidos", estudiante.getApellidos());
        contentValues.put("carnet", estudiante.getCarnet());
        contentValues.put("sexo", estudiante.getSexo());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("estudiante", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }
}

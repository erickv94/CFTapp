package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dev.grupo5.cftapp.modelos.Estudiante;

public class EstudianteDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idestudiante", "nombres", "apelllidos","carnet","sexo"};

    public EstudianteDB(Context context) {
        dbHelper = DBHelper.getSingleton(context);
    }

    public String insertar(Estudiante estudiante) {
        String regInsertados = "Registro Insertado No: ";
        long contador = 0;

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombres", estudiante.getNombres());
        contentValues.put("apelllidos", estudiante.getApellidos());
        contentValues.put("carnet", estudiante.getCarnet());
        contentValues.put("sexo", estudiante.getSexo());


        db = dbHelper.getWritableDatabase();
        contador = db.insert("estudiante", null, contentValues);
        regInsertados += contador;


        return regInsertados;

    }

    public Estudiante consultar(String carnet){
        db = dbHelper.getWritableDatabase();
        String[] id = {carnet};
        Cursor c = db.query("estudiante", campos, "carnet=?", id, null, null, null);
        if (c.moveToFirst()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setIdEstudiante(c.getInt(0));
            estudiante.setNombres(c.getString(1));
            estudiante.setApellidos(c.getString(2));
            estudiante.setCarnet(c.getString(3));
            estudiante.setSexo(c.getString(4));

            dbHelper.close();
            return estudiante;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Estudiante estudiante){
        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(estudiante.getCarnet())};

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombres", estudiante.getNombres());
        contentValues.put("apelllidos", estudiante.getApellidos());
        contentValues.put("carnet", estudiante.getCarnet());
        contentValues.put("sexo", estudiante.getSexo());

        contador = db.update("estudiante", contentValues, "carnet=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con codigo local: " + estudiante.getCarnet() + " no existe";


    }

}

package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import dev.grupo5.cftapp.modelos.Evaluacion;

public class EvaluacionDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] campos = {"idevaluacion", "idgrupo", "idtipoevaluacion", "nombreevaluacion", "fecha"};

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

    public Evaluacion consultar(String idtipoevaluacion, String fecha) throws ParseException {
        db=dbHelper.getWritableDatabase();
        String[] id = {idtipoevaluacion, fecha};

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Cursor c = db.query("evaluacion", campos, "idtipoevaluacion=? and fecha=?", id,
        null, null, null);

        if (c.moveToFirst()){
            Evaluacion evaluacion = new Evaluacion();

            evaluacion.setFecha(simpleDateFormat.parse(c.getString(0)));
            evaluacion.setIdEvaluacion(c.getInt(1));
            evaluacion.setIdGrupo(c.getInt(2));
            evaluacion.setIdTipoEvaluacion(c.getInt(3));
            evaluacion.setNombreEvaluacion(c.getString(4));

            dbHelper.close();
            return evaluacion;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Evaluacion evaluacion){
        int con = 0;
        db = dbHelper.getWritableDatabase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String[] id = {String.valueOf(evaluacion.getIdEvaluacion())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("idgrupo", evaluacion.getIdGrupo());
        contentValues.put("idtipoevaluacion", evaluacion.getIdTipoEvaluacion());
        contentValues.put("nombreevaluacion", evaluacion.getNombreEvaluacion());
        contentValues.put("fecha", simpleDateFormat.format(evaluacion.getFecha()));

        con = db.update("evaluacion", contentValues, "idevaluacion=?", id);
        dbHelper.close();

        if (con > 0)
            return "Registro actualizado correctamente";
        else
            return "Registro con id evaluacion: " + evaluacion.getIdEvaluacion() + "no existe";
    }

    public String eliminar(Evaluacion evaluacion){
        String reg = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("evaluacion", "idevaluacion='" + evaluacion.getIdEvaluacion() + "'", null);
            reg += contador;
            dbHelper.close();
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return reg;
    }

    public HashMap<Integer, String> getEvaluaciones(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("evaluacion", campos, null, null, null, null, null);
        Cursor tipo, grupo;

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        String informacion;
        if (cursor.moveToFirst()){
            do {
                Evaluacion evaluacion = new Evaluacion();
                informacion = "";
                informacion = cursor.getString(0);
                informacion += " - ";

                //grupo
                grupo = db.query("grupomateriaciclo", new String[]{"codgrupo"},"idgrupo=?",
                new String[]{String.valueOf(cursor.getInt(1))}, null, null, null);

                if (grupo.moveToFirst()){
                    informacion += grupo.getString(0);
                }
                informacion += " - ";
                //tipo evaluacion
                tipo = db.query("tipoevaluacion", new String[]{"nombre"}, "idtipoevaluacion=?",
                        new String[]{String.valueOf(cursor.getInt(2))}, null, null, null);

                if (tipo.moveToFirst()){
                    informacion += tipo.getString(0);
                }

                //has
                map.put(cursor.getInt(3), informacion);
            } while (cursor.moveToNext());
        }

        dbHelper.close();
        return map;
    }
}

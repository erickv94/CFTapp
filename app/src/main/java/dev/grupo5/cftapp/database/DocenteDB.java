package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import dev.grupo5.cftapp.modelos.Docente;

public class DocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] camposDocente = {"idDocente", "nom_docente"};
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
        contador=db.insert("Docente",null,contentValues);
        regInsertados+=contador;


        return regInsertados;
    }

    public void abrir() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        dbHelper.close();
    }

    public void actualizar(Docente docente) {

    }

    public String eliminar(Docente docente){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("docente", "idDocente='" +docente.getIdDocente()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }
    public Docente consultar(String idDocente){
        String[] id = {idDocente};
        Cursor cursor = db.query("docente",camposDocente , "idDocente = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Docente docente= new Docente();
            docente.setIdDocente(Integer.parseInt(cursor.getString(0)));
            docente.setCodDocente(cursor.getString(1));
            docente.setNombre(cursor.getString(2));
            docente.setSexo(cursor.getString(3));
            docente.setApellidos(cursor.getString(4));
            docente.setIdTipoDocente(Integer.parseInt(cursor.getString(5)));
            return docente;
        }else{
            return null;
        }
    }

}

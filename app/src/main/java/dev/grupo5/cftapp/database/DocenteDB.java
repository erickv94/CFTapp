package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import android.database.sqlite.SQLiteConstraintException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.modelos.Docente;

public class DocenteDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] camposDocente = {"iddocente", "idtipodocente","nombre","apellidos","cod_docente","sexo"};

    public DocenteDB(Context context){

        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Docente docente){

        String regInsertados="Registros Insertados No: ";
        long contador=0;

        ContentValues contentValues= new ContentValues();
        //contentValues.put("iddocente",docente.getIdDocente());
        contentValues.put("idtipodocente",docente.getIdTipoDocente());
        contentValues.put("nombre",docente.getNombre());
        contentValues.put("apellidos",docente.getApellidos());
        contentValues.put("cod_docente",docente.getCodDocente());
        contentValues.put("sexo",docente.getSexo());


        db=dbHelper.getWritableDatabase();
        contador=db.insert("docente",null,contentValues);
        regInsertados=regInsertados+contador;

        return regInsertados;
        
    }

    public void abrir() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        dbHelper.close();
    }

    public String actualizar(Docente docente) {
        int contador=0;
        db = dbHelper.getWritableDatabase();

        String[] id = {String.valueOf(docente.getIdDocente())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("idtipodocente",docente.getIdTipoDocente());
        contentValues.put("nombre", docente.getNombre());
        contentValues.put("apellidos",docente.getApellidos());
        contentValues.put("cod_docente",docente.getCodDocente());
        contentValues.put("sexo",docente.getSexo());


        contador = db.update("docente", contentValues, "iddocente=?", id);
        dbHelper.close();

        if(contador > 0)
            return "Registro Actualizado Correctamente";

        else
            return "Registro con codigo local: " + docente.getIdDocente() + " no existe";

    }

    public String eliminar(Docente docente){


        String regAfectados = "filas afectadas";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("docente", "iddocente='" +docente.getIdDocente()+ "'", null);
            regAfectados += contador;
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return regAfectados;
    }



    public Docente consultar(String idtipodocente) {
        db=dbHelper.getWritableDatabase();
        String[] id = {idtipodocente};
        Cursor cursor = db.query("docente",camposDocente , "iddocente=?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Docente docente= new Docente();

            docente.setIdDocente(cursor.getInt(0));
            docente.setIdTipoDocente(cursor.getInt(1));
            docente.setNombre(cursor.getString(2));
            docente.setApellidos(cursor.getString(3));
            docente.setCodDocente(cursor.getString(4));
            docente.setSexo(cursor.getString(5));

            /*ciclo.setIdCiclo(c.getInt(0));
            ciclo.setCiclo(c.getInt(1));
            ciclo.setAnio(c.getInt(2)); */

            dbHelper.close();
            return docente;
        }else{
            dbHelper.close();
            return null;
        }
    }


    public List<Docente> getListaDocentes(){

        db=dbHelper.getWritableDatabase();
        Cursor c= db.query("docente",camposDocente,null,null,null,null,null);
        List<Docente> docenteList = new ArrayList<Docente>();
        if (c.moveToFirst()) {
            do {
                Docente tipoDocente = new Docente();
                tipoDocente.setIdDocente(c.getInt(0));
                tipoDocente.setNombre(c.getString(1));

                docenteList.add(tipoDocente);
            } while (c.moveToNext());

        }
        dbHelper.close();

        return docenteList;

    }

    public List<Docente> getDocentesListado(){


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

    public HashMap<Integer,String> getDocentes() {
        db=dbHelper.getReadableDatabase();
        Cursor c= db.query("docente",camposDocente,null,null,null,null,null);
        Cursor tipo;

        HashMap<Integer,String> mapeo= new HashMap<Integer, String>();
        String informacion;

        if (c.moveToFirst()) {
            do {
                Docente docente = new Docente();
                informacion="";
                informacion=c.getString(0);
                informacion+=" - ";


                //tipo Docente
                tipo=db.query("tipodocente",new String[]{"nombre"},"idtipodocente=?"
                        ,new String[]{String.valueOf(c.getInt(1))},null,null,null);

                if(tipo.moveToFirst()){
                    informacion+=tipo.getString(0);
                }
                //informacion += " - ";
                //has
                mapeo.put(c.getInt(2),informacion);

            } while (c.moveToNext());

        }

        dbHelper.close();


        return mapeo;


    }



}

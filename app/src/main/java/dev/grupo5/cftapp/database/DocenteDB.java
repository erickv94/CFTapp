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
    private String[] camposDocente = {"iddocente", "idtipodocente","nombre","apellidos","cod_docente","sexo"};

    public DocenteDB(Context context){
        dbHelper=DBHelper.getSingleton(context);
    }

    public String insertar(Docente docente){

        String regInsertados="Registros Insertados No: ";
        long contador=0;

        ContentValues contentValues= new ContentValues();
        contentValues.put("iddocente",docente.getIdDocente());
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

    public void actualizar(Docente docente) {

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
    public Docente consultar(String idDocente){
        String[] id = {idDocente};
        Cursor cursor = db.query("docente",camposDocente , "iddocente = ?", id,
                null, null, null);
        if(cursor.moveToFirst()){
            Docente docente= new Docente();
            //docente.setIdDocente(Integer.parseInt(cursor.getString(0)));
            docente.setIdTipoDocente(Integer.parseInt(cursor.getString(0)));
            docente.setCodDocente(cursor.getString(1));
            docente.setNombre(cursor.getString(2));
            docente.setSexo(cursor.getString(3));
            docente.setApellidos(cursor.getString(4));

            return docente;
        }else{
            return null;
        }
    }

  /*  private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){case 1:
        {
//verificar que al insertar docente exista carnet del alumno y el codigo de materia
            Docente docente = (Docente)dato;
            String[] id1 = {Integer.parseInt(docente.getIdDocente())};
            String[] id2 = {docente.getIdTipoDocente()};
//abrir();
            Cursor cursor1 = db.query("docente", null, "iddocente = ?", id1, null,
                    null, null);
            Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2,
                    null, null, null);
            if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
                return true;
            }
            return false;
        }
            case 2:
            {
//verificar que al modificar docente exista carnet del alumno, el
                codigo de materia y el ciclo
                Docente docente1 = (Docente)dato;
                String[] ids = {docente1.getCarnet(), docente1.getCodmateria(),
                        docente1.getCiclo()};
                abrir();
                Cursor c = db.query("docente", null, "carnet = ? AND codmateria = ? AND
                        ciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            case 3:
            {
                Alumno alumno = (Alumno)dato;
                Cursor c=db.query(true, "docente", new String[] {
                                "carnet" }, "carnet='"+alumno.getCarnet()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 4:
            {
                Materia materia = (Materia)dato;
                Cursor cmat=db.query(true, "docente", new String[] {
                                "codmateria" },
                        "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
                if(cmat.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 5:
            {
//verificar que exista alumno
                Alumno alumno2 = (Alumno)dato;
                String[] id = {alumno2.getCarnet()};
                abrir();
                Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null,
                        null);
                if(c2.moveToFirst()){
//Se encontro Alumno
                    return true;
                }
                return false;
            }
            case 6:
            {
//verificar que exista Materia
                Materia materia2 = (Materia)dato;
                String[] idm = {materia2.getCodmateria()};
                abrir();
                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
//Se encontro Materia
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }  */

}

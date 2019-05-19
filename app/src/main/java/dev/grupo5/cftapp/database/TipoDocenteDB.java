package dev.grupo5.cftapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dev.grupo5.cftapp.modelos.TipoDocente;



public class TipoDocenteDB {
    private static final String[]camposTipoDocente = new String []
            {"idTipoDocente","nombre"};

    private SQLiteDatabase db;
    private DBHelper dbHelper;


    public TipoDocenteDB(Context context){

        dbHelper=DBHelper.getSingleton(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "proyecto1.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE tipodocente(idtipodocente integer(7) NOT NULL PRIMARY KEY,nombre VARCHAR(30));");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }
    public void abrir() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        dbHelper.close();
    }




    public String insertar(TipoDocente tipoDocente){
        String regInsertados="Registros Insertados No: ";
        long contador=0;

        ContentValues contentValues= new ContentValues();
        contentValues.put("nombre",tipoDocente.getNombre());

        contador=db.insert("tipodocente",null,contentValues);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;



    }

}
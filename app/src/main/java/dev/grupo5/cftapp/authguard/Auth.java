package dev.grupo5.cftapp.authguard;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import dev.grupo5.cftapp.MainActivity;
import dev.grupo5.cftapp.database.AcessoUsuarioDB;
import dev.grupo5.cftapp.database.OpcionCrudDB;
import dev.grupo5.cftapp.modelos.AccesoUsuario;
import dev.grupo5.cftapp.modelos.OpcionCrud;


public class Auth {


    public static boolean userHasPermission(int idUser, Context context,int idPermiso){

        AcessoUsuarioDB acessoUsuarioDB= new AcessoUsuarioDB(context);
        OpcionCrudDB opcionCrudDB= new OpcionCrudDB(context);

        List<AccesoUsuario> lista=acessoUsuarioDB.getAccesos(idUser);
        OpcionCrud opcion= new OpcionCrud();

        for (AccesoUsuario access:lista) {
            opcion=opcionCrudDB.getOpcion(access.getIdOpcionCrud());
            if(opcion.getNumCrud()==idPermiso){
                return true;
            }
        }


        return false;
    }

    public static int guard(Context context){
        return context.getSharedPreferences("auth",Context.MODE_PRIVATE).getInt("userid",0);
    }


}

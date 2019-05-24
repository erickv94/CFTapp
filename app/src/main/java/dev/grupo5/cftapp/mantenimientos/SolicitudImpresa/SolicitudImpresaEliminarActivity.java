package dev.grupo5.cftapp.mantenimientos.SolicitudImpresa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.SolicitudImpresaDB;
import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaEliminarActivity extends AppCompatActivity {

    EditText editIdSolicitud;
    private static final int permiso = 84;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresar_eliminar);
        setTitle(R.string.solicitudesdelete);
        verificarPermisos();
        editIdSolicitud=findViewById(R.id.idSolicitudes);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.solicitudesdelete), Toast.LENGTH_LONG).show();
        }

    }

    public  void eliminarSolicitudImpresa(View view){
        SolicitudImpresaDB solicitudImpresaDB= new SolicitudImpresaDB(this);
        SolicitudImpresa solicitudImpresa= new SolicitudImpresa();

        try {
            solicitudImpresa.setIdSolicitudImp(Integer.valueOf(editIdSolicitud.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String resultado=solicitudImpresaDB.eliminar(solicitudImpresa);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdSolicitud.setText("");
        
    }
}

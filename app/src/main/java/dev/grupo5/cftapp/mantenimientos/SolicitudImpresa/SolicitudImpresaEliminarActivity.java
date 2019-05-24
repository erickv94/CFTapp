package dev.grupo5.cftapp.mantenimientos.SolicitudImpresa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.SolicitudImpresaDB;
import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaEliminarActivity extends AppCompatActivity {

    EditText editIdSolicitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresar_eliminar);
        setTitle(R.string.solicitudesdelete);

        editIdSolicitud=findViewById(R.id.idSolicitudes);

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

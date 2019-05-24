package dev.grupo5.cftapp.mantenimientos.Tramite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Tramite;

public class TramiteEliminarActivity extends AppCompatActivity {
    EditText idText;
    private static final int  permiso=28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramite_eliminar);
        setTitle(R.string.tramitedelete);
        verificarPermisos();

        idText= findViewById(R.id.idtramite);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tramitedelete), Toast.LENGTH_LONG).show();
        }

    }

    public  void eliminarTramite(View view){
        TramiteDB tramiteDB= new TramiteDB(this);
        Tramite tramite= new Tramite();

        try {
            tramite.setIdTramite(Integer.valueOf(idText.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String resultado=tramiteDB.eliminar(tramite);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View view){
        idText.setText("");
    }
}

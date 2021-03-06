package dev.grupo5.cftapp.mantenimientos.Local;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.Local;

public class LocalEliminarActivity extends AppCompatActivity {
    EditText codigolocalText;
    private static final int  permiso=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        setTitle(R.string.localdelete);
        verificarPermisos();
        codigolocalText= findViewById(R.id.ideliminar);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.localdelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarLocal(View view){
        Local local= new Local();
        LocalDB localDB= new LocalDB(this);
        String resultado;

        local.setCodigoLocal(codigolocalText.getText().toString());
        resultado=localDB.eliminar(local);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }


}

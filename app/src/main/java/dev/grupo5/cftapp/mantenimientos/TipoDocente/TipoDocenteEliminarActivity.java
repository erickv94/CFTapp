package dev.grupo5.cftapp.mantenimientos.TipoDocente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class TipoDocenteEliminarActivity extends AppCompatActivity {
    EditText editIdTipoDocente;
    private static final int  permiso=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_eliminar);
        setTitle(R.string.tipodocentedelete);
        verificarPermisos();
        editIdTipoDocente=findViewById(R.id.editIdTipoDocente);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipodocentedelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarTipoDocente(View view){
        TipoDocente tipoDocente= new TipoDocente();
        TipoDocenteDB tipoDocenteDB= new TipoDocenteDB(this);
        String resultado;

        tipoDocente.setIdTipoDocente(Integer.parseInt(editIdTipoDocente.getText().toString()));
        resultado=tipoDocenteDB.eliminar(tipoDocente);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }

}

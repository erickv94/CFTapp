package dev.grupo5.cftapp.mantenimientos.TipoGrupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoEliminarActivity extends AppCompatActivity {

    EditText editIdTipoGrupo;
    private static final int permiso=36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_eliminar);
        setTitle(R.string.tipogrupodelete);
        verificarPermisos();

        editIdTipoGrupo=findViewById(R.id.editIdTipoGrupo);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipogrupodelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarTipoGrupo(View view){
        TipoGrupo tipoGrupo= new TipoGrupo();
        TipoGrupoDB tipoGrupoDB= new TipoGrupoDB(this);
        String resultado;

        tipoGrupo.setIdTipoGrupo(Integer.parseInt(editIdTipoGrupo.getText().toString()));
        resultado=tipoGrupoDB.eliminar(tipoGrupo);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }
}

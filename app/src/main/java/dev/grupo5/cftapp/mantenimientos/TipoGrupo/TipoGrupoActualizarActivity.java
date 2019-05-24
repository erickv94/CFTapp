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

public class TipoGrupoActualizarActivity extends AppCompatActivity {

    EditText editIdTipoGrupo;
    EditText editNombre;
    private static final int permiso=35;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_actualizar);
        setTitle(R.string.tipogrupoupdate);
        verificarPermisos();

        editIdTipoGrupo=findViewById(R.id.editIdTipoGrupo);
        editNombre= findViewById(R.id.editNombre);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipogrupoupdate), Toast.LENGTH_LONG).show();
        }

    }

    public void actualizarTipoGrupo(View view){
        TipoGrupo tipoDocente= new TipoGrupo();
        String registros;
        TipoGrupoDB tipoDocenteDB= new TipoGrupoDB(this);
        tipoDocente.setIdTipoGrupo(Integer.parseInt(editIdTipoGrupo.getText().toString()));
        tipoDocente.setNombre(editNombre.getText().toString());
        registros=tipoDocenteDB.actualizar(tipoDocente);
        Toast.makeText(this,registros, Toast.LENGTH_SHORT).show();
}

}

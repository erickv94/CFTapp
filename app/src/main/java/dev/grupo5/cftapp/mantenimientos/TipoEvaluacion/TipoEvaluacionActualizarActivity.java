package dev.grupo5.cftapp.mantenimientos.TipoEvaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class TipoEvaluacionActualizarActivity extends AppCompatActivity {

    EditText editIdTipoEvaluacion;
    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_actualizar);
        setTitle(R.string.tipoevaluacionupdate);
        verificarPermisos();

        editIdTipoEvaluacion=findViewById(R.id.editIdTipoEvaluacion);
        editNombre= findViewById(R.id.editNombre);
        editDescripcion= findViewById(R.id.editDescripcion);
    }

    public void actualizarTipoEvaluacion(View view){
        TipoEvaluacion tipoEvaluacion= new TipoEvaluacion();
        String registros;
        TipoEvaluacionDB tipoDocenteDB= new TipoEvaluacionDB(this);
        tipoEvaluacion.setIdTipoEvaluacion(Integer.parseInt(editIdTipoEvaluacion.getText().toString()));
        tipoEvaluacion.setNombre(editNombre.getText().toString());
        tipoEvaluacion.setDescripcion(editDescripcion.getText().toString());

        registros=tipoDocenteDB.actualizar(tipoEvaluacion);
        Toast.makeText(this,registros,Toast.LENGTH_SHORT).show();
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluacionupdate), Toast.LENGTH_LONG).show();
        }

    }


    public void limpiarTexto(View v) {
        editIdTipoEvaluacion.setText("");
        editNombre.setText("");
        editDescripcion.setText("");
    }
}


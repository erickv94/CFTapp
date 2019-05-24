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

public class TipoEvaluacionConsultarActivity extends AppCompatActivity {


    EditText editIdTipoEvaluacion;
    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_consultar);
        setTitle(R.string.tipoevaluacionread);
        verificarPermisos();
        editIdTipoEvaluacion=findViewById(R.id.editIdTipoEvaluacion);
        editNombre=findViewById(R.id.editNombre);
        editDescripcion=findViewById(R.id.editDescripcion);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluacionread), Toast.LENGTH_LONG).show();
        }

    }

    public void consultarTipoEvaluacion(View view) {
        TipoEvaluacionDB tipoEvaluacionDB = new TipoEvaluacionDB(this);
        TipoEvaluacion tipoEvaluacion = tipoEvaluacionDB.consultar(editIdTipoEvaluacion.getText().toString());
      /*  try {
            System.out.println(tipoEvaluacion.getIdTipoDocente() + "," + tipoEvaluacion.getNombre());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        if(tipoEvaluacion!=null){
            editNombre.setText(tipoEvaluacion.getNombre());
            editDescripcion.setText(tipoEvaluacion.getDescripcion());
            return;
        }

        Toast.makeText(this,"Resultado no existe" ,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdTipoEvaluacion.setText("");
        editNombre.setText("");
        editDescripcion.setText("");
    }

}

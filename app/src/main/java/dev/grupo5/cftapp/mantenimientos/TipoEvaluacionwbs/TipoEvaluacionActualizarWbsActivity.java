package dev.grupo5.cftapp.mantenimientos.TipoEvaluacionwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.os.StrictMode;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.TipoEvaluacionWS;
@SuppressLint("NewApi")
public class TipoEvaluacionActualizarWbsActivity extends AppCompatActivity {

    EditText editIdTipoEvaluacion;
    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 51;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_actualizar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.tipoevaluacionupdate)+" webservice");
        verificarPermisos();
        editIdTipoEvaluacion=findViewById(R.id.editIdTipoEvaluacion);
        editNombre= findViewById(R.id.editNombre);
        editDescripcion= findViewById(R.id.editDescripcion);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluacionupdate), Toast.LENGTH_LONG).show();
        }

    }
    public void actualizarTipoEvaluacionWS(View view){
        String url="http://192.168.0.21/ws_tipo_evaluacion_update.php?" +
                "idtipoevaluacion=" +editIdTipoEvaluacion.getText().toString()+
                "&nombre=" +editNombre.getText().toString()+
                "&descripcion="+editDescripcion.getText().toString();
        String mensaje=TipoEvaluacionWS.actualizarTipoEvaluacion(url,this);
        if (mensaje.equals("No se pudo actualizar")){
            Toast.makeText(this,"No se pudo Actualizar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Registro Actualizado con exito",Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        editIdTipoEvaluacion.setText("");
        editNombre.setText("");
        editDescripcion.setText("");
    }
}

package dev.grupo5.cftapp.mantenimientos.TipoEvaluacionwbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.TipoEvaluacionWS;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;

@SuppressLint("NewApi")
public class TipoEvaluacionInsertarWbsActivity extends AppCompatActivity {

    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 49;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_insertar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(R.string.tipoevaluacioninsert + "WebService");
        verificarPermisos();

        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluacioninsert), Toast.LENGTH_LONG).show();
        }

    }
    public void insertarTipoEvaluacionWS(View v){
        String url="http://192.168.0.21/ws_tipo_evaluacion_insert.php?" +
                "nombre=" +editNombre.getText().toString()+
                "&descripcion="+editDescripcion.getText().toString();
        String mensaje=TipoEvaluacionWS.insertarTipoEvaluacion(url,this);
        if(mensaje == "No se pudo insertar"){
            Toast.makeText(this,"No se pudo insertar",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Tipo Evaluacion ingresado con exito", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {

        editNombre.setText("");
        editDescripcion.setText("");
    }
}

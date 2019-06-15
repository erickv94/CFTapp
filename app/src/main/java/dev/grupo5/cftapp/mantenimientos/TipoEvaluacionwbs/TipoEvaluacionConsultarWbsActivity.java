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
public class TipoEvaluacionConsultarWbsActivity extends AppCompatActivity {

    EditText editNombreCons;
    EditText editIdTipoEvaluacion;
    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 50;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_consultar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.tipoevaluacionread)+" webservice");

        editNombreCons = findViewById(R.id.editNomTipoEvaluacionQ);
        editIdTipoEvaluacion=findViewById(R.id.editId);
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
    public void consultarTipoEvaluacionWS(View v){
        String url="http://192.168.0.21/ws_tipo_evaluacion_query.php?" +
                "nombre="+editNombreCons.getText().toString();
        String json = TipoEvaluacionWS.consultaTipoEvaluacion(url,this);
        if (json==null){
            editDescripcion.setText("");
            editNombre.setText("");
            editIdTipoEvaluacion.setText("");
            Toast.makeText(this,"No existe la Evaluacion",Toast.LENGTH_LONG).show();
        }else {
            String[] datosJson = json.split(",");
            String[] id, nombre, concepto;
            id = datosJson[0].split(":");
            nombre = datosJson[1].split(":");
            concepto = datosJson[2].split(":");

            editIdTipoEvaluacion.setText(id[1]);
            editNombre.setText(nombre[1]);
            editDescripcion.setText(concepto[1]);
        }
    }

    public void limpiarTexto(View v) {
        editNombreCons.setText("");
        editIdTipoEvaluacion.setText("");
        editNombre.setText("");
        editDescripcion.setText("");
    }
}

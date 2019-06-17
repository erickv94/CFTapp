package dev.grupo5.cftapp.mantenimientos.TipoEvaluacionwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.TipoEvaluacionWS;
@SuppressLint("NewApi")
public class TipoEvaluacionEliminarWbsActivity extends AppCompatActivity {

    EditText editIdTipoEvaluacion;
    private static final int permiso = 52;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_eliminar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.tipoevaluaciondelete)+" webservice");
        verificarPermisos();

        editIdTipoEvaluacion=findViewById(R.id.editIdTipoEvaluacion);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluaciondelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarTipoEvaluacionWS(View v){
        //si da error con los mensajes retornar json
        String url;
        String mensaje;

        url="https://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_tipo_evaluacion_delete.php?" +
                "idtipoevaluacion="+editIdTipoEvaluacion.getText().toString();
        mensaje=TipoEvaluacionWS.eliminarTipoEvaluacion(url,this);
        if(mensaje.equals("No se pudo eliminar")){
            Toast.makeText(this,"No se pudo eliminar o no existe",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Tipo Evaluacion eliminado con exito", Toast.LENGTH_SHORT).show();
        }
    }

}

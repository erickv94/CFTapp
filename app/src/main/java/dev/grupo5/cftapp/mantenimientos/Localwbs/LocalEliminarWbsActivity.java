package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.LocalWS;

@SuppressLint("NewApi")
public class LocalEliminarWbsActivity extends AppCompatActivity {
    EditText idlocalText;
    private static final int  permiso=20;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.localdelete)+" webservice");
        verificarPermisos();
        idlocalText= findViewById(R.id.idlocal);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.localdelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarLocalServidor(View v){

        String url;
        String mensaje;
        url="http://192.168.1.5:8080/ws_local_delete.php?" +
                "idlocal="+idlocalText.getText().toString();

        mensaje= LocalWS.EliminarLocalServidor(url,this);
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}
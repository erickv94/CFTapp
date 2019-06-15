package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.LocalWS;

@SuppressLint("NewApi")
public class LocalInsertarWbsActivity extends AppCompatActivity {
    EditText codigoEdificioText;
    EditText nombreText;
    EditText codigoLocalText;
    EditText capacidadText;
    private static final int  permiso=17;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.localinsert)+" webservice");
        verificarPermisos();
        codigoEdificioText= findViewById(R.id.codigoedificio);
        nombreText= findViewById(R.id.nombrelocal);
        codigoLocalText= findViewById(R.id.codigolocal);
        capacidadText=findViewById(R.id.capacidad);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.localinsert), Toast.LENGTH_LONG).show();
        }

    }
    public void limpiar(View view){
        codigoLocalText.setText("");
        codigoEdificioText.setText("");
        nombreText.setText("");
        capacidadText.setText("");
    }

    public void insertarLocalServidor(View v){
        String url;
        String mensaje;
        url="http://192.168.1.5:8080/ws_local_insert.php?" +
                "codigoedificio=" +codigoEdificioText.getText().toString()+
                "&nombrelocal=" +nombreText.getText().toString()+
                "&codigolocal=" +codigoLocalText.getText().toString()+
                "&capacidad="+capacidadText.getText().toString();

        mensaje= LocalWS.InsertarLocalServidor(url,this);
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

}

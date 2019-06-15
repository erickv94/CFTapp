package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.databaseWS.LocalWS;

@SuppressLint("NewApi")
public class LocalConsultarWbsActivity extends AppCompatActivity {

    EditText codigoLocalTargetText;
    EditText codigoEdificioText;
    EditText nombreText;
    EditText codigoLocalText;
    EditText capacidadText;
    EditText idlocalText;
    //TextView txtJson;

    private static final int  permiso=18;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar_wbs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle(getResources().getString(R.string.localread)+" webservice");
        verificarPermisos();

        idlocalText=findViewById(R.id.idlocal);
        codigoLocalTargetText= findViewById(R.id.idbusqueda);
        codigoEdificioText= findViewById(R.id.codigoedificio);
        nombreText=findViewById(R.id.nombrelocal);
        codigoLocalText= findViewById(R.id.codigolocal);
        capacidadText=findViewById(R.id.capacidad);

        //txtJson=findViewById(R.id.consultarJson);

    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.localread), Toast.LENGTH_LONG).show();
        }

    }

    public  void consultarEquipoServidor(View v){
        //txtJson.setText("");
        String url="http://192.168.1.5:8080/ws_local_query.php?" +
                "idlocal="+codigoLocalTargetText.getText().toString();
        String json=LocalWS.ConsultarLocalServidor(url,this);

        if(json==null){
           // txtJson.setText("No existe el equipo"+codigoLocalTargetText.getText().toString());
            idlocalText.setText("");
            codigoLocalTargetText.setText("");
            codigoEdificioText.setText("");
            nombreText.setText("");
            codigoLocalText.setText("");
            capacidadText.setText("");
            Toast.makeText(this, "No existe local", Toast.LENGTH_SHORT).show();


        }
        else{
            String[] datosJson=json.split(",");//esto es lo que separa cada campo
            String [] id,codigoedificio,nombrelocal,codigolocal,capacidad;
            id=datosJson[0].split(":");//esto me separa
            codigoedificio=datosJson[1].split(":");
            nombrelocal=datosJson[2].split(":");
            codigolocal=datosJson[3].split(":");
            capacidad=datosJson[4].split(":");

            idlocalText.setText(id[1]);
            codigoEdificioText.setText(codigoedificio[1]);
            nombreText.setText(nombrelocal[1]);
            codigoLocalText.setText(codigolocal[1]);
            capacidadText.setText(capacidad[1]);

           /* //asi va cuando sea un textview
            txtJson.setText(txtJson.getText().toString()+"id"+id[1]+"\n");
            txtJson.setText(txtJson.getText().toString()+"nombre"+nombre[1]+"\n");
            txtJson.setText(txtJson.getText().toString()+"goles favor"+favor[1]+"\n");
            txtJson.setText(txtJson.getText().toString()+"goles contra"+contra[1]+"\n");
            txtJson.setText(txtJson.getText().toString()+"mundiales ganados"+mundiales[1]+"\n");
            txtJson.setText(txtJson.getText().toString()+"es europeo"+europeo[1]+"\n");
            */
        }

    }

}

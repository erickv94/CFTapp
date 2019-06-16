package dev.grupo5.cftapp.mantenimientos.Docentewbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.databaseWS.DocenteDBWS;
import dev.grupo5.cftapp.modelos.Docente;

@SuppressLint("NewApi")
public class DocenteConsultarWbsActivity extends AppCompatActivity {

    DocenteDB helper;
    EditText iddoctext;
    EditText idtipotext;
    EditText nomtext;
    EditText apellidotext;
    EditText coddoctext;
    EditText sexotext;
    TextView jsontext;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar_wbs);
        setTitle(getResources().getString(R.string.docenteread)+" webservice");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        helper = new  DocenteDB(this);
        iddoctext = findViewById(R.id.iddocente);
        idtipotext = findViewById(R.id.idtipodocente_busqueda);
        nomtext = findViewById(R.id.editNombre);
        apellidotext = findViewById(R.id.editApellido);
        coddoctext = findViewById(R.id.editCodDocente);
        sexotext = findViewById(R.id.editSexo);
        jsontext = findViewById(R.id.consultarJson);

    }

    public void consultarDocente(View v){
        Docente docente = helper.consultar(idtipotext.getText().toString());
        if(docente == null)
            Toast.makeText(this, "Docente con id tipo docente " +
                    idtipotext.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            iddoctext.setText(String.valueOf(docente.getIdDocente()));
            nomtext.setText(docente.getNombre());
            apellidotext.setText(docente.getApellidos());
            coddoctext.setText(docente.getCodDocente());
            sexotext.setText(docente.getSexo());
        }
    }

    public void consultarDocenteServidor(View v){
        jsontext.setText("");
        String url="http://192.168.1.12:8081/ws_docente_query.php?" +
                "&idtipodocente="+idtipotext.getText().toString().toUpperCase();
        String json=DocenteDBWS.consultarDocenteServidor(url,this);

        if(json==null){
            jsontext.setText("No existe el docente con id tipo "+idtipotext.getText().toString());
            iddoctext.setText("");
            idtipotext.setText("");
            nomtext.setText("");
            apellidotext.setText("");
            coddoctext.setText("");
            sexotext.setText("");
        }
        else{
            String[] datosJson=json.split(",");//esto es lo que separa cada campo
            String [] id,idtipo,nombre,apellido,coddocente,sexo;
            id=datosJson[0].split(":");//esto me separa
            idtipo=datosJson[1].split(":");
            nombre=datosJson[2].split(":");
            apellido=datosJson[3].split(":");
            coddocente=datosJson[4].split(":");
            sexo=datosJson[5].split(":");

            iddoctext.setText(id[1]);
            nomtext.setText(nombre[1]);
            apellidotext.setText(apellido[1]);
            coddoctext.setText(coddocente[1]);
            sexotext.setText(sexo[1]);

            //asi va cuando sea un textview
            jsontext.setText(jsontext.getText().toString()+"id "+id[1]+"\n");
            jsontext.setText(jsontext.getText().toString()+"id tipo "+idtipo[1]+"\n");
            jsontext.setText(jsontext.getText().toString()+"nombre "+nombre[1]+"\n");
            jsontext.setText(jsontext.getText().toString()+"apellido "+apellido[1]+"\n");
            jsontext.setText(jsontext.getText().toString()+"codigo docente "+coddocente[1]+"\n");
            jsontext.setText(jsontext.getText().toString()+"sexo "+sexo[1]+"\n");
        }
    }

    public void limpiar(View v){
        iddoctext.setText("");
        idtipotext.setText("");
        nomtext.setText("");
        apellidotext.setText("");
        coddoctext.setText("");
        sexotext.setText("");
    }
}

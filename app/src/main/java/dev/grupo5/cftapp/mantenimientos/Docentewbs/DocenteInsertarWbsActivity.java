package dev.grupo5.cftapp.mantenimientos.Docentewbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.databaseWS.DocenteDBWS;
import dev.grupo5.cftapp.modelos.Docente;

@SuppressLint("NewApi")
public class DocenteInsertarWbsActivity extends AppCompatActivity {


  //  EditText idtext;
    EditText idtipotext;
    EditText nomtext;
    EditText apellidotext;
    EditText coddoctext;
    EditText sexotext;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar_wbs);
        setTitle(getResources().getString(R.string.docenteinsert) + " webservice");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    //    idtext = (EditText) findViewById(R.id.iddocente);
        idtipotext = (EditText) findViewById(R.id.editIdTipoDocente);
        nomtext = (EditText) findViewById(R.id.editNombre);
        apellidotext = (EditText) findViewById(R.id.editApellido);
        coddoctext = (EditText) findViewById(R.id.editCodDocente);
        sexotext = (EditText) findViewById(R.id.editSexo);
    }



    public void insertarDocenteServidor(View v) {
        String url="http://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_docente_insert.php?" +
        //        "iddocente=" +idtext.getText().toString()+
                "&idtipodocente=" + idtipotext.getText().toString()+
                "&nombre=" + nomtext.getText().toString() +
                "&apellidos=" + apellidotext.getText().toString() +
                "&coddocente=" + coddoctext.getText().toString() +
                "&sexo=" + sexotext.getText().toString();
        String mensaje= DocenteDBWS.insertarDocenteServidor(url,this);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

    }

    public void limpiar(View v){
     //   idtext.setText("");
        idtipotext.setText("");
        nomtext.setText("");
        apellidotext.setText("");
        coddoctext.setText("");
        sexotext.setText("");
    }
}
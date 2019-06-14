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
import dev.grupo5.cftapp.database.DocenteDBWS;
import dev.grupo5.cftapp.modelos.Docente;

@SuppressLint("NewApi")
public class DocenteInsertarWbsActivity extends AppCompatActivity {

    DocenteDB helper;
    EditText idtext;
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

        helper = new DocenteDB(this);
        idtext = findViewById(R.id.iddocente);
        idtipotext = findViewById(R.id.editIdTipoDocente);
        nomtext = findViewById(R.id.editNombre);
        apellidotext = findViewById(R.id.editApellido);
        coddoctext = findViewById(R.id.editCodDocente);
        sexotext = findViewById(R.id.editSexo);
    }

    public void insertarDocente(View v) {
        String iddocente = idtext.getText().toString();
        String idtipodocente = idtipotext.getText().toString();
        String nombre = nomtext.getText().toString();
        String apellidos = apellidotext.getText().toString();
        String coddocente = coddoctext.getText().toString();
        String sexo = sexotext.getText().toString();
        String regInsertados;
        Docente docente = new Docente();
        docente.setIdDocente(Integer.parseInt(iddocente));
        docente.setIdTipoDocente(Integer.parseInt(idtipodocente));
        docente.setNombre(nombre);
        docente.setApellidos(apellidos);
        docente.setCodDocente(coddocente);
        docente.setSexo(sexo);
        regInsertados = helper.insertar(docente);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void insertarDocenteServidor(View v) {
        String url;
        String mensaje;

        url = "http://192.168.56.1:8081/ws_docente_insert.php?" +
                "iddocente=" + idtext.getText().toString() +
                "&idtipodocente=" + idtipotext.getText().toString() +
                "&nombre=" + nomtext.getText().toString() +
                "&apellidos=" + apellidotext.getText().toString() +
                "&cod_docente=" + coddoctext.getText().toString() +
                "&sexo=" + sexotext.getText().toString();

        mensaje = DocenteDBWS.insertarDocenteServidor(url, this);
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

    }

    public void limpiar(View v){
        idtext.setText("");
        idtipotext.setText("");
        nomtext.setText("");
        apellidotext.setText("");
        coddoctext.setText("");
        sexotext.setText("");
    }
}
package dev.grupo5.cftapp.mantenimientos.TipoDocentewbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.databaseWS.TipoDocenteWS;


@SuppressLint("NewApi")
public class TipoDocenteInsertarWbsActivity extends AppCompatActivity {
    EditText idtext;
    EditText nomtext;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_insertar_wbs);
        setTitle(getResources().getString(R.string.tipodocenteinsert)+" webservice");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idtext = findViewById(R.id.editIdTipoDocente);
        nomtext = findViewById(R.id.nombre);
    }

    public void insertarTipoDocenteServidor(View v){
        String url="http://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_tipo_docente_insert.php?" +
                "idtipodocente=" +idtext.getText().toString()+

                "&nombre=" + nomtext.getText().toString();

        String mensaje= TipoDocenteWS.insertarTipoDocenteServidor(url,this);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        idtext.setText("");
        nomtext.setText("");
    }
}

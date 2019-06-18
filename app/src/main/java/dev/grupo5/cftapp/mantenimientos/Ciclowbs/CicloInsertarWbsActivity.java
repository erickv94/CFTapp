package dev.grupo5.cftapp.mantenimientos.Ciclowbs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.databaseWS.CicloWS;
import dev.grupo5.cftapp.modelos.Ciclo;

@SuppressLint("NewApi")
public class CicloInsertarWbsActivity extends AppCompatActivity {
    CicloDB helper;
    EditText editIdCiclo;
    EditText editCiclo;
    EditText editAnio;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar_wbs);
        setTitle(getResources().getString(R.string.cicloinsert)+" webservice");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editIdCiclo=findViewById(R.id.editIdCiclo);
        editCiclo = findViewById(R.id.editCiclo);
        editAnio = findViewById(R.id.editAnio);
    }

    public void insertarCiclo(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoDocente.getText().toString());
        CicloDB cicloDB=new CicloDB(this);
        Ciclo ciclo=new Ciclo();
        String cantidad;
        ciclo.setCiclo(Integer.parseInt(editCiclo.getText().toString()));
        ciclo.setAnio(Integer.parseInt(editAnio.getText().toString()));

        cantidad=cicloDB.insertar(ciclo);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

    public void insertarCicloWS (View v){
        //Codigo para prueba en lcomp4
        //String url="http://192.168.43.62/ws_equipo_insert.php?" +
        //String url="http://192.168.1.20/ws_insertar_ciclo.php?" +
        String url="https://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_insertar_ciclo.php?" +
                "idciclo=" +editIdCiclo.getText().toString()+
                "&ciclo=" + editCiclo.getText().toString()+
                "&anio=" + editAnio.getText().toString();
        String mensaje= CicloWS.insertarCiclo(url,this);
        Toast.makeText(this,"Reultado ingresado con exito",Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdCiclo.setText("");
        editCiclo.setText("");
        editAnio.setText("");
    }

    }


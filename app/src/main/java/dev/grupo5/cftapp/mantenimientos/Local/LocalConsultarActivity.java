package dev.grupo5.cftapp.mantenimientos.Local;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.Local;

public class LocalConsultarActivity extends AppCompatActivity {
    EditText codigoLocalTargetText;
    EditText codigoEdificioText;
    EditText nombreText;
    EditText codigoLocalText;
    EditText capacidadText;
    private static final int  permiso=18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);
        setTitle(R.string.localread);

        codigoLocalTargetText= findViewById(R.id.idbusqueda);
        codigoEdificioText= findViewById(R.id.codigoedificio);
        codigoLocalText= findViewById(R.id.codigolocal);
        capacidadText=findViewById(R.id.capacidad);
        nombreText=findViewById(R.id.nombrelocal);

    }

    public void consultarLocal(View view) {
        LocalDB localDB = new LocalDB(this);
        Local local = localDB.consultar(codigoLocalTargetText.getText().toString());
        if(local!=null){
            codigoEdificioText.setText(local.getCodigoEdificio());
            codigoLocalText.setText(local.getCodigoLocal());
            capacidadText.setText(String.valueOf(local.getCapacidad()));
            nombreText.setText(local.getNombreLocal());
            return;
        }

        Toast.makeText(this,this.getResources().getString(R.string.consulta_no_existe)
                +": "+codigoLocalTargetText.getText().toString()
                ,Toast.LENGTH_SHORT).show();
    }
    }

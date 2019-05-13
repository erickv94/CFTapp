package dev.grupo5.cftapp.mantenimientos.Local;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.Local;

public class LocalInsertarActivity extends AppCompatActivity {
    EditText codigoEdificioText;
    EditText nombreText;
    EditText codigoLocalText;
    EditText capacidadText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        setTitle(R.string.localinsert);

        codigoEdificioText= findViewById(R.id.codigoedificio);
        nombreText= findViewById(R.id.nombrelocal);
        codigoLocalText= findViewById(R.id.codigolocal);
        capacidadText=findViewById(R.id.capacidad);



    }

    public void limpiar(View view){
        codigoLocalText.setText("");
        codigoEdificioText.setText("");
        nombreText.setText("");
        capacidadText.setText("");
    }

    public void insertarLocal(View view){
        LocalDB localDB= new LocalDB(this);
        Local local= new Local();
        String cantidad;
        local.setCodigoLocal(codigoLocalText.getText().toString());
        local.setNombreLocal(nombreText.getText().toString());
        local.setCodigoLocal(codigoLocalText.getText().toString());
        local.setCapacidad(Integer.parseInt(capacidadText.getText().toString()));

        cantidad =localDB.insertar(local);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

}

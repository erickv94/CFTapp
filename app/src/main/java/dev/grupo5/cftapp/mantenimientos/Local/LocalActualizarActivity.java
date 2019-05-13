package dev.grupo5.cftapp.mantenimientos.Local;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.Local;

public class LocalActualizarActivity extends AppCompatActivity {

    EditText codigoEdificioText;
    EditText nombreText;
    EditText codigoLocalText;
    EditText capacidadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        setTitle(R.string.localupdate);

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

    public void actualizarLocal(View view){
        Local local= new Local();
        String registros;
        LocalDB localDB= new LocalDB(this);
        local.setCapacidad(Integer.parseInt(capacidadText.getText().toString()));
        local.setCodigoLocal(codigoLocalText.getText().toString());
        local.setNombreLocal(nombreText.getText().toString());
        local.setCodigoEdificio(codigoEdificioText.getText().toString());

        registros=localDB.actualizar(local);
        Toast.makeText(this,registros,Toast.LENGTH_SHORT).show();
    }

}

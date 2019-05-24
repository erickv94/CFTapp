package dev.grupo5.cftapp.mantenimientos.Ciclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloActualizarActivity extends AppCompatActivity {

    EditText editIdCiclo;
    EditText editCiclo;
    EditText editAnio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_actualizar);
        setTitle(R.string.cicloupdate);

        editIdCiclo=findViewById(R.id.editIdCiclo);
        editCiclo= findViewById(R.id.editCiclo);
        editAnio= findViewById(R.id.editAnio);

    }
    public void actualizarCiclo(View view){
        Ciclo ciclo= new Ciclo();
        String registros;
        CicloDB cicloDB= new CicloDB(this);
        ciclo.setIdCiclo(Integer.parseInt(editIdCiclo.getText().toString()));
        ciclo.setCiclo(Integer.parseInt(editCiclo.getText().toString()));
        ciclo.setAnio(Integer.parseInt(editAnio.getText().toString()));
        registros=cicloDB.actualizar(ciclo);
        Toast.makeText(this,registros,Toast.LENGTH_SHORT).show();
    }



    public void limpiarTexto(View v) {
        editIdCiclo.setText("");
        editCiclo.setText("");
        editAnio.setText("");
    }
}

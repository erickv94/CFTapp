package dev.grupo5.cftapp.mantenimientos.MateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloActualizarActivity extends AppCompatActivity {
    MateriaCicloDB helper;
    EditText editIdCiclo;

    EditText editIdMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_actualizar);
        setTitle(R.string.materiacicloupdate);

        helper = new MateriaCicloDB(this);


        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        editIdMateria = (EditText) findViewById(R.id.editIdMateria);
    }

    public void actualizarMateriaCiclo (View v) {
        MateriaCiclo materiaCiclo = new MateriaCiclo();
        materiaCiclo.setIdCiclo(Integer.parseInt(editIdCiclo.getText().toString()));
        materiaCiclo.setIdMateria(Integer.parseInt(editIdMateria.getText().toString()));
        String estado = helper.actualizar(materiaCiclo);

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoMateriaCiclo(View v){
        editIdMateria.setText("");
        editIdCiclo.setText("");

    }
}

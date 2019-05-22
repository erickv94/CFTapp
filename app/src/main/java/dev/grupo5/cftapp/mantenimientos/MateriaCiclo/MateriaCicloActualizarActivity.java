package dev.grupo5.cftapp.mantenimientos.MateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;
import dev.grupo5.cftapp.database.MateriaDB;

public class MateriaCicloActualizarActivity extends AppCompatActivity {
    MateriaCicloDB helper;
    EditText editIdCiclo;
    Spinner materiaSpinner;
    EditText idmatcicloText;

    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,String> nombresmateriasMapeo = new HashMap<String, String>();

    List<Materia> materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_actualizar);
        setTitle(R.string.materiacicloupdate);

        helper = new MateriaCicloDB(this);

        materiaSpinner = (Spinner) findViewById(R.id.editIdMateria);
        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        idmatcicloText = (EditText) findViewById(R.id.idmatciclo);

        materias = new MateriaDB(this).getMaterias();
        for (Materia materia : materias){
            nombresmaterias.add(materia.getNombre());
            nombresmateriasMapeo.put(materia.getNombre(),String.valueOf(materia.getIdMateria()));
        }

        ArrayAdapter<String> adapterMateria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresmaterias);
        adapterMateria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(adapterMateria);
    }

    public void actualizarMateriaCiclo (View v) {
        helper = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = new MateriaCiclo();

        String idmat = materiaSpinner.getSelectedItem().toString();
        try {
            materiaCiclo.setIdMatCiclo(Integer.parseInt(idmatcicloText.getText().toString()));
            materiaCiclo.setIdCiclo(Integer.parseInt(editIdCiclo.getText().toString()));
            materiaCiclo.setIdMateria(Integer.parseInt(idmat));
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String estado = helper.actualizar(materiaCiclo);
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoMateriaCiclo(View v){
        idmatcicloText.setText("");
        editIdCiclo.setText("");

    }
}

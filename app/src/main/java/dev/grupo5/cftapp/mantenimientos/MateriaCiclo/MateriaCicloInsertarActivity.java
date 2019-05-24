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
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloInsertarActivity extends AppCompatActivity {
    EditText idcicloText;
    Spinner materiaSpinner;
    private static final int permiso=41;

    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,String> nombresmateriasMapeo = new HashMap<String, String>();

    List<Materia> materias = new ArrayList<Materia>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_insertar);
        setTitle(R.string.materiacicloinsert);
        idcicloText = findViewById(R.id.editIdCiclo);
        materiaSpinner = findViewById(R.id.editIdMateria);

        materias = new MateriaDB(this).getMaterias();
        for (Materia materia : materias){
            nombresmaterias.add(materia.getNombre());
            nombresmateriasMapeo.put(materia.getNombre(),String.valueOf(materia.getIdMateria()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresmaterias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(adapter);
    }

    public void limpiarTextoMateriaCiclo(View v){
        idcicloText.setText("");
    }

    public void insertarMateriaCiclo(View v){
        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = new MateriaCiclo();
        String reginsertado;

        materiaCiclo.setIdCiclo(Integer.parseInt(idcicloText.getText().toString()));
        materiaCiclo.setIdMateria(Integer.valueOf(nombresmateriasMapeo.get(materiaSpinner.getSelectedItem().toString())));

        reginsertado = materiaCicloDB.insertar(materiaCiclo);
        Toast.makeText(this,reginsertado,Toast.LENGTH_SHORT).show();
    }
}

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
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;
import dev.grupo5.cftapp.database.MateriaDB;

public class MateriaCicloActualizarActivity extends AppCompatActivity {
    MateriaCicloDB helper;
    Spinner ciclospinner;
    Spinner materiaSpinner;
    EditText idmatcicloText;
    private static final int permiso=43;


    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,Integer> nombresmateriasMapeo = new HashMap<String, Integer>();
    List<String> numerociclo = new ArrayList<String>();
    HashMap<String,Integer> numerocicloMapeo = new HashMap<String, Integer>();

    List<Materia> materias;
    List<Ciclo> ciclos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_actualizar);
        setTitle(R.string.materiacicloupdate);
        verificarPermisos();

        helper = new MateriaCicloDB(this);

        materiaSpinner = (Spinner) findViewById(R.id.editIdMateria);
        ciclospinner= findViewById(R.id.editIdCiclo);
        idmatcicloText = (EditText) findViewById(R.id.idmatciclo);

        CicloDB cicloDB = new CicloDB(this);
        ciclos = cicloDB.getCiclos();
        materias = new MateriaDB(this).getMaterias();
        for (Materia materia : materias){
            nombresmaterias.add(materia.getNombre());
            nombresmateriasMapeo.put(materia.getNombre(),materia.getIdMateria());
        }
        for (Ciclo ciclo: ciclos){
            numerociclo.add(String.valueOf(ciclo.getCiclo()));
            numerocicloMapeo.put(String.valueOf(ciclo.getCiclo()),ciclo.getIdCiclo());
        }

        ArrayAdapter<String> adapterMateria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresmaterias);
        adapterMateria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(adapterMateria);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,numerociclo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciclospinner.setAdapter(adapter);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.materiacicloupdate), Toast.LENGTH_LONG).show();
        }

    }

    public void actualizarMateriaCiclo (View v) {
        helper = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = new MateriaCiclo();

        String ciclodata = ciclospinner.getSelectedItem().toString();
        Integer idciclo = numerocicloMapeo.get(ciclodata);
        String materiadata = materiaSpinner.getSelectedItem().toString();
        Integer idmateria = nombresmateriasMapeo.get(materiadata);
        try {
            materiaCiclo.setIdMatCiclo(Integer.parseInt(idmatcicloText.getText().toString()));
            materiaCiclo.setIdCiclo(idciclo);
            materiaCiclo.setIdMateria(idmateria);
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String estado = helper.actualizar(materiaCiclo);
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoMateriaCiclo(View v){
        idmatcicloText.setText("");

    }
}

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
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloInsertarActivity extends AppCompatActivity {
    Spinner spinnerciclo;
    Spinner materiaSpinner;
    private static final int permiso=41;

    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,Integer> nombresmateriasMapeo = new HashMap<String, Integer>();
    List<String> numerosciclos =  new ArrayList<String>();
    HashMap<String,Integer> numeroscicloMapeo = new HashMap<String, Integer>();

    List<Materia> materias = new ArrayList<Materia>();
    List<Ciclo> ciclos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_insertar);
        setTitle(R.string.materiacicloinsert);
        verificarPermisos();
        spinnerciclo = findViewById(R.id.editIdCiclo);
        materiaSpinner = findViewById(R.id.editIdMateria);

        CicloDB cicloDB = new CicloDB(this);
        ciclos = cicloDB.getCiclos();
        materias = new MateriaDB(this).getMaterias();
        for (Materia materia : materias){
            nombresmaterias.add(materia.getNombre());
            nombresmateriasMapeo.put(materia.getNombre(),materia.getIdMateria());
        }

        for (Ciclo ciclo : ciclos){
            numerosciclos.add(String.valueOf(ciclo.getCiclo()));
            numeroscicloMapeo.put(String.valueOf(ciclo.getCiclo()),ciclo.getIdCiclo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresmaterias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,numerosciclos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerciclo.setAdapter(adapter2);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.materiacicloinsert), Toast.LENGTH_LONG).show();
        }

    }


    public void insertarMateriaCiclo(View v){
        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = new MateriaCiclo();

        String ciclodata = spinnerciclo.getSelectedItem().toString();
        Integer idciclo = numeroscicloMapeo.get(ciclodata);

        String materiadata = materiaSpinner.getSelectedItem().toString();
        Integer idmateria = nombresmateriasMapeo.get(materiadata);
        String reginsertado;

        materiaCiclo.setIdCiclo(idciclo);
        materiaCiclo.setIdMateria(idmateria);

        reginsertado = materiaCicloDB.insertar(materiaCiclo);
        Toast.makeText(this,reginsertado,Toast.LENGTH_SHORT).show();
    }
}

package dev.grupo5.cftapp.mantenimientos.MateriaCiclo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloConsultarActivity extends AppCompatActivity {
    EditText idmatcText;
    EditText idcText;
    Spinner materiaSpinner;
    EditText materiaText;
    EditText idtargetText;
    EditText cicloText;
    private static final int permiso=42;


    /*ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,String> nombresmateriasMapeo = new HashMap<String, String>();
    List<String> numerociclos = new ArrayList<String>();
    HashMap<String,Integer> numerociclosMapeo = new HashMap<String, Integer>();

    List<Materia> materias;
    List<Ciclo> ciclos; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_consultar);
        setTitle(R.string.materiacicloread);
        cicloText = findViewById(R.id.editIdCiclo);
        materiaText = findViewById(R.id.editIdMateria);
        idmatcText = findViewById(R.id.idmatciclo);
        idtargetText = findViewById(R.id.busqueda_mat);

    }

    public void consultarMateriaCiclo(View v){

        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = materiaCicloDB.consultar(idtargetText.getText().toString());
        if (materiaCiclo != null){
            cicloText.setText(String.valueOf(materiaCiclo.getIdCiclo()));
            materiaText.setText(String.valueOf(materiaCiclo.getIdMateria()));
            idmatcText.setText(String.valueOf(materiaCiclo.getIdMatCiclo()));
            return;
        } else
            Toast.makeText(this, "Materia Ciclo con id materia ciclo: " +idtargetText + " no existe", Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateriaCiclo(View v){
        idtargetText.setText("");
        idmatcText.setText("");
        cicloText.setText("");
        materiaText.setText(" ");
    }
}

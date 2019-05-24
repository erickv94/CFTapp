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
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloConsultarActivity extends AppCompatActivity {
    EditText idmatcText;
    EditText idcText;
    Spinner materiaSpinner;
    EditText materiaText;
    private static final int permiso=42;



    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,String> nombresmateriasMapeo = new HashMap<String, String>();

    List<Materia> materias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_consultar);
        setTitle(R.string.materiacicloread);
        idmatcText = findViewById(R.id.idmatciclo);
        idcText = findViewById(R.id.editIdCiclo);
        materiaText = findViewById(R.id.editIdMateria);
        materiaSpinner = findViewById(R.id.idbusqueda);

        materias = new MateriaDB(this).getMaterias();
        for (Materia materia : materias){
            nombresmaterias.add(materia.getNombre());
            nombresmateriasMapeo.put(materia.getNombre(),String.valueOf(materia.getIdMateria()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresmaterias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(adapter);
    }

    public void consultarMateriaCiclo(View v){
        String materiaselecionada = materiaSpinner.getSelectedItem().toString();
        String idmateria;

        idmateria = nombresmateriasMapeo.get(materiaselecionada);
        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        MateriaCiclo materiaCiclo = materiaCicloDB.consultar(idmateria);
        if (materiaCiclo != null){
            SQLiteDatabase db = DBHelper.getSingleton(this).getReadableDatabase();
            String[] campo = {"nombre"};
            String[] toWhere = {String.valueOf(materiaCiclo.getIdMateria())};
            Cursor cursor = db.query("materiaciclo", campo, "idmateria=?", toWhere, null, null, null);
            cursor.moveToFirst();
            idcText.setText(String.valueOf(materiaCiclo.getIdCiclo()));
            idmatcText.setText(String.valueOf(materiaCiclo.getIdMatCiclo()));
            materiaText.setText(cursor.getString(0));
            return;
        }
        Toast.makeText(this, "Materia Ciclo con id materia: " + materiaCiclo.getIdMateria() + " no existe", Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateriaCiclo(View v){

        idmatcText.setText("");
        idcText.setText("");
    }
}

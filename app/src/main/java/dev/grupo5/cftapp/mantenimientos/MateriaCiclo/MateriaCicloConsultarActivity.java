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


    ArrayList<String> nombresmaterias = new ArrayList<String>();
    HashMap<String,String> nombresmateriasMapeo = new HashMap<String, String>();

    List<Materia> materias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_consultar);
        setTitle(R.string.materiacicloread);
        idmatcText = findViewById(R.id.idbusqueda);
        idcText = findViewById(R.id.editIdCiclo);
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

    public void consultarMateriaCiclo(View v){
        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        String idmatciclo = idmatcText.getText().toString();
        MateriaCiclo materiaCiclo = materiaCicloDB.consultar(idmatciclo);
        if (materiaCiclo != null){
            SQLiteDatabase db = DBHelper.getSingleton(this).getReadableDatabase();
            String[] campo = {"nombre"};
            String[] toWhere = {String.valueOf(materiaCiclo.getIdMateria())};
            Cursor cursor = db.query("materiaciclo", campo, "idmateria=?", toWhere, null, null, null);
            cursor.moveToFirst();
            materiaCiclo.setIdCiclo(Integer.valueOf(idcText.getText().toString()));
            materiaCiclo.setIdMatCiclo(Integer.valueOf(idmatciclo));
            materiaText.setText(materiaSpinner.getSelectedItem().toString());
            return;
        }
        Toast.makeText(this, "Materia Ciclo con id: " + materiaCiclo.getIdMatCiclo() + " no existe", Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateriaCiclo(View v){
        idmatcText.setText("");
    }
}

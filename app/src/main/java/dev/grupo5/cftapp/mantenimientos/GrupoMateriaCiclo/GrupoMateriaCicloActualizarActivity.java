package dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo;

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
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;
import dev.grupo5.cftapp.modelos.TipoGrupo;

public class GrupoMateriaCicloActualizarActivity extends AppCompatActivity {
    GrupoMateriaCicloDB grupoMateriaCicloDB;
    Spinner spinnerdocente;
    Spinner spinnermatc;
    Spinner spinnertipo;
    EditText idText;
    EditText codText;
    EditText cantidadText;
    EditText capacidadText;

    List<String> nombredocente = new ArrayList<String>();
    HashMap<String,Integer> nombredocenteMapeo = new HashMap<String, Integer>();
    List<String> informacionmatc = new ArrayList<String>();
    HashMap<String,Integer> informacionmatcMapeo = new HashMap<String, Integer>();
    List<String> nombretipo = new ArrayList<String>();
    HashMap<String,Integer> nombretipoMapeo = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_actualizar);
        setTitle(R.string.grupomateriacicloupdate);
        spinnertipo = findViewById(R.id.grupo_tipo_id);
        idText = findViewById(R.id.idgrupo);
        spinnerdocente = findViewById(R.id.grupo_docente_id);
        spinnermatc = findViewById(R.id.grupo_materia_id);
        codText = findViewById(R.id.codgrupo);
        cantidadText = findViewById(R.id.cantidad);
        capacidadText = findViewById(R.id.capacidad);

        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        DocenteDB docenteDB = new DocenteDB(this);

        List<TipoGrupo> tipoGrupos = new TipoGrupoDB(this).getTipoGrupos();
        HashMap<Integer, String> matciclos = materiaCicloDB.getMateriaCiclos();
        List<Docente> docentes = docenteDB.getDocentes();

        for (TipoGrupo tipoGrupo : tipoGrupos) {
            nombretipo.add(tipoGrupo.getNombre());
            nombretipoMapeo.put(tipoGrupo.getNombre(), tipoGrupo.getIdTipoGrupo());
        }

        for (HashMap.Entry<Integer, String> entry : matciclos.entrySet()) {
            informacionmatc.add(entry.getValue());
            informacionmatcMapeo.put(entry.getValue(), entry.getKey());
        }
        for (Docente docente : docentes) {
            nombredocente.add(docente.getNombre()+" "+docente.getApellidos());
            nombredocenteMapeo.put(docente.getNombre()+" "+docente.getApellidos(),docente.getIdDocente());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombredocente);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdocente.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, informacionmatc);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermatc.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombretipo);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipo.setAdapter(adapter3);

    }

    public void actualizarGrupoMatCiclo(View v){
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);

        int idgrupo = Integer.parseInt(idText.getText().toString());
        String tipodata = spinnertipo.getSelectedItem().toString();
        Integer idtipo = nombretipoMapeo.get(tipodata);
        String docentedata = spinnerdocente.getSelectedItem().toString();
        Integer iddocente = nombredocenteMapeo.get(docentedata);

        String matciclodata = spinnermatc.getSelectedItem().toString();
        Integer idmatciclo = informacionmatcMapeo.get(matciclodata);

        GrupoMateriaCiclo grupoMateriaCiclo = new GrupoMateriaCiclo();
        grupoMateriaCiclo.setIdGrupo(idgrupo);
        grupoMateriaCiclo.setIdDocente(iddocente);
        grupoMateriaCiclo.setIdMatCiclo(idmatciclo);
        grupoMateriaCiclo.setIdTipoGrupo(idtipo);
        grupoMateriaCiclo.setCantidadAlumnos(Integer.parseInt(cantidadText.getText().toString()));
        grupoMateriaCiclo.setCodgrupo(codText.getText().toString());
        grupoMateriaCiclo.setCapacidadAlumnos(Integer.parseInt(capacidadText.getText().toString()));

        String resultado = grupoMateriaCicloDB.actualizar(grupoMateriaCiclo);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        idText.setText("");
        codText.setText("");
        cantidadText.setText("");
        capacidadText.setText("");
    }
}

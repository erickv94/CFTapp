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
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;
import dev.grupo5.cftapp.modelos.TipoGrupo;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.database.DocenteDB;

public class GrupoMateriaCicloInsertarActivity extends AppCompatActivity {
    GrupoMateriaCicloDB grupoMateriaCicloDB;
    Spinner docentespinner;
    Spinner matciclospinner;
    Spinner tipospinner;
    EditText codgrupoText;
    EditText cantidadText;
    EditText capacidadText;

    List<String> nombredocente = new ArrayList<String>();
    HashMap<String,Integer> nombredocenteMapeo = new HashMap<String, Integer>();
    ArrayList<String> informacionmatciclo = new ArrayList<String>();
    HashMap<String, Integer> informacionmatMapeo = new HashMap<String, Integer>();
    ArrayList<String> nombretipos = new ArrayList<String>();
    HashMap<String, Integer> nombretiposMapeo = new HashMap<String, Integer>();

    List<TipoGrupo> tipoGrupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_insertar);
        setTitle(R.string.grupomateriacicloinsert);

        docentespinner = findViewById(R.id.grupo_docente_id);
        matciclospinner = findViewById(R.id.grupo_materia_id);
        tipospinner = findViewById(R.id.grupo_tipo_id);
        codgrupoText = findViewById(R.id.codgrupo);
        cantidadText = findViewById(R.id.cantidad);
        capacidadText = findViewById(R.id.capacidad);

        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);
        DocenteDB docenteDB = new DocenteDB(this);
        TipoGrupoDB tipoGrupoDB = new TipoGrupoDB(this);
        tipoGrupos = tipoGrupoDB.getTipoGrupos();
        HashMap<Integer,String> materiaCiclos = materiaCicloDB.getMateriaCiclos();
        List<Docente> docentes = docenteDB.getDocentes();

        for (TipoGrupo tipoGrupo : tipoGrupos) {
            nombretipos.add(tipoGrupo.getNombre());
            nombretiposMapeo.put(tipoGrupo.getNombre(), tipoGrupo.getIdTipoGrupo());
        }

        for (HashMap.Entry<Integer,String> entry : materiaCiclos.entrySet()){
            informacionmatciclo.add(entry.getValue());
            informacionmatMapeo.put(entry.getValue(),entry.getKey());
        }

        for (Docente docente : docentes){
            nombredocente.add(docente.getNombre()+" "+docente.getApellidos());
            nombredocenteMapeo.put(docente.getNombre()+" "+docente.getApellidos(),docente.getIdDocente());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombredocente);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docentespinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, informacionmatciclo);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matciclospinner.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombretipos);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipospinner.setAdapter(adapter3);
    }

    public void insertarGrupoMatCiclo(View v) {
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);

        String docentedata = docentespinner.getSelectedItem().toString();
        Integer iddocente = nombredocenteMapeo.get(docentedata);

        String matciclodata = matciclospinner.getSelectedItem().toString();
        Integer idmatciclo = informacionmatMapeo.get(matciclodata);
        String tipodata = tipospinner.getSelectedItem().toString();
        Integer idtipo = nombretiposMapeo.get(tipodata);

        GrupoMateriaCiclo grupoMateriaCiclo = new GrupoMateriaCiclo();
        grupoMateriaCiclo.setIdDocente(iddocente);
        grupoMateriaCiclo.setIdMatCiclo(idmatciclo);
        grupoMateriaCiclo.setIdTipoGrupo(idtipo);
        grupoMateriaCiclo.setCantidadAlumnos(Integer.parseInt(cantidadText.getText().toString()));
        grupoMateriaCiclo.setCodgrupo(codgrupoText.getText().toString());
        grupoMateriaCiclo.setCapacidadAlumnos(Integer.parseInt(capacidadText.getText().toString()));

        String registro = grupoMateriaCicloDB.insertar(grupoMateriaCiclo);
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        codgrupoText.setText("");
        cantidadText.setText("");
        capacidadText.setText("");
    }
}
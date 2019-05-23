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

public class GrupoMateriaCicloConsultarActivity extends AppCompatActivity {
    GrupoMateriaCicloDB grupoMateriaCicloDB;
    Spinner spinnerdocente;
    Spinner spinnermatc;
    Spinner spinnertipo;
    EditText idtargetText;
    EditText idText;
    EditText docentetext;
    EditText matcText;
    EditText tipoText;
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
        setContentView(R.layout.activity_grupo_materia_ciclo_consultar);
        setTitle(R.string.grupomateriacicloread);
        spinnertipo = findViewById(R.id.busquedatipo);
        idtargetText = findViewById(R.id.busquedagrupo);
        idText = findViewById(R.id.idgrupo);
        spinnerdocente = findViewById(R.id.grupo_docente_id);
        spinnermatc = findViewById(R.id.grupo_materia_id);
        docentetext = findViewById(R.id.iddocente);
        matcText = findViewById(R.id.idmatciclo);
        tipoText = findViewById(R.id.idtipogrupo);
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
            nombredocenteMapeo.put(docente.getNombre()+" "+docente.getApellidos(), docente.getIdDocente());
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

    public void consultarGrupoMatCiclo(View v){
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);

        String idgrupo = idtargetText.getText().toString();

        String tipodata = spinnertipo.getSelectedItem().toString();
        Integer idtipo = nombretipoMapeo.get(tipodata);
        GrupoMateriaCiclo grupoMateriaCiclo = grupoMateriaCicloDB.consultar(String.valueOf(idtipo),idgrupo);
        if (grupoMateriaCiclo!=null){
            matcText.setText(spinnermatc.getSelectedItem().toString());
            tipoText.setText(spinnertipo.getSelectedItem().toString());
            docentetext.setText(spinnerdocente.getSelectedItem().toString());
            idText.setText(String.valueOf(grupoMateriaCiclo.getIdGrupo()));
            codText.setText(grupoMateriaCiclo.getCodgrupo());
            capacidadText.setText(String.valueOf(grupoMateriaCiclo.getCapacidadAlumnos()));
            cantidadText.setText(String.valueOf(grupoMateriaCiclo.getCantidadAlumnos()));
        }
        Toast.makeText(this,"Esta consulta no existe",Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        idtargetText.setText("");
    }
}

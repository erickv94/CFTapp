package dev.grupo5.cftapp.mantenimientos.Testigo;

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
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.TestigoDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Estudiante;
import dev.grupo5.cftapp.modelos.Testigo;

public class TestigoInsertarActivity extends AppCompatActivity {
    Spinner spinnerEstudiante;
    Spinner spinnerTramites;
    EditText justificacionText;

    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testigo_insertar);
        setTitle(R.string.testigoinsert);

        spinnerEstudiante= findViewById(R.id.estudiante_testigo);
        spinnerTramites= findViewById(R.id.tramite_testigo);
        justificacionText=findViewById(R.id.justificacion);

        EstudianteDB estudianteDB= new EstudianteDB(this);
        TramiteDB tramiteDB= new TramiteDB(this);



        List<Estudiante> estudiantes=estudianteDB.getEstudiantes();
        HashMap<Integer,String> tramites= tramiteDB.getTramites();


        for(Estudiante estudiante: estudiantes){
            nombresEstudiantes.add(estudiante.getNombres()+" "+estudiante.getApellidos());
            nombresEstudiantesMapeo.put(estudiante.getNombres()+" "+estudiante.getApellidos(),estudiante.getIdEstudiante());
        }


        for (HashMap.Entry<Integer, String> entry : tramites.entrySet()) {
            informacionTramite.add(entry.getValue());
            informacionTramiteMapeo.put(entry.getValue(),entry.getKey());
        }


        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresEstudiantes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstudiante.setAdapter(adapter);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,informacionTramite);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTramites.setAdapter(adapter2);


    }
    public void insertarTestigo(View view){
        TestigoDB testigoDB= new TestigoDB(this);

        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String tramiteData=spinnerTramites.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        String justificacion= justificacionText.getText().toString();


        Testigo testigo= new Testigo();
        testigo.setIdEstudiante(idEstudiante);
        testigo.setIdTramite(idtramite);
        testigo.setJustificacion(justificacion);

        String respuesta =testigoDB.insertar(testigo);


        Toast.makeText(this,respuesta,Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View view){
        justificacionText.setText("");
    }
}

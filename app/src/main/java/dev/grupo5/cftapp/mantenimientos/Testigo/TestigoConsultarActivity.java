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
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.TestigoDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Estudiante;
import dev.grupo5.cftapp.modelos.Testigo;
import dev.grupo5.cftapp.modelos.Tramite;

public class TestigoConsultarActivity extends AppCompatActivity {
    //parametros
    Spinner spinnerEstudiante;
    Spinner spinnerTramite ;

    //salidas
    EditText nombreEstudianteText;
    EditText informacionTramiteText;
    EditText justificacionText;
    EditText idText;
    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testigo_consultar);
        setTitle(R.string.testigoread);

        spinnerEstudiante= findViewById(R.id.busqueda1);
        spinnerTramite=findViewById(R.id.busqueda2);
        idText=findViewById(R.id.idtestigo);
        nombreEstudianteText=findViewById(R.id.estudiante);
        informacionTramiteText=findViewById(R.id.tramite);
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
        spinnerTramite.setAdapter(adapter2);


    }

    public void consultarTestigo(View view){
        TestigoDB testigoDB= new TestigoDB(this);
        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        Testigo testigo=testigoDB.consultar(String.valueOf(idEstudiante),String.valueOf(idtramite));
        if(testigo!=null) {
            idText.setText(String.valueOf(testigo.getIdTestigo()));
            nombreEstudianteText.setText(estudianteData);
            informacionTramiteText.setText(tramiteData);
            justificacionText.setText(testigo.getJustificacion());
            return;
        }
        Toast.makeText(this,R.string.consulta_no_existe,Toast.LENGTH_SHORT).show();
    }
}

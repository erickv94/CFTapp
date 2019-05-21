package dev.grupo5.cftapp.mantenimientos.EstadoEvaluacion;

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
import dev.grupo5.cftapp.database.EstadoEvaluacionDB;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.modelos.EstadoEvaluacion;
import dev.grupo5.cftapp.modelos.Estudiante;
import dev.grupo5.cftapp.modelos.Evaluacion;

public class EstadoEvaluacionConsultarActivity extends AppCompatActivity {

    //parametros
    Spinner spinnerEstudiante;
    Spinner spinnerEvaluacion ;

    EditText notaText;
    EditText estudianteText;
    EditText evaluacionText;

    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> nombresEvaluaciones= new ArrayList<String>();
    HashMap<String,Integer> nombresEvaluacionesMapeo= new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_evaluacion_consultar);
        setTitle(R.string.estadoread);

        spinnerEstudiante= findViewById(R.id.busqueda1);
        spinnerEvaluacion=findViewById(R.id.busqueda2);

        notaText= findViewById(R.id.nota);
        estudianteText=findViewById(R.id.idestudiante);
        evaluacionText=findViewById(R.id.idevaluacion);

        EstudianteDB estudianteDB= new EstudianteDB(this);
        List<Estudiante> estudiantes=estudianteDB.getEstudiantes();

        EvaluacionDB evaluacionDB= new EvaluacionDB(this);
        List<Evaluacion> evaluaciones= evaluacionDB.getEvaluaciones();

        for(Estudiante estudiante: estudiantes){
            nombresEstudiantes.add(estudiante.getNombres()+" "+estudiante.getApellidos());
            nombresEstudiantesMapeo.put(estudiante.getNombres()+" "+estudiante.getApellidos(),estudiante.getIdEstudiante());
        }


        for (Evaluacion evaluacion:evaluaciones){
            nombresEvaluaciones.add(evaluacion.getNombreEvaluacion());
            nombresEvaluacionesMapeo.put(evaluacion.getNombreEvaluacion(),evaluacion.getIdEvaluacion());
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresEstudiantes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstudiante.setAdapter(adapter);

        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresEvaluaciones);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvaluacion.setAdapter(adapter2);

    }

    public void consultarEstado(View view){
        EstadoEvaluacionDB estadoEvaluacionDB= new EstadoEvaluacionDB(this);

        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String evaluacionData=spinnerEvaluacion.getSelectedItem().toString();
        Integer idevaluacion= nombresEvaluacionesMapeo.get(evaluacionData);

        EstadoEvaluacion estadoEvaluacion=estadoEvaluacionDB.consultar(String.valueOf(idEstudiante),String.valueOf(idevaluacion));
        if(estadoEvaluacion!=null) {
            estudianteText.setText(spinnerEstudiante.getSelectedItem().toString());
            evaluacionText.setText(spinnerEvaluacion.getSelectedItem().toString());
            notaText.setText(String.valueOf(estadoEvaluacion.getNota()));

            return;
        }
        Toast.makeText(this,R.string.consulta_no_existe,Toast.LENGTH_SHORT).show();


    }


}

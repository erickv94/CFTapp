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

public class EstadoEvaluacionActualizarActivity extends AppCompatActivity {

    //parametros
    Spinner spinnerEstudiante;
    Spinner spinnerEvaluacion ;

    EditText notaText;
    EditText idText;

    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> nombresEvaluaciones= new ArrayList<String>();
    HashMap<String,Integer> nombresEvaluacionesMapeo= new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_evaluacion_actualizar);
        setTitle(R.string.estadoupdate);

        spinnerEstudiante= findViewById(R.id.estudiante_estado);
        spinnerEvaluacion=findViewById(R.id.evaluacion_estado);

        notaText= findViewById(R.id.nota);
        idText= findViewById(R.id.idestado);

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

    public void actualizarEstado(View view){
        EstadoEvaluacionDB estadoEvaluacionDB= new EstadoEvaluacionDB(this);
        EstadoEvaluacion estad= new EstadoEvaluacion();


        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String evaluacionData=spinnerEvaluacion.getSelectedItem().toString();
        Integer idEvaluacion= nombresEvaluacionesMapeo.get(evaluacionData);

        estad.setIdEvaluacion(idEvaluacion);
        estad.setIdEstudiante(idEstudiante);

        if(!notaText.getText().toString().isEmpty()) {
            estad.setNota(Double.valueOf(notaText.getText().toString()));
        }

        try {
            estad.setIdEstado(Integer.valueOf(idText.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String resultado=estadoEvaluacionDB.actualizar(estad);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }

    public void limpiar(View view){

    }
}

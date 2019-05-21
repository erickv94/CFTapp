package dev.grupo5.cftapp.mantenimientos.DetalleSolicitud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DetalleSolicitudDB;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.DetalleSolicitud;
import dev.grupo5.cftapp.modelos.Estudiante;

public class DetalleSolicitudActualizarActivity extends AppCompatActivity {

    Spinner spinnerTramite;
    Spinner spinnerEstudiante;
    EditText motivoText;
    CheckBox rechazadoCheck;





    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_actualizar);
        setTitle(R.string.detallesolicitudupdate);

        //initializing
        spinnerTramite= findViewById(R.id.detalle_sol_tramite);
        spinnerEstudiante= findViewById(R.id.detalle_sol_estudiante);
        motivoText= findViewById(R.id.detalle_sol_motivo);
        rechazadoCheck= findViewById(R.id.checkBox);

        //filling spinners
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

    public void actualizarDetSol(View view){


        DetalleSolicitudDB detalleSolicitudDB= new DetalleSolicitudDB(this);
        DetalleSolicitud detalleSolicitud= new DetalleSolicitud();


        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        detalleSolicitud.setIdTramite(idtramite);
        detalleSolicitud.setIdEstudiante(idEstudiante);
        detalleSolicitud.setMotivo(motivoText.getText().toString());
        detalleSolicitud.setEsRechazado(rechazadoCheck.isChecked());

        String resultado=detalleSolicitudDB.actualizar(detalleSolicitud);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }

    public void limpiar(View view){
        rechazadoCheck.setChecked(false);
        motivoText.setText("");
    }

}

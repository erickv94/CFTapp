package dev.grupo5.cftapp.mantenimientos.DetalleRevision;

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
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.DetalleRevisionDB;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.DetalleRevision;
import dev.grupo5.cftapp.modelos.Estudiante;

public class DetalleRevisionConsultarActivity extends AppCompatActivity {
    Spinner spinnerTramite;
    Spinner spinnerEstudiante;
    EditText tramiteText;
    EditText estudianteText;
    EditText motivoText;
    EditText asistenciaText;
    private static final int permiso = 66;

    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_revision_consultar);
        setTitle(R.string.detallerevisionread);
        verificarPermisos();

        //initializing
        spinnerTramite= findViewById(R.id.busqueda1);
        spinnerEstudiante= findViewById(R.id.busqueda2);

        tramiteText= findViewById(R.id.idtramite);
        estudianteText= findViewById(R.id.idestudiante);
        motivoText= findViewById(R.id.detalle_rev_motivo);
        asistenciaText= findViewById(R.id.asistencia_rev);
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

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.detallerevisionread), Toast.LENGTH_LONG).show();
        }

    }


    public void consultarDetalleRev(View view){


        DetalleRevisionDB detalleRevisionDB= new DetalleRevisionDB(this);

        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        DetalleRevision detalleRevision=detalleRevisionDB.consultar(String.valueOf(idEstudiante),String.valueOf(idtramite));

        if(detalleRevision!=null) {
            tramiteText.setText(spinnerTramite.getSelectedItem().toString());
            estudianteText.setText(spinnerEstudiante.getSelectedItem().toString());
            motivoText.setText(detalleRevision.getMotivo().toString());
            asistenciaText.setText(detalleRevision.getAsistencia()?"Asistio":"No asistio");
            return;
        }
        Toast.makeText(this,R.string.consulta_no_existe,Toast.LENGTH_SHORT).show();

    }



}

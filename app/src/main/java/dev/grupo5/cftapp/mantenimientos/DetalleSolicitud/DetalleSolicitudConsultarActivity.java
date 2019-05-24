package dev.grupo5.cftapp.mantenimientos.DetalleSolicitud;

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
import dev.grupo5.cftapp.database.DetalleSolicitudDB;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.DetalleSolicitud;
import dev.grupo5.cftapp.modelos.Estudiante;

public class DetalleSolicitudConsultarActivity extends AppCompatActivity {

    Spinner spinnerTramite;
    Spinner spinnerEstudiante;
    EditText tramiteText;
    EditText estudianteText;
    EditText motivoText;
    EditText rechazadoText;
    private static final int permiso = 70;





    //mapeo para los arrayadapters
    List<String> nombresEstudiantes= new ArrayList<String>();
    HashMap<String,Integer> nombresEstudiantesMapeo= new HashMap<String, Integer>();

    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_consultar);
        setTitle(R.string.detallesolicitudread);
        verificarPermisos();
        //initializing
        spinnerTramite= findViewById(R.id.busqueda1);
        spinnerEstudiante= findViewById(R.id.busqueda2);

        tramiteText= findViewById(R.id.idtramite);
        estudianteText= findViewById(R.id.idestudiante);
        motivoText= findViewById(R.id.detalle_sol_motivo);
        rechazadoText= findViewById(R.id.rechazado_sol);
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
                    +getResources().getString(R.string.detallesolicitudread), Toast.LENGTH_LONG).show();
        }

    }

    public void consultarDetalleSol(View view){


        DetalleSolicitudDB detalleSolicitudDB= new DetalleSolicitudDB(this);

        String estudianteData=spinnerEstudiante.getSelectedItem().toString();
        Integer idEstudiante= nombresEstudiantesMapeo.get(estudianteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        DetalleSolicitud detalleSolicitud=detalleSolicitudDB.consultar(String.valueOf(idEstudiante),String.valueOf(idtramite));

        if(detalleSolicitud!=null) {
            tramiteText.setText(spinnerTramite.getSelectedItem().toString());
            estudianteText.setText(spinnerEstudiante.getSelectedItem().toString());
            motivoText.setText(detalleSolicitud.getMotivo().toString());
            rechazadoText.setText(detalleSolicitud.getEsRechazado()?"Ha sido rechazado":"No ha sido rechazado");
            return;
        }
        Toast.makeText(this,R.string.consulta_no_existe,Toast.LENGTH_SHORT).show();

    }



}

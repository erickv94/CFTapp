package dev.grupo5.cftapp.mantenimientos.DetalleDocente;

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
import dev.grupo5.cftapp.database.DetalleDocenteDB;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.DetalleDocente;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.RolRevision;

public class DetalleDocenteConsultarActivity extends AppCompatActivity {

    Spinner spinnerRol;
    Spinner spinnerDocente;
    Spinner spinnerTramite;

    EditText rolTxt;
    EditText docenteTxt;
    EditText tramiteTxt;
    EditText asistenciaTxt;
    EditText motivoInasistenciaTxt;


    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();

    List<String> nombresDocentes= new ArrayList<String>();
    HashMap<String,Integer> nombresDocentesMapeo= new HashMap<String, Integer>();

    List<String> nombresRoles= new ArrayList<String>();
    HashMap<String,Integer> nombresRolesMapeo= new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_consultar);
        setTitle(R.string.detalledocenteread);
         spinnerRol= findViewById(R.id.busqueda1);
         spinnerDocente = findViewById(R.id.busqueda2);
         spinnerTramite= findViewById(R.id.busqueda3);

         docenteTxt=findViewById(R.id.docente);
         tramiteTxt=findViewById(R.id.tramite);
         rolTxt=findViewById(R.id.rol);
         asistenciaTxt= findViewById(R.id.asistencia);
         motivoInasistenciaTxt= findViewById(R.id.motivo);

        TramiteDB tramiteDB= new TramiteDB(this);
        HashMap<Integer,String> tramites= tramiteDB.getTramites();

        DocenteDB docenteDB= new DocenteDB(this);
        List<Docente> docentes= docenteDB.getDocentesListado();

        RolRevisionDB rolRevisionDB = new RolRevisionDB(this);
        List<RolRevision> roles= rolRevisionDB.getRoles();



        for (HashMap.Entry<Integer, String> entry : tramites.entrySet()) {
            informacionTramite.add(entry.getValue());
            informacionTramiteMapeo.put(entry.getValue(),entry.getKey());
        }

        for(Docente docente: docentes){
            nombresDocentes.add(docente.getNombre()+" "+docente.getApellidos());
            nombresDocentesMapeo.put(docente.getNombre()+" "+docente.getApellidos(),docente.getIdDocente());
        }

        for(RolRevision rol:roles) {
            nombresRoles.add(rol.getNombre());
            nombresRolesMapeo.put(rol.getNombre(), rol.getIdRol());
        }

        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresRoles);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRol.setAdapter(adapter1);


        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,informacionTramite);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTramite.setAdapter(adapter2);

        ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresDocentes);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocente.setAdapter(adapter3);


    }
    public void consultarDetalleDocente(View view){

        DetalleDocenteDB detalleDocenteDB= new DetalleDocenteDB(this);
        String docenteData=spinnerDocente.getSelectedItem().toString();
        Integer idDocente= nombresDocentesMapeo.get(docenteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        String rolData=spinnerRol.getSelectedItem().toString();
        Integer idRol= nombresRolesMapeo.get(rolData);

        DetalleDocente detalleDocente =detalleDocenteDB.consultar(String.valueOf(idDocente),
                String.valueOf(idtramite),String.valueOf(idRol));
        if(detalleDocente!=null) {

            docenteTxt.setText(spinnerDocente.getSelectedItem().toString());
            tramiteTxt.setText(spinnerTramite.getSelectedItem().toString());
            rolTxt.setText(spinnerRol.getSelectedItem().toString());
            asistenciaTxt.setText(detalleDocente.getAsistencia()?"Asitio":"No asistio");
            motivoInasistenciaTxt.setText(detalleDocente.getMotivoInasistencia());
            return;
        }
        Toast.makeText(this,R.string.consulta_no_existe,Toast.LENGTH_SHORT).show();


    }

}

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
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.DetalleDocenteDB;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.DetalleDocente;

import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.RolRevision;

public class DetalleDocenteInsertarActivity extends AppCompatActivity {
    Spinner spinnerRol;
    Spinner spinnerDocente;
    Spinner spinnerTramite;

    CheckBox asisCheckBox;
    EditText motivoTxt;
    private static final int  permiso=29;


    List<String> informacionTramite= new ArrayList<String>();
    HashMap<String,Integer> informacionTramiteMapeo= new HashMap<String, Integer>();

    List<String> nombresDocentes= new ArrayList<String>();
    HashMap<String,Integer> nombresDocentesMapeo= new HashMap<String, Integer>();

    List<String> nombresRoles= new ArrayList<String>();
    HashMap<String,Integer> nombresRolesMapeo= new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_insertar);
        setTitle(R.string.detalledocenteinsert);
        verificarPermisos();

        spinnerRol= findViewById(R.id.idrol);
        spinnerDocente=findViewById(R.id.iddocente);
        spinnerTramite=findViewById(R.id.idtramite);

        asisCheckBox=findViewById(R.id.asistencia);
        motivoTxt=findViewById(R.id.motivo);

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
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocente.setAdapter(adapter3);




    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.detalledocenteinsert), Toast.LENGTH_LONG).show();
        }

    }

    public void crearDetalleDocente(View view){

        DetalleDocenteDB detalleDocenteDB= new DetalleDocenteDB(this);
        DetalleDocente detal= new DetalleDocente();

        String docenteData=spinnerDocente.getSelectedItem().toString();
        Integer idDocente= nombresDocentesMapeo.get(docenteData);

        String tramiteData=spinnerTramite.getSelectedItem().toString();
        Integer idtramite= informacionTramiteMapeo.get(tramiteData);

        String rolData=spinnerRol.getSelectedItem().toString();
        Integer idRol= nombresRolesMapeo.get(rolData);

        detal.setIdDocente(idDocente);
        detal.setIdTramite(idtramite);
        detal.setIdRol(idRol);

        detal.setAsistencia(asisCheckBox.isChecked());
        detal.setMotivoInasistencia(motivoTxt.getText().toString());

        String respuesta =detalleDocenteDB.insertar(detal);
        respuesta= (respuesta==null)?getResources().getString(R.string.already_exist):respuesta;
        Toast.makeText(this,respuesta,Toast.LENGTH_SHORT).show();

    }

    public void limpiar(View view){
        motivoTxt.setText("");
        asisCheckBox.setChecked(false);
    }


}

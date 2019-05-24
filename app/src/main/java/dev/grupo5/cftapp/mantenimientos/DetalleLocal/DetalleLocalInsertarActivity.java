package dev.grupo5.cftapp.mantenimientos.DetalleLocal;

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
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.database.DetalleLocalDB;
import dev.grupo5.cftapp.modelos.DetalleLocal;
import dev.grupo5.cftapp.modelos.Evaluacion;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.database.EvaluacionDB;

public class DetalleLocalInsertarActivity extends AppCompatActivity {
    DetalleLocalDB detalleLocalDB;
    Spinner localspinner;
    Spinner evaluacionespinner;
    EditText cantidadText;
    private static final int permiso = 57;

    ArrayList<String> nombrelocales = new ArrayList<String>();
    HashMap<String,Integer> nombrelocalesMapeo = new HashMap<String, Integer>();
    ArrayList<String> informacionevaluaciones = new ArrayList<String>();
    HashMap<String,Integer> informacionevaluacionesMapeo = new HashMap<String, Integer>();

    List<Local> locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local_insertar);
        setTitle(R.string.detallelocalinsert);
        verificarPermisos();
        localspinner = (Spinner) findViewById(R.id.local_detalle);
        evaluacionespinner = (Spinner) findViewById(R.id.evaluacion_detalle);
        cantidadText = findViewById(R.id.cantidad);

        locales = new LocalDB(this).getLocales();
        EvaluacionDB evaluacionDB = new EvaluacionDB(this);
        HashMap<Integer,String> evaluaciones = evaluacionDB.getEvaluaciones();

        for (Local local : locales){
            nombrelocales.add(local.getNombreLocal());
            nombrelocalesMapeo.put(local.getNombreLocal(),local.getIdLocal());
        }

        for (HashMap.Entry<Integer,String> entry : evaluaciones.entrySet()){
            informacionevaluaciones.add(entry.getValue());
            informacionevaluacionesMapeo.put(entry.getValue(),entry.getKey());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombrelocales);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localspinner.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,informacionevaluaciones);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        evaluacionespinner.setAdapter(adapter2);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.detallelocalinsert), Toast.LENGTH_LONG).show();
        }

    }

    public void insertarDetalleLocal(View v){
        detalleLocalDB = new DetalleLocalDB(this);

        String localdata = localspinner.getSelectedItem().toString();
        Integer idLocal = nombrelocalesMapeo.get(localdata);

        String evaluaciondata = evaluacionespinner.getSelectedItem().toString();
        Integer idEvaluacion = informacionevaluacionesMapeo.get(evaluaciondata);

        DetalleLocal detalleLocal = new DetalleLocal();
        detalleLocal.setIdLocal(idLocal);
        detalleLocal.setIdEvaluacion(idEvaluacion);
        detalleLocal.setCantidadAlumnos(Integer.parseInt(cantidadText.getText().toString()));

        String registro = detalleLocalDB.insertar(detalleLocal);
        if (registro == null)
            Toast.makeText(this,"Este registro existe",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();
    }

    public void limpiarDetalleLocal(View v){
        cantidadText.setText("");
    }
}

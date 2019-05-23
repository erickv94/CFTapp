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
import dev.grupo5.cftapp.database.DetalleLocalDB;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.modelos.DetalleLocal;

public class DetalleLocalConsultarActivity extends AppCompatActivity {
    DetalleLocalDB detalleLocalDB;
    Spinner spinnerlocal;
    Spinner spinnerevaluacion;
    EditText localtext;
    EditText evaluacionText;
    EditText cantidadText;


    List<String> nombrelocales = new ArrayList<String>();
    HashMap<String,Integer> nombreslocalesMapeo = new HashMap<String, Integer>();
    List<String> informacionevaluacion = new ArrayList<String>();
    HashMap<String,Integer> informacionevaluacionMapeo = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local_consultar);
        setTitle(R.string.detallelocalread);

        spinnerlocal = findViewById(R.id.busquedalocal);
        spinnerevaluacion = findViewById(R.id.busquedaeval);
        localtext = findViewById(R.id.idlocal);
        evaluacionText = findViewById(R.id.idevaluacion);
        cantidadText = findViewById(R.id.cantidad);

        LocalDB localDB = new LocalDB(this);
        EvaluacionDB evaluacionDB = new EvaluacionDB(this);
        List<Local> locales = localDB.getLocales();
        HashMap<Integer,String> evaluaciones = evaluacionDB.getEvaluaciones();

        for (Local local : locales){
            nombrelocales.add(local.getNombreLocal());
            nombreslocalesMapeo.put(local.getNombreLocal(),local.getIdLocal());
        }

        for (HashMap.Entry<Integer,String> entry : evaluaciones.entrySet()){
            informacionevaluacion.add(entry.getValue());
            informacionevaluacionMapeo.put(entry.getValue(),entry.getKey());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombrelocales);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocal.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,informacionevaluacion);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerevaluacion.setAdapter(adapter2);
    }

    public void consultarDetalleLocal(View v){
        detalleLocalDB = new DetalleLocalDB(this);
        String localdata = spinnerlocal.getSelectedItem().toString();
        Integer idLocal = nombreslocalesMapeo.get(localdata);
        String evaluaciondata = spinnerevaluacion.getSelectedItem().toString();
        Integer idEvaluacion = informacionevaluacionMapeo.get(evaluaciondata);

        DetalleLocal detalleLocal = detalleLocalDB.consultar(String.valueOf(idLocal),String.valueOf(idEvaluacion));
        if (detalleLocal != null){
            localtext.setText(spinnerlocal.getSelectedItem().toString());
            evaluacionText.setText(spinnerevaluacion.getSelectedItem().toString());
            cantidadText.setText(String.valueOf(detalleLocal.getCantidadAlumnos()));
        } else
        Toast.makeText(this, "Esta consulta oon id local: " + idLocal +
                " and id evaluacion: " + idEvaluacion + " no existe", Toast.LENGTH_SHORT).show();
    }
}

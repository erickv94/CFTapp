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
import dev.grupo5.cftapp.modelos.DetalleLocal;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.database.EvaluacionDB;

public class DetalleLocalActualizarActivity extends AppCompatActivity {
    DetalleLocalDB detalleLocalDB;
    Spinner spinnerlocal;
    Spinner spinnereval;
    EditText cantidadText;

    List<String> nombreslocales = new ArrayList<String>();
    HashMap<String,Integer> nombrelocalMapeo = new HashMap<String, Integer>();
    List<String> informacioneval = new ArrayList<String>();
    HashMap<String,Integer> informacionevalMapeo = new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local_actualizar);
        setTitle(R.string.detallelocalupdate);
        spinnerlocal = findViewById(R.id.detalle_local);
        spinnereval = findViewById(R.id.detalle_evalua);
        cantidadText = findViewById(R.id.cantidad);

        List<Local> locales = new LocalDB(this).getLocales();
        HashMap<Integer,String> evaluaciones = new EvaluacionDB(this).getEvaluaciones();

        for (Local local : locales){
            nombreslocales.add(local.getNombreLocal());
            nombrelocalMapeo.put(local.getNombreLocal(),local.getIdLocal());
        }

        for (HashMap.Entry<Integer,String> entry : evaluaciones.entrySet()){
            informacioneval.add(entry.getValue());
            informacionevalMapeo.put(entry.getValue(),entry.getKey());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombreslocales);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocal.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,informacioneval);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnereval.setAdapter(adapter2);

    }

    public void actualizarDetalleLocal(View v){
        detalleLocalDB = new DetalleLocalDB(this);

        String localdata = spinnerlocal.getSelectedItem().toString();
        Integer idLocal = nombrelocalMapeo.get(localdata);

        String evaluaciondata = spinnereval.getSelectedItem().toString();
        Integer idEvaluacion = informacionevalMapeo.get(evaluaciondata);

        DetalleLocal detalleLocal = new DetalleLocal();
        detalleLocal.setIdLocal(idLocal);
        detalleLocal.setIdEvaluacion(idEvaluacion);
        detalleLocal.setCantidadAlumnos(Integer.parseInt(cantidadText.getText().toString()));

        String resultado = detalleLocalDB.actualizar(detalleLocal);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
}

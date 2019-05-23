package dev.grupo5.cftapp.mantenimientos.DetalleLocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DetalleLocalDB;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.modelos.DetalleLocal;
import dev.grupo5.cftapp.modelos.Local;

public class DetalleLocalEliminarActivity extends AppCompatActivity {
    DetalleLocalDB detalleLocalDB;
    Spinner spinnerlocal;
    Spinner spinnereval;

    List<String> nombrelocales = new ArrayList<String>();
    HashMap<String,Integer> nombrelocalesMapeo = new HashMap<String, Integer>();
    List<String> informacionevaluaciones = new ArrayList<String>();
    HashMap<String,Integer> informacionevaluacionesMapeo = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local_eliminar);
        setTitle(R.string.detallelocaldelete);

        spinnerlocal = findViewById(R.id.detalle_local);
        spinnereval = findViewById(R.id.detalle_evalua);

        List<Local> locales = new LocalDB(this).getLocales();
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
        spinnerlocal.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,informacionevaluaciones);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnereval.setAdapter(adapter2);
    }

    public void eliminarDetalleLocal(View v){
        detalleLocalDB = new DetalleLocalDB(this);
        DetalleLocal detalleLocal = new DetalleLocal();

        String localdata = spinnerlocal.getSelectedItem().toString();
        Integer idlocal = nombrelocalesMapeo.get(localdata);
        String evaldata = spinnereval.getSelectedItem().toString();
        Integer ideval = informacionevaluacionesMapeo.get(evaldata);

        detalleLocal.setIdLocal(idlocal);
        detalleLocal.setIdEvaluacion(ideval);

        String resultado = detalleLocalDB.eliminar(detalleLocal);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
}

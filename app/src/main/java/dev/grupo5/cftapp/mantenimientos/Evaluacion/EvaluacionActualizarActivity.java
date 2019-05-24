package dev.grupo5.cftapp.mantenimientos.Evaluacion;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;
import dev.grupo5.cftapp.modelos.Evaluacion;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class EvaluacionActualizarActivity extends AppCompatActivity {
    EditText idText;
    EditText nombreText;
    EditText fechaText;
    Spinner spinnerGrupo;
    Spinner spinnerTipo;
    DatePickerDialog picker;
    private static final int permiso = 55;


    ArrayList<String> codgrupos = new ArrayList<String>();
    HashMap<String,String> codgruposMapeo = new HashMap<String, String>();
    ArrayList<String> nombretipos = new ArrayList<String>();
    HashMap<String,String> nombretiposMapeo = new HashMap<String, String>();

    List<GrupoMateriaCiclo> grupoMateriaCiclos;
    List<TipoEvaluacion> tipoEvaluaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_actualizar);
        setTitle(R.string.evalucionupdate);
        verificarPermisos();
        idText = findViewById(R.id.idevaluacion);
        nombreText = findViewById(R.id.nombreevaluacion);
        fechaText = findViewById(R.id.fecha);
        spinnerGrupo = findViewById(R.id.idgrupo);
        spinnerTipo = findViewById(R.id.idtipoevaluacion);

        grupoMateriaCiclos = new GrupoMateriaCicloDB(this).getGruposMateriasCiclos();
        tipoEvaluaciones = new TipoEvaluacionDB(this).getTiposEvaluaciones();

        for (GrupoMateriaCiclo grupoMateriaCiclo : grupoMateriaCiclos){
            codgrupos.add(grupoMateriaCiclo.getCodgrupo());
            codgruposMapeo.put(grupoMateriaCiclo.getCodgrupo(),String.valueOf(grupoMateriaCiclo.getIdGrupo()));
        }

        for (TipoEvaluacion tipoEvaluacion : tipoEvaluaciones){
            nombretipos.add(tipoEvaluacion.getNombre());
            nombretiposMapeo.put(tipoEvaluacion.getNombre(),String.valueOf(tipoEvaluacion.getIdTipoEvaluacion()));
        }

        ArrayAdapter<String> adapterGrupo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,codgrupos);
        adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrupo.setAdapter(adapterGrupo);

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombretipos);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipo);

        fechaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(EvaluacionActualizarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                fechaText.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.evalucionupdate), Toast.LENGTH_LONG).show();
        }

    }

    public void actualizarEvaluacion(View v){
        EvaluacionDB evaluacionDB = new EvaluacionDB(this);
        Evaluacion evaluacion = new Evaluacion();

        String idgrupomatc = codgruposMapeo.get(spinnerGrupo.getSelectedItem().toString());
        String idtipoe = nombretiposMapeo.get(spinnerTipo.getSelectedItem().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            evaluacion.setIdEvaluacion(Integer.valueOf(idText.getText().toString()));
            evaluacion.setIdGrupo(Integer.valueOf(idgrupomatc));
            evaluacion.setIdTipoEvaluacion(Integer.valueOf(idtipoe));
            evaluacion.setNombreEvaluacion(nombreText.getText().toString());
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try {
            evaluacion.setFecha(simpleDateFormat.parse(fechaText.getText().toString()));
        } catch (ParseException pe){
            Toast.makeText(this,pe.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String resultado = evaluacionDB.actualizar(evaluacion);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }

    public void limpiarEvaluacion(View v){
        idText.setText("");
        fechaText.setText("");
        nombreText.setText("");
    }
}

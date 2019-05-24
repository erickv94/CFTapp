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

public class EvaluacionInsertarActivity extends AppCompatActivity {
    EditText nombreevaluacionText;
    EditText fechaText;
    Spinner grupoSpinner;
    Spinner tipoEvaluacionSpinner;
    DatePickerDialog picker;
    private static final int permiso = 53;

    ArrayList<String> codgrupos= new ArrayList<String>();
    HashMap<String,String> codgruposMapeo= new HashMap<String, String>();
    ArrayList<String> nombrestipos= new ArrayList<String>();
    HashMap<String,String> nombretiposMapeo= new HashMap<String, String>();

    List<GrupoMateriaCiclo> grupoMateriaCiclos;
    List<TipoEvaluacion> tipoEvaluaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_insertar);
        setTitle(R.string.evaluacioninsert);
         verificarPermisos();

        nombreevaluacionText = (EditText) findViewById(R.id.nombreevaluacion);
        fechaText = (EditText) findViewById(R.id.fecha);
        grupoSpinner = findViewById(R.id.idgrupo);
        tipoEvaluacionSpinner = findViewById(R.id.idtipoevaluacion);

        grupoMateriaCiclos = new GrupoMateriaCicloDB(this).getGruposMateriasCiclos();
        tipoEvaluaciones = new TipoEvaluacionDB(this).getTiposEvaluaciones();

        for(GrupoMateriaCiclo grupo:grupoMateriaCiclos){
            codgrupos.add(grupo.getCodgrupo());
            codgruposMapeo.put(grupo.getCodgrupo(),String.valueOf(grupo.getIdGrupo()));
        }

        for(TipoEvaluacion tipoEvaluacion: tipoEvaluaciones){
            nombrestipos.add(tipoEvaluacion.getNombre());
            nombretiposMapeo.put(tipoEvaluacion.getNombre(),String.valueOf(tipoEvaluacion.getIdTipoEvaluacion()));
        }

        ArrayAdapter<String> adapterGrupo= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,codgrupos);
        adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupoSpinner.setAdapter(adapterGrupo);

        ArrayAdapter<String> adapterTipo= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombrestipos);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoEvaluacionSpinner.setAdapter(adapterTipo);


        fechaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(EvaluacionInsertarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                fechaText.setText(dayOfMonth + "-" + (monthofyear + 1) + "-" + year);
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
                    +getResources().getString(R.string.evaluacioninsert), Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarEvaluacion(View v){
        fechaText.setText("");
        nombreevaluacionText.setText("");
    }

    public void insertarEvaluacion(View v) throws ParseException {
        EvaluacionDB evaluacionDB= new EvaluacionDB(this);
        Evaluacion evaluacion= new Evaluacion();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String reginsertados;

        evaluacion.setIdTipoEvaluacion(Integer.valueOf(nombretiposMapeo
                .get(tipoEvaluacionSpinner.getSelectedItem().toString())));
        evaluacion.setIdGrupo(Integer.parseInt(codgruposMapeo.get(grupoSpinner.getSelectedItem().toString())));
        evaluacion.setNombreEvaluacion(nombreevaluacionText.getText().toString());
        evaluacion.setFecha(simpleDateFormat.parse(fechaText.getText().toString()));

        reginsertados = evaluacionDB.insertar(evaluacion);
        Toast.makeText(this,reginsertados,Toast.LENGTH_SHORT).show();
    }
}

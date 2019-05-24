package dev.grupo5.cftapp.mantenimientos.Evaluacion;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;
import dev.grupo5.cftapp.modelos.Evaluacion;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class EvaluacionConsultarActivity extends AppCompatActivity {
    EvaluacionDB evaluacionDB;
    Spinner spinnerTipo;
    EditText fechaBusqueda;
    EditText idgrupoText;
    EditText idText;
    EditText nombreText;
    EditText fechaText;
    EditText tipoevaluacionText;
    DatePickerDialog picker;

    ArrayList<String> nombreTipos = new ArrayList<String>();
    HashMap<String,String> nombretiposMapeo = new HashMap<String,String>();

    List<TipoEvaluacion> tipoEvaluaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_consultar);
        setTitle(R.string.evaluacionread);
        spinnerTipo = (Spinner) findViewById(R.id.busqueda1);
        fechaBusqueda = findViewById(R.id.busqueda2);
        idgrupoText = findViewById(R.id.idgrupo);
        idText = findViewById(R.id.idevaluacion);
        nombreText = findViewById(R.id.nombreevaluacion);
        tipoevaluacionText = findViewById(R.id.idtipoevaluacion);
        fechaText = findViewById(R.id.fecha);

        tipoEvaluaciones = new TipoEvaluacionDB(this).getTiposEvaluaciones();
        for (TipoEvaluacion tipoEvaluacion : tipoEvaluaciones){
            nombreTipos.add(tipoEvaluacion.getNombre());
            nombretiposMapeo.put(tipoEvaluacion.getNombre(),String.valueOf(tipoEvaluacion.getIdTipoEvaluacion()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombreTipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        fechaBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(EvaluacionConsultarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                fechaBusqueda.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void consultarEvaluacion(View v) throws ParseException {
        String tiposeleccionado = spinnerTipo.getSelectedItem().toString();
        String fechaseleccionada = fechaBusqueda.getText().toString();
        String idtipo;


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(fechaseleccionada);
        fechaseleccionada = simpleDateFormat.format(date);

        idtipo = nombretiposMapeo.get(tiposeleccionado);
        evaluacionDB = new EvaluacionDB(this);
        Evaluacion evaluacion = evaluacionDB.consultar(idtipo,fechaseleccionada);

        if (evaluacion != null){
            SQLiteDatabase db = DBHelper.getSingleton(this).getReadableDatabase();
            String[] campo = {"nombre"};
            String[] toWhere = {String.valueOf(evaluacion.getIdTipoEvaluacion())};
            Cursor cursor = db.query("evaluacion", campo, "idtipoevaluacion=?", toWhere, null, null, null);
            cursor.moveToFirst();
            tipoevaluacionText.setText(spinnerTipo.getSelectedItem().toString());
            idgrupoText.setText(cursor.getString(0));
            idText.setText(String.valueOf(evaluacion.getIdEvaluacion()));
            nombreText.setText(cursor.getString(1));
            fechaText.setText(simpleDateFormat.format(evaluacion.getFecha()));
            return;
        } else
            Toast.makeText(this, "fecha: " + fechaseleccionada + ", tipo: " + tiposeleccionado
            + "no existe", Toast.LENGTH_SHORT).show();

    }

    public void limpiarEvaluacion(View v){
        tipoevaluacionText.setText("");
        idText.setText("");
        idgrupoText.setText("");
        nombreText.setText("");
        fechaText.setText("");
        fechaBusqueda.setText("");
    }
}

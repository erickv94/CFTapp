package dev.grupo5.cftapp.mantenimientos.DiasNoHabiles;

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
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.DiasNoHabilesDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasNoHabilesActualizarActivity extends AppCompatActivity {

    Spinner spinnerCiclo;
    DatePickerDialog picker;

    EditText editIdDia;
    EditText editNombre;
    EditText editDescripcion;
    EditText editFecha;

    ArrayList<String> nombresCiclos= new ArrayList<String>();
    HashMap<String,String> nombresCiclosMapeo= new HashMap<String, String>();

    List<Ciclo> ciclos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_actualizar);
        setTitle(R.string.diasupdate);

        spinnerCiclo = findViewById(R.id.idCiclo);
        editIdDia=findViewById(R.id.editIdDia);
        editNombre = findViewById(R.id.editNombreDia);
        editDescripcion = findViewById(R.id.editDescripcion);
        editFecha = findViewById(R.id.fechasolicitud);

        ciclos = new CicloDB(this).getCiclos();
        for (Ciclo ciclo : ciclos) {
            nombresCiclos.add(String.valueOf(ciclo.getCiclo()));
            nombresCiclosMapeo.put(String.valueOf(ciclo.getCiclo()), String.valueOf(ciclo.getIdCiclo()));
        }
        ArrayAdapter<String> adapterCiclo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresCiclos);
        adapterCiclo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclo.setAdapter(adapterCiclo);

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DiasNoHabilesActualizarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                editFecha.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        },year, month, day);
                picker.show();
            }
        });

    }
    public void actualizarDiaNoHabil(View view){
        DiasNoHabilesDB diasNoHabilesDB= new DiasNoHabilesDB(this);
        DiasNoHabiles diasNoHabiles= new DiasNoHabiles();

        String  idciclo=nombresCiclosMapeo.get(spinnerCiclo.getSelectedItem().toString());
        //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        try {
            diasNoHabiles.setIdDias(Integer.valueOf(editIdDia.getText().toString()));
            diasNoHabiles.setIdCiclo(Integer.valueOf(idciclo));
            diasNoHabiles.setNombre(editNombre.getText().toString());
            diasNoHabiles.setDescripcion(editDescripcion.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try {
            diasNoHabiles.setFecha(simpleDateFormat.parse(editFecha.getText().toString()));
        }
        catch (ParseException pe){
            Toast.makeText(this,pe.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String resultado=diasNoHabilesDB.actualizar(diasNoHabiles);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {

        editIdDia.setText("");
        editNombre.setText("");
        editFecha.setText("");
        editDescripcion.setText("");
    }
}

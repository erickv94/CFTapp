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

import java.net.Inet4Address;
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

public class DiasNoHabilesInsertarActivity extends AppCompatActivity {

    Spinner spinnerCiclo;
    DatePickerDialog picker;

    EditText editNombre;
    EditText editDescripcion;
    EditText editFecha;

    ArrayList<String> nombresCiclos= new ArrayList<String>();
    HashMap<String,String> nombresCiclosMapeo= new HashMap<String, String>();

    List<Ciclo> ciclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_insertar);
        setTitle(R.string.diasinsert);

        spinnerCiclo = findViewById(R.id.idCiclo);
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
                picker = new DatePickerDialog(DiasNoHabilesInsertarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                editFecha.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

        public void insertarDiaNoHabil(View v) throws ParseException {
            DiasNoHabilesDB diasNoHabilesDB= new DiasNoHabilesDB(this);
            DiasNoHabiles diasNoHabiles= new DiasNoHabiles();

            //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String cantidad;
            diasNoHabiles.setIdCiclo(Integer.valueOf(nombresCiclosMapeo.get(spinnerCiclo.getSelectedItem().toString())));
            diasNoHabiles.setNombre(editNombre.getText().toString());
            diasNoHabiles.setDescripcion(editDescripcion.getText().toString());
            diasNoHabiles.setFecha(simpleDateFormat.parse(editFecha.getText().toString()));
            //diasNoHabilesDB.insertar(diasNoHabiles);
            cantidad=diasNoHabilesDB.insertar(diasNoHabiles);
            Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();
        }


    public void limpiarTexto(View v) {

        //editIdDocente.setText("");
        //editIdTipoDocente.setText("");
        editNombre.setText("");
        editFecha.setText("");
        editDescripcion.setText("");
    }
}

package dev.grupo5.cftapp.mantenimientos.DiasNoHabileswbs;

import android.app.DatePickerDialog;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DiasNoHabilesDB;
import dev.grupo5.cftapp.databaseWS.DiasWS;

import dev.grupo5.cftapp.databaseWS.DocenteDBWS;
import dev.grupo5.cftapp.mantenimientos.DiasNoHabiles.DiasNoHabilesInsertarActivity;
import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasInsertarWbsActivity extends AppCompatActivity {
    EditText   idcicloText;
    DatePickerDialog picker;

    EditText editNombre;
    EditText editDescripcion;
    EditText editFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_insertar_wbs);
        setTitle(getResources().getString(R.string.diasinsert)+" webservice");
        idcicloText=findViewById(R.id.idciclo);
        editNombre = findViewById(R.id.editNombreDia);
        editDescripcion = findViewById(R.id.editDescripcion);
        editFecha = findViewById(R.id.fechasolicitud);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DiasInsertarWbsActivity.this,
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
        String url="http://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_dias_insert.php?" +
                      "idciclo=" +idcicloText.getText().toString()+
                       "&nombre="+editNombre.getText().toString()+
                        "&descripcion="+editDescripcion.getText().toString()+
                        "&fecha="+editFecha.getText().toString();


        String mensaje= DiasWS.insertarDiasServidor(url,this);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT)
                .show();

    }


    public void limpiarTexto(View v) {

        //editIdDocente.setText("");
        //editIdTipoDocente.setText("");
        editNombre.setText("");
        editFecha.setText("");
        editDescripcion.setText("");
    }
}

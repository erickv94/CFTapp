package dev.grupo5.cftapp.mantenimientos.Tramite;

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
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.modelos.TipoTramite;
import dev.grupo5.cftapp.modelos.Tramite;

public class TramiteActualizarActivity extends AppCompatActivity {
    EditText fechaText;
    EditText idText;
    Spinner localSpinner;
    Spinner tipoTramiteSpinner;
    DatePickerDialog picker;
    private static final int  permiso=27;


    ArrayList<String> nombresLocales= new ArrayList<String>();
    HashMap<String,String> nombresLocalesMapeo= new HashMap<String, String>();
    ArrayList<String> nombresTramite= new ArrayList<String>();
    HashMap<String,String> nombresTramiteMapeo= new HashMap<String, String>();


    List<TipoTramite> tiposTramites;
    List<Local> locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramite_actualizar);
        setTitle(R.string.tramiteupdate);
        idText=findViewById(R.id.idtramite);
        fechaText= findViewById(R.id.fechasolicitud);
        localSpinner= findViewById(R.id.idlocaltramite);
        tipoTramiteSpinner=findViewById(R.id.idtipotramite);

        locales= new LocalDB(this).getLocales();
        tiposTramites= new TipoTramiteDB(this).getTiposTramites();
        fechaText= findViewById(R.id.fechasolicitud);

        for(Local local:locales){
            nombresLocales.add(local.getNombreLocal());
            nombresLocalesMapeo.put(local.getNombreLocal(),String.valueOf(local.getIdLocal()));
        }

        for(TipoTramite tipoTramite: tiposTramites){
            nombresTramite.add(tipoTramite.getNombre());
            nombresTramiteMapeo.put(tipoTramite.getNombre(),String.valueOf(tipoTramite.getIdTipoTramite()));
        }

        ArrayAdapter<String> adapterLocal= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresLocales);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localSpinner.setAdapter(adapterLocal);

        ArrayAdapter<String> adapterTramite= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresTramite);
        adapterTramite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoTramiteSpinner.setAdapter(adapterTramite);

        fechaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker= new DatePickerDialog(TramiteActualizarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }

    public void limpiar(View view){
        fechaText.setText("");
    }

    public void actualizarTramite(View view){
        TramiteDB tramiteDB= new TramiteDB(this);
        Tramite tramite= new Tramite();

        String  idlocal=nombresLocalesMapeo.get(localSpinner.getSelectedItem().toString());
        String  idtipotramite=nombresTramiteMapeo.get(tipoTramiteSpinner.getSelectedItem().toString());
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        try {
            tramite.setIdTramite(Integer.valueOf(idText.getText().toString()));
            tramite.setIdLocal(Integer.valueOf(idlocal));
            tramite.setIdTipoTramite(Integer.valueOf(idtipotramite));
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try {
            tramite.setFechaSolicitud(simpleDateFormat.parse(fechaText.getText().toString()));
        }
        catch (ParseException pe){
            Toast.makeText(this,pe.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String resultado=tramiteDB.actualizar(tramite);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
}

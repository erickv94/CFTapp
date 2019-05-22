package dev.grupo5.cftapp.mantenimientos.SolicitudImpresa;

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
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.SolicitudImpresaDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaInsertarActivity extends AppCompatActivity {

    Spinner spinnerDocente;
    DatePickerDialog picker;

    //EditText editIdSolicitudImp;
    //EditText editIdDocente;
    EditText editCantidadImp;
    EditText editAsunto;
    EditText editJustificacion;
    EditText editAprobado;
    EditText editFechaSolicitud;
    EditText editPaginasAnexas;
    EditText editCodImpresion;

    ArrayList<String> nombresSolicitudes= new ArrayList<String>();
    HashMap<String,String> nombresSolicitudesMapeo= new HashMap<String, String>();

    List<Docente> docentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresa_insertar);
        setTitle(R.string.solicitudesinsert);

        spinnerDocente=findViewById(R.id.idDocente);
        editCantidadImp=findViewById(R.id.editCantidadImp);
        editAsunto=findViewById(R.id.editAsunto);
        editJustificacion=findViewById(R.id.editJustificacion);
        editAprobado=findViewById(R.id.editAprobado);
        editFechaSolicitud=findViewById(R.id.editFechaSolicitud);
        editPaginasAnexas=findViewById(R.id.editPaginasAnexas);
        editCodImpresion=findViewById(R.id.editCodImpresion);

        docentes= new DocenteDB(this).getListaDocentes();
        for(Docente docente:docentes){
            nombresSolicitudes.add(docente.getNombre());
            nombresSolicitudesMapeo.put(docente.getNombre(),String.valueOf(docente.getIdDocente()));
        }

        ArrayAdapter<String> adapterDocente= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresSolicitudes);
        adapterDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocente.setAdapter(adapterDocente);

        editFechaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker= new DatePickerDialog(SolicitudImpresaInsertarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editFechaSolicitud.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void insertarSolicitudImpresa(View view) throws ParseException {
        SolicitudImpresaDB solicitudImpresaDB= new SolicitudImpresaDB(this);
        SolicitudImpresa solicitudImpresa= new SolicitudImpresa();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");

        String cantidad;

        solicitudImpresa.setIdDocente(Integer.valueOf(nombresSolicitudesMapeo.get(spinnerDocente.getSelectedItem().toString())));
        solicitudImpresa.setCantidadImpresiones(Integer.parseInt(editCantidadImp.getText().toString()));
        solicitudImpresa.setAsunto(editAsunto.getText().toString());
        solicitudImpresa.setJustificacion(editJustificacion.getText().toString());
        solicitudImpresa.setAprobado(Boolean.parseBoolean(editAprobado.getText().toString()));
        solicitudImpresa.setCodigoImpresion(editCodImpresion.getText().toString());
        solicitudImpresa.setFechasolicitud(simpleDateFormat.parse(editFechaSolicitud.getText().toString()));

        //solicitudImpresaDB.insertar(solicitudImpresa);

        cantidad=solicitudImpresaDB.insertar(solicitudImpresa);
        Toast.makeText(this, cantidad,Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v) {

        editCantidadImp.setText("");
        editAsunto.setText("");
        editJustificacion.setText("");
        editAprobado.setText("");
        editFechaSolicitud.setText("");
        editPaginasAnexas.setText("");
        editCodImpresion.setText("");
    }

}

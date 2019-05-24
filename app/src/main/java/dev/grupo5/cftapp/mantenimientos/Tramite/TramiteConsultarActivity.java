package dev.grupo5.cftapp.mantenimientos.Tramite;

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
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.modelos.TipoTramite;
import dev.grupo5.cftapp.modelos.Tramite;

public class TramiteConsultarActivity extends AppCompatActivity {
    //parametros
    Spinner spinnerlocal;
    EditText fechaBusqueda;
    DatePickerDialog picker;
    //salidas
    EditText localText;
    EditText tipoTramiteText;
    EditText fechaText;
    EditText idText;
    private static final int  permiso=26;

    ArrayList<String> nombresLocales= new ArrayList<String>();
    HashMap<String,String> nombresLocalesMapeo= new HashMap<String, String>();


    List<TipoTramite> tiposTramites;
    List<Local> locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramite_consultar);
        setTitle(R.string.tramiteread);
        //seteando elementos del view
        spinnerlocal=(Spinner) findViewById(R.id.busqueda1);
        fechaBusqueda=findViewById(R.id.busqueda2);
        fechaText= findViewById(R.id.fecha);
        localText=findViewById(R.id.idlocal);
        tipoTramiteText= findViewById(R.id.idtipotramite);
        idText = findViewById(R.id.idtramite);

        //llenando arrays
        List<Local> locales= new LocalDB(this).getLocales();
        for (Local local: locales){
            nombresLocales.add(local.getNombreLocal());
            nombresLocalesMapeo.put(local.getNombreLocal(),String.valueOf(local.getIdLocal()));

        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresLocales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocal.setAdapter(adapter);

        fechaBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TramiteConsultarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaBusqueda.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void consultarTramite(View view) throws ParseException {

        String localseleccionado=spinnerlocal.getSelectedItem().toString();
        String fechaSeleccionada= fechaBusqueda.getText().toString();
        String idlocal;

        //transformar fecha a formato adecuado
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        Date date= simpleDateFormat.parse(fechaSeleccionada);
        fechaSeleccionada= simpleDateFormat.format(date);

        //obtener codigolocal
        idlocal= nombresLocalesMapeo.get(localseleccionado);
        TramiteDB tramiteDB=new TramiteDB(this);
        Tramite tramite=tramiteDB.consultar(idlocal,fechaSeleccionada);

        if(tramite!=null){

            SQLiteDatabase db=DBHelper.getSingleton(this).getReadableDatabase();
            String[] campos={"nombre"};
            String [] toWhere={String.valueOf(tramite.getIdTipoTramite())};
            Cursor cursor = db.query("tipotramite", campos, "idtipotramite=?", toWhere, null, null, null);
            cursor.moveToFirst();
            localText.setText(spinnerlocal.getSelectedItem().toString());
            tipoTramiteText.setText(cursor.getString(0));
            fechaText.setText(simpleDateFormat.format(tramite.getFechaSolicitud()));
            idText.setText(String.valueOf(tramite.getIdTramite()));
            return;
        }


     Toast.makeText(this,R.string.consulta_no_existe+" fecha:"
             +fechaSeleccionada+", local: "+localseleccionado,Toast.LENGTH_SHORT).show();
    }

    private void clean() {
        localText.setText("");
        tipoTramiteText.setText("");
        fechaText.setText("");
    }


}

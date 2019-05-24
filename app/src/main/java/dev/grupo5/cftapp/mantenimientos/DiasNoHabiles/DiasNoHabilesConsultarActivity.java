package dev.grupo5.cftapp.mantenimientos.DiasNoHabiles;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.DiasNoHabilesDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasNoHabilesConsultarActivity extends AppCompatActivity {

    EditText fechaBusqueda;
    DatePickerDialog picker;
    Spinner spinnerCiclo;

    EditText editIdDia;
    EditText editIdCiclo;
    EditText editNombreDia;
    EditText editDescripcion;
    EditText editFecha;
    private static final int permiso = 74;

    ArrayList<String> nombresCiclos= new ArrayList<String>();
    HashMap<String,String> nombresCiclosMapeo= new HashMap<String, String>();

    List<Ciclo> ciclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_consultar);
        setTitle(R.string.diasread);
        verificarPermisos();
        //spinnerCiclo = findViewById(R.id.Busqueda);
        //fechaBusqueda=findViewById(R.id.busqueda);
        editFecha=findViewById(R.id.editFechaSolicitud);
        editIdDia=findViewById(R.id.editIdDia);
        editIdCiclo=findViewById(R.id.editCiclo);
        editNombreDia=findViewById(R.id.editNombreDia);
        editDescripcion=findViewById(R.id.editDescripcion);

        /*ciclos = new CicloDB(this).getCiclos();
        for (Ciclo ciclo : ciclos) {
            nombresCiclos.add(String.valueOf(ciclo.getCiclo()));
            nombresCiclosMapeo.put(String.valueOf(ciclo.getCiclo()), String.valueOf(ciclo.getIdCiclo()));
        }
        ArrayAdapter<String> adapterCiclo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresCiclos);
        adapterCiclo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        fechaBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DiasNoHabilesConsultarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaBusqueda.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        */

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.diasread), Toast.LENGTH_LONG).show();
        }

    }

    public void consultarDias(View view) throws ParseException {
        //String cicloseleccionado=spinnerCiclo.getSelectedItem().toString();
        //String fechaSeleccionada= fechaBusqueda.getText().toString();
        String id_dias;

        //transformar fecha a formato adecuado
        //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date= simpleDateFormat.parse(fechaSeleccionada);
        //fechaSeleccionada= simpleDateFormat.format(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date d = dateFormat.parse(datestring);

       DiasNoHabilesDB diasNoHabilesDB=new DiasNoHabilesDB(this);
       DiasNoHabiles diasNoHabiles=diasNoHabilesDB.consultar(editIdDia.getText().toString());

        //obtener idciclo
        /*
        idciclo= nombresCiclosMapeo.get(cicloseleccionado);
        DiasNoHabilesDB diasNoHabilesDB=new DiasNoHabilesDB(this);
        DiasNoHabiles diasNoHabiles=diasNoHabilesDB.consultar(idciclo,fechaSeleccionada);
    */
        if(diasNoHabiles!=null){

           /* SQLiteDatabase db= DBHelper.getSingleton(this).getReadableDatabase();
            String[] campos={"nombre"};
            String [] toWhere={String.valueOf(diasNoHabiles.getIdDias())};
            Cursor cursor = db.query("diasnohabiles", campos, "id_dias=?", toWhere, null, null, null);
            cursor.moveToFirst();


            //editIdCiclo.setText(spinnerCiclo.getSelectedItem().toString());
            //editIdDia.setText(String.valueOf(diasNoHabiles.getIdDias()));
            //editIdCiclo.setText(cursor.getString(0));
            editNombreDia.setText(cursor.getString(0));
            editDescripcion.setText(cursor.getString(1));
            editFecha.setText(simpleDateFormat.format(diasNoHabiles.getFecha()));
            */


           editNombreDia.setText(diasNoHabiles.getNombre());
            editDescripcion.setText(diasNoHabiles.getDescripcion());
            //editFecha.setText(simpleDateFormat.format(diasNoHabiles.getFecha()));


            return; }
        Toast.makeText(this,"Consulta no existe"+" ID: "
                +editIdDia+"",Toast.LENGTH_SHORT).show();
        }

        public void limpiarTexto(View v) {
            editIdCiclo.setText("");
            editIdDia.setText("");
            editNombreDia.setText("");
            editDescripcion.setText("");
            editFecha.setText("");
}
}

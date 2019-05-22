package dev.grupo5.cftapp.mantenimientos.Docente;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class DocenteConsultarActivity extends AppCompatActivity {

    DocenteDB helper;
    Spinner spinnerTipoDocente;

    //
    EditText editIdDocente;
    EditText editIdTipoDocente;
    EditText editNombre;
    EditText editApellido;
    EditText editCodDocente;
    EditText editSexo;
    /** Called when the activity is first created. */

    ArrayList<String> nombresTipoDocente= new ArrayList<String>();
    HashMap<String,String> nombresTipoDocenteMapeo= new HashMap<String, String>();

    List<TipoDocente> tipoDocentes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        setTitle(R.string.docenteread);

        spinnerTipoDocente=(Spinner) findViewById(R.id.busqueda1);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

        //LLenando Array
        List<TipoDocente> tipoDocentes=new TipoDocenteDB(this).getTipoDocentes();
        //tipoDocentes=new TipoDocenteDB(this).getTipoDocentes();

        for (TipoDocente tipoDocente:tipoDocentes){
            nombresTipoDocente.add(tipoDocente.getNombre());
            nombresTipoDocenteMapeo.put(tipoDocente.getNombre(),String.valueOf(tipoDocente.getIdTipoDocente()));

        }
        ArrayAdapter<String> adapterTipoDocente= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresTipoDocente);
        adapterTipoDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDocente.setAdapter(adapterTipoDocente);
    }
    public void consultarDocente(View v) throws ParseException {
        // helper.abrir();
        String tipoDocenteSeleccionado=spinnerTipoDocente.getSelectedItem().toString();
        String idtipodocente;

        //obtener idtipodocente
        idtipodocente= nombresTipoDocenteMapeo.get(tipoDocenteSeleccionado);
        DocenteDB docenteDB=new DocenteDB(this);
        Docente docente=docenteDB.consultar(idtipodocente);

      if(docente!=null){

            SQLiteDatabase db= DBHelper.getSingleton(this).getReadableDatabase();
            String[] campo={"nombre"};
            String [] toWhere={String.valueOf(docente.getIdTipoDocente())};
            Cursor cursor = db.query("docente", campo, "idtipodocente=?", toWhere, null, null, null);
            cursor.moveToNext();
            //editIdTipoDocente.setText(nombresTipoDocenteMapeo.get(spinnerTipoDocente.getSelectedItem().toString()));
            editIdTipoDocente.setText(spinnerTipoDocente.getSelectedItem().toString());
            editIdDocente.setText(String.valueOf(docente.getIdDocente()));
            editNombre.setText(cursor.getString(0));
            editApellido.setText(cursor.getString(1));
            editCodDocente.setText(cursor.getString(2));
            editSexo.setText(cursor.getString(3));

            return;
        }

        Toast.makeText(this,"Consulta no existe"+" tipo docente:"
                +idtipodocente,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdDocente.setText("");
        editIdTipoDocente.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
        editNombre.setText("");
    }

}

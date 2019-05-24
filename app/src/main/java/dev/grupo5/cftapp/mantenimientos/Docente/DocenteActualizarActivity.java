package dev.grupo5.cftapp.mantenimientos.Docente;

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
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class DocenteActualizarActivity extends AppCompatActivity {

    DocenteDB helper;
    Spinner tipoDocenteSpinner;
    EditText editIdDocente;
    //EditText editIdTipoDocente;
    EditText editNombre;
    EditText editApellido;
    EditText editCodDocente;
    EditText editSexo;
    /** Called when the activity is first created. */
    ArrayList<String> nombresTipoDocentes= new ArrayList<String>();
    HashMap<String,String> nombresTipoDocentesMapeo= new HashMap<String, String>();
    List<TipoDocente> tiposDocentes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        setTitle(R.string.docenteupdate);
        helper = new DocenteDB(this);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        tipoDocenteSpinner = (Spinner) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

        tiposDocentes=new TipoDocenteDB(this).getTipoDocentes();

        for (TipoDocente tipoDocente:tiposDocentes){
            nombresTipoDocentes.add(tipoDocente.getNombre());
            nombresTipoDocentesMapeo.put(tipoDocente.getNombre(),String.valueOf(tipoDocente.getIdTipoDocente()));

        }
        ArrayAdapter<String> adapterTipoDocente= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresTipoDocentes);
        adapterTipoDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDocenteSpinner.setAdapter(adapterTipoDocente);

    }


    public void actualizarDocente(View v) {
        DocenteDB docenteDB=new DocenteDB(this);
        Docente docente = new Docente();
        String idtipodocente=nombresTipoDocentesMapeo.get(tipoDocenteSpinner.getSelectedItem().toString());

        try{
            docente.setIdDocente(Integer.valueOf(editIdDocente.getText().toString()));
            docente.setIdTipoDocente(Integer.valueOf(idtipodocente));
            docente.setNombre(editNombre.getText().toString());
            docente.setApellidos(editApellido.getText().toString());
            docente.setCodDocente(editCodDocente.getText().toString());
            docente.setSexo(editSexo.getText().toString());

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String resultado=docenteDB.actualizar(docente);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }


    public void limpiarTexto(View v) {
        editIdDocente.setText("");
        //editIdTipoDocente.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
    }
}

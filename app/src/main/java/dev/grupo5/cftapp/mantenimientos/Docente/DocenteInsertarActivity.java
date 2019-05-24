package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class DocenteInsertarActivity extends AppCompatActivity {

    DocenteDB helper;
    EditText editIdDocente;
    Spinner tipoDocenteSpinner;
    EditText editIdTipoDocente;
    EditText editNombre;
    EditText editApellido;
    EditText editCodDocente;
    EditText editSexo;
    private static final int  permiso=13;

    ArrayList<String> nombresTipoDocente= new ArrayList<String>();
    HashMap<String,String> nombresTipoDocenteMapeo= new HashMap<String, String>();

    List<TipoDocente> tipoDocentes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        setTitle(R.string.docenteinsert);

        //editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        //editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        tipoDocenteSpinner= (Spinner) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

        tipoDocentes=new TipoDocenteDB(this).getTipoDocentes();

        for (TipoDocente tipoDocente:tipoDocentes){
            nombresTipoDocente.add(tipoDocente.getNombre());
            nombresTipoDocenteMapeo.put(tipoDocente.getNombre(),String.valueOf(tipoDocente.getIdTipoDocente()));

        }
        ArrayAdapter<String> adapterTipoDocente= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombresTipoDocente);
        adapterTipoDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDocenteSpinner.setAdapter(adapterTipoDocente);

    }
    public void insertarDocente(View v) {

        DocenteDB docenteDB=new DocenteDB(this);
        Docente docente=new Docente();
        String cantidad;
        //docente.setIdTipoDocente(Integer.parseInt(editIdDocente.getText().toString()));
        docente.setIdTipoDocente(Integer.valueOf(nombresTipoDocenteMapeo.get(tipoDocenteSpinner.getSelectedItem().toString())));
        docente.setNombre(editNombre.getText().toString());
        docente.setApellidos(editApellido.getText().toString());
        docente.setCodDocente(editCodDocente.getText().toString());
        docente.setSexo(editSexo.getText().toString());

        cantidad=docenteDB.insertar(docente);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();





    }

    public void limpiarTexto(View v) {

        //editIdDocente.setText("");
        //editIdTipoDocente.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
        editNombre.setText("");
    }

}

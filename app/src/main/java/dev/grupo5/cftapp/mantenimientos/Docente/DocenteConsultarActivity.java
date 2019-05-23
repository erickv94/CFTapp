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
    //Spinner spinnerTipoDocente;

    //
    EditText editIdDocente;
    EditText editIdTipoDocente;
    EditText editNombre;
    EditText editApellido;
    EditText editCodDocente;
    EditText editSexo;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        setTitle(R.string.docenteread);

        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

    }

    public void consultarDocente(View view) {
        DocenteDB docenteDB = new DocenteDB(this);
        Docente docente = docenteDB.consultar(editIdDocente.getText().toString());

      if(docente!=null){

            //editIdTipoDocente.setText(nombresTipoDocenteMapeo.get(spinnerTipoDocente.getSelectedItem().toString()));
            //editIdDocente.setText(docente.getIdDocente());
            //editIdTipoDocente.setText(docente.getIdTipoDocente());
            editNombre.setText(docente.getNombre());
            editApellido.setText(docente.getApellidos());
            editCodDocente.setText(docente.getCodDocente());
            editSexo.setText(docente.getSexo());

            return;
        }

        Toast.makeText(this,"Resultado no existe" ,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdDocente.setText("");
        //editIdTipoDocente.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
        editNombre.setText("");
    }

}

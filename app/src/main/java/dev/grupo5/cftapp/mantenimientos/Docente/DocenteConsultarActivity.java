package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.modelos.Docente;

public class DocenteConsultarActivity extends AppCompatActivity {

    DocenteDB helper;
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
        //editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);
    }
    public void consultarDocente(View v) { 
        // helper.abrir();
        Docente docente =
                helper.consultar(editIdTipoDocente.getText().toString());
        helper.cerrar();
        if(docente == null)
            Toast.makeText(this, "Docente con id " +editIdTipoDocente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            //editIdDocente.setText(docente.getIdDocente());
            editIdTipoDocente.setText(docente.getIdTipoDocente());
            editNombre.setText(docente.getNombre());
            editApellido.setText(docente.getApellidos());
            editCodDocente.setText(docente.getCodDocente());
            editSexo.setText(docente.getSexo());
            return;
            
        }
    }

    public void limpiarTexto(View v) {
        //editIdDocente.setText("");
        editIdTipoDocente.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
        editNombre.setText("");
    }

}

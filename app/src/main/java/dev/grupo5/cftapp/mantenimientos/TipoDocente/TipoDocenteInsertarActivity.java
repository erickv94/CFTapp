
package dev.grupo5.cftapp.mantenimientos.TipoDocente;

import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class TipoDocenteInsertarActivity extends AppCompatActivity {

    TipoDocenteDB helper;
    EditText editIdTipoDocente;
    EditText editNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_insertar);
        setTitle(R.string.tipodocenteinsert);

        helper = new TipoDocenteDB(this);
        //editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }

    public void insertarTipoDocente(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoDocente.getText().toString());
        String nombre=editNombre.getText().toString();

        String regInsertados;
        TipoDocente tipoDocente=new TipoDocente();
        //tipoDocente.setIdTipoDocente(idtipodocente);
        tipoDocente.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(tipoDocente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        //editIdTipoDocente.setText("");
        editNombre.setText("");
    }

}
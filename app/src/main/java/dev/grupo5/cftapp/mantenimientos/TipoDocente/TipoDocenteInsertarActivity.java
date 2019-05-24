
package dev.grupo5.cftapp.mantenimientos.TipoDocente;

import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class TipoDocenteInsertarActivity extends AppCompatActivity {

    TipoDocenteDB helper;
    EditText editIdTipoDocente;
    EditText editNombre;
    private static final int  permiso=9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_insertar);
        setTitle(R.string.tipodocenteinsert);

        editNombre = findViewById(R.id.editNombre);

    }

    public void insertarTipoDocente(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoDocente.getText().toString());
        TipoDocenteDB tipoDocenteDB=new TipoDocenteDB(this);
        TipoDocente tipoDocente=new TipoDocente();
        String cantidad;
        tipoDocente.setNombre(editNombre.getText().toString());

        cantidad=tipoDocenteDB.insertar(tipoDocente);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        //editIdTipoDocente.setText("");
        editNombre.setText("");
    }

}
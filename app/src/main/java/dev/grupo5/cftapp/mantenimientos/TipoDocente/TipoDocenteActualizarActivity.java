package dev.grupo5.cftapp.mantenimientos.TipoDocente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class TipoDocenteActualizarActivity extends AppCompatActivity {

    EditText editIdTipoDocente;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_actualizar);
        setTitle(R.string.tipodocenteupdate);

        editIdTipoDocente=findViewById(R.id.editIdTipoDocente);
        editNombre= findViewById(R.id.editNombre);

    }

    public void actualizarTipoDocente(View view){
        TipoDocente tipoDocente= new TipoDocente();
        String registros;
        TipoDocenteDB tipoDocenteDB= new TipoDocenteDB(this);
        tipoDocente.setIdTipoDocente(Integer.parseInt(editIdTipoDocente.getText().toString()));
        tipoDocente.setNombre(editNombre.getText().toString());
        registros=tipoDocenteDB.actualizar(tipoDocente);
        Toast.makeText(this,registros,Toast.LENGTH_SHORT).show();
    }



    public void limpiarTexto(View v) {
        editIdTipoDocente.setText("");
        editNombre.setText("");
    }
}

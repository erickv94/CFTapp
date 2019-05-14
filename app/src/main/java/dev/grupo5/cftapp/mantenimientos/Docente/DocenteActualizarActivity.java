package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.modelos.Docente;

public class DocenteActualizarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_docente_actualizar);
        helper = new DocenteDB(this);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

    }


    public void actualizarDocente(View v) {

     /*   Docente docente = new Docente();
        docente.setIdDocente (Integer.parseInt(editIdDocente.getText().toString()));
        docente.setIdTipoDocente(Integer.parseInt(editCodDocente.getText().toString()));
        docente.setNombre(editNombre.getText().toString());
        docente.setApellidos(editApellido.getText().toString());
        docente.setCodDocente(editCodDocente.getText().toString());
        docente.setSexo(editSexo.getText().toString());

        String estado = helper.actualizar(docente);

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
*/

    }
    public void limpiarTextoDocente(View v) {
        editIdDocente.setText("");
        editIdTipoDocente.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
    }
}

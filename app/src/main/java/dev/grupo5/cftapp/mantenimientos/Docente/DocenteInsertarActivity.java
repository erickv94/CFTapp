package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.modelos.Docente;

public class DocenteInsertarActivity extends AppCompatActivity {

    DocenteDB helper;
    EditText editIdDocente;
    EditText editIdTipoDocente;
    EditText editNombre;
    EditText editApellido;
    EditText editCodDocente;
    EditText editSexo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new DocenteDB(this);

       // editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

    }
    public void insertar(View v) {

        //Integer idDocente=Integer.parseInt(editIdDocente.getText().toString());
        Integer idTipoDocente=Integer.parseInt(editIdTipoDocente.getText().toString());
        String nombre=editNombre.getText().toString();
        String apellidos=editApellido.getText().toString();
        String codDocente=editCodDocente.getText().toString();
        String sexo=editSexo.getText().toString();
        String regInsertados;

        Docente docente=new Docente();
        //docente.setIdDocente(idDocente);
        docente.setIdTipoDocente(idTipoDocente);
        docente.setNombre(nombre);
        docente.setApellidos(apellidos);
        docente.setCodDocente(codDocente);
        docente.setSexo(sexo);
        //helper.abrir();
        regInsertados=helper.insertar(docente);
        //helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdTipoDocente.setText("");
        //editIdDocente.setText("");
        editApellido.setText("");
        editCodDocente.setText("");
        editSexo.setText("");
        editNombre.setText("");
    }

}

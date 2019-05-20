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
        setTitle(R.string.docenteinsert);

        //editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdTipoDocente = (EditText) findViewById(R.id.editIdTipoDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editSexo = (EditText) findViewById(R.id.editSexo);

    }
    public void insertarDocente(View v) {

        DocenteDB docenteDB=new DocenteDB(this);
        Docente docente=new Docente();
        String cantidad;
        docente.setIdTipoDocente(Integer.parseInt(editIdDocente.getText().toString()));
        docente.setNombre(editNombre.getText().toString());
        docente.setApellidos(editApellido.getText().toString());
        docente.setCodDocente(editCodDocente.getText().toString());
        docente.setSexo(editSexo.getText().toString());

        cantidad=docenteDB.insertar(docente);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();





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

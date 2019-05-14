package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.modelos.Docente;

public class DocenteEliminarActivity extends AppCompatActivity {

    EditText editIdDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        setTitle(R.string.docentedelete);

        editIdDocente = findViewById(R.id.editIdDocente);
    }

    public void eliminarDocente(View view) {
        Docente docente = new Docente();
        DocenteDB docenteDB = new DocenteDB(this);
        String resultado;

        docente.setIdDocente(Integer.parseInt(editIdDocente.getText().toString()));
        resultado = docenteDB.eliminar(docente);

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

    }
}



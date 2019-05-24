package dev.grupo5.cftapp.mantenimientos.Docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.modelos.Docente;

public class DocenteEliminarActivity extends AppCompatActivity {

    EditText editIdDocente;
    private static final int  permiso=16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        setTitle(R.string.docentedelete);
        verificarPermisos();
        editIdDocente = findViewById(R.id.editIdDocente);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.docentedelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarDocente(View view) {
        Docente docente = new Docente();
        DocenteDB docenteDB = new DocenteDB(this);
        String resultado;

        docente.setIdDocente(Integer.parseInt(editIdDocente.getText().toString()));
        resultado = docenteDB.eliminar(docente);

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        editIdDocente.setText("");
    }
}



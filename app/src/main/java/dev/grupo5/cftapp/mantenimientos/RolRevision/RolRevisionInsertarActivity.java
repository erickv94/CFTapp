package dev.grupo5.cftapp.mantenimientos.RolRevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionInsertarActivity extends AppCompatActivity {
    RolRevisionDB rolRevisionDB;
    EditText nombrerolrevision;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_revision_insertar);
        setTitle(R.string.rolrevisioninsert);

        nombrerolrevision = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void insertarRolRevision(View v){
        RolRevision rolRevision = new RolRevision();
        String resul;
        rolRevision.setNombre(nombrerolrevision.getText().toString());
        rolRevision.setDescripcion(descripcion.getText().toString());

        resul = rolRevisionDB.insertar(rolRevision);
        Toast.makeText(this, resul, Toast.LENGTH_SHORT).show();
    }

    public void limpiarRolRevision(View v){
        nombrerolrevision.setText("");
        descripcion.setText("");
    }
}

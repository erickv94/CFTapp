package dev.grupo5.cftapp.mantenimientos.RolRevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionActualizarActivity extends AppCompatActivity {
    RolRevisionDB rolRevisionDB;
    EditText idrolText;
    EditText nombreText;
    EditText descripcionText;
    private static final int  permiso=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_revision_actualizar);
        setTitle(R.string.rolrevisionupdate);

        idrolText = findViewById(R.id.idrol);
        nombreText = (EditText) findViewById(R.id.nombrerolrevision);
        descripcionText = (EditText) findViewById(R.id.descripcionrolrevision);
    }

    public void actualizarRolRevision(View v){
        RolRevision rolRevision = new RolRevision();
        rolRevisionDB = new RolRevisionDB(this);
        String resultado;
        rolRevision.setIdRol(Integer.parseInt(idrolText.getText().toString()));
        rolRevision.setNombre(nombreText.getText().toString());
        rolRevision.setDescripcion(descripcionText.getText().toString());

        resultado = rolRevisionDB.actualizar(rolRevision);
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarRolRevision(View v){
        nombreText.setText("");
        descripcionText.setText("");
    }
}

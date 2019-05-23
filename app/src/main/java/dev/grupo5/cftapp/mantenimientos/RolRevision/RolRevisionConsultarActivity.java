package dev.grupo5.cftapp.mantenimientos.RolRevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionConsultarActivity extends AppCompatActivity {
    RolRevisionDB rolRevisionDB;
    EditText idRolText;
    EditText nombre;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_revision_consultar);
        setTitle(R.string.rolrevisionread);

        idRolText = (EditText) findViewById(R.id.idbusqueda);
        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void consultarRolRevision(View v){
        rolRevisionDB = new RolRevisionDB(this);
        RolRevision rolRevision;
        rolRevision=rolRevisionDB.consultar(idRolText.getText().toString());
        if (rolRevision != null){
            nombre.setText(rolRevision.getNombre());
            descripcion.setText(rolRevision.getDescripcion());
            return;
        } else
            Toast.makeText(this,"Rol Revision con idrol" + idRolText.getText().toString() + "no existe",
                Toast.LENGTH_SHORT).show();
        }

        public void limpiarRolRevision(View v){
            idRolText.setText("");
            nombre.setText("");
            descripcion.setText("");
        }
    }


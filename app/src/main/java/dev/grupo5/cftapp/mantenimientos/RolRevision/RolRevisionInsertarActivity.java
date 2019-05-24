package dev.grupo5.cftapp.mantenimientos.RolRevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionInsertarActivity extends AppCompatActivity {
    RolRevisionDB rolRevisionDB;
    EditText nombrerolrevisionText;
    EditText descripcionText;
    private static final int  permiso=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_revision_insertar);
        setTitle(R.string.rolrevisioninsert);
        verificarPermisos();

        rolRevisionDB = new RolRevisionDB(this);

        nombrerolrevisionText = (EditText) findViewById(R.id.nombrerolrevision);
        descripcionText = (EditText) findViewById(R.id.descripcionrolrevision);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.rolrevisioninsert), Toast.LENGTH_LONG).show();
        }

    }

    public void insertarRolRevision(View v){
        RolRevision rolRevision = new RolRevision();
        String resul;
        String nombrerol = nombrerolrevisionText.getText().toString();
        String descripcion = descripcionText.getText().toString();
        rolRevision.setNombre(nombrerol);
        rolRevision.setDescripcion(descripcion);

        resul = rolRevisionDB.insertar(rolRevision);
        Toast.makeText(this, resul, Toast.LENGTH_SHORT).show();
    }

    public void limpiarRolRevision(View v){
        nombrerolrevisionText.setText("");
        descripcionText.setText("");
    }
}

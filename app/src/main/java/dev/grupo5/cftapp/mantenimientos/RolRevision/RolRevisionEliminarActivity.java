package dev.grupo5.cftapp.mantenimientos.RolRevision;

import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.modelos.RolRevision;

public class RolRevisionEliminarActivity extends AppCompatActivity {
    RolRevisionDB rolRevisionDB;
    EditText idrolText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_revision_eliminar);
        setTitle(R.string.rolrevisiondelete);

        idrolText = (EditText) findViewById(R.id.idrol);
    }

    public void eliminarRolRevision(View v){
        rolRevisionDB = new RolRevisionDB(this);
        RolRevision rolRevision = new RolRevision();
        try {
            rolRevision.setIdRol(Integer.valueOf(idrolText.getText().toString()));
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        String resul = rolRevisionDB.eliminar(rolRevision);
        Toast.makeText(this, resul, Toast.LENGTH_SHORT).show();
    }

    public void limpiarRolRevision(View v){
        idrolText.setText("");
    }
}

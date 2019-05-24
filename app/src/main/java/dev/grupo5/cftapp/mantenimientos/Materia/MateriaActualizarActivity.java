package dev.grupo5.cftapp.mantenimientos.Materia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;

public class MateriaActualizarActivity extends AppCompatActivity {
    MateriaDB materiaDB;
    EditText idmatText;
    EditText codigomateriaText;
    EditText nombremateriaText;
    EditText uvsText;
    private static final int permiso=39;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar);
        setTitle(R.string.materiaupdate);
        verificarPermisos();
        idmatText = findViewById(R.id.editIdMateria);
        codigomateriaText = (EditText) findViewById(R.id.codigomateria);
        nombremateriaText = (EditText) findViewById(R.id.nombremateria);
        uvsText = (EditText) findViewById(R.id.uvs);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.materiaupdate), Toast.LENGTH_LONG).show();
        }

    }

    public void actualizarMateria (View v){
        Materia materia = new Materia();
        materiaDB = new MateriaDB(this);
        String resultado;
        materia.setIdMateria(Integer.parseInt(idmatText.getText().toString()));
        materia.setCodigoMateria(codigomateriaText.getText().toString());
        materia.setNombre(nombremateriaText.getText().toString());
        materia.setUvs(Integer.parseInt(uvsText.getText().toString()));

        resultado = materiaDB.actualizar(materia);
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateria(View v){
        codigomateriaText.setText("");
        nombremateriaText.setText("");
        uvsText.setText("");
    }
}

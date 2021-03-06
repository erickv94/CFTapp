package dev.grupo5.cftapp.mantenimientos.MateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.modelos.MateriaCiclo;

public class MateriaCicloEliminarActivity extends AppCompatActivity {
    EditText idmatcText;
    private static final int permiso=44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_eliminar);
        setTitle(R.string.materiaciclodelete);
        verificarPermisos();

        idmatcText = findViewById(R.id.idmatciclo);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.materiaciclodelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarMateriaCiclo(View v){
        MateriaCiclo materiaCiclo = new MateriaCiclo();
        MateriaCicloDB materiaCicloDB = new MateriaCicloDB(this);

        try {
            materiaCiclo.setIdMatCiclo(Integer.parseInt(idmatcText.getText().toString()));
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String resultado = materiaCicloDB.eliminar(materiaCiclo);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
}

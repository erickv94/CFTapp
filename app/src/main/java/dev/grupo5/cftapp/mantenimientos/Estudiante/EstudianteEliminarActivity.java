package dev.grupo5.cftapp.mantenimientos.Estudiante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.modelos.Estudiante;

public class EstudianteEliminarActivity extends AppCompatActivity {
    EditText carnetEditText;
    private static final int permiso = 64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_eliminar);
        setTitle(R.string.estudiantesdelete);
        verificarPermisos();
        carnetEditText= findViewById(R.id.idestudiante);
    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.estudiantesdelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarEstudiante(View view){

        EstudianteDB estudianteDB= new EstudianteDB(this);
        Estudiante estudiante= new Estudiante();
        estudiante.setCarnet(carnetEditText.getText().toString());
        String resultado=estudianteDB.eliminar(estudiante);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }

}

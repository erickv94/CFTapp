package dev.grupo5.cftapp.mantenimientos.Estudiante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.modelos.Estudiante;

public class EstudianteConsultarActivity extends AppCompatActivity {
    EditText carnetIdText;
    EditText nombreText;
    EditText apellidoText;
    EditText carnetText;
    EditText sexoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar);
        setTitle(R.string.estudiantesread);
        carnetIdText= findViewById(R.id.idbusqueda);
        nombreText= findViewById(R.id.nombreestudiante);
        apellidoText=findViewById(R.id.apellidoestudiante);
        carnetText= findViewById(R.id.carnetestudiante);
        sexoText= findViewById(R.id.sexoestudiante);
    }

    public void consultarEstudiante(View view) {
        EstudianteDB estudianteDB= new EstudianteDB(this);
        Estudiante estudiante;
        estudiante=estudianteDB.consultar(carnetIdText.getText().toString());
        if(estudiante!=null){
            nombreText.setText(estudiante.getNombres());
            apellidoText.setText(estudiante.getApellidos());
            carnetText.setText(estudiante.getCarnet());
            sexoText.setText(estudiante.getSexo().equals("F")?"Femenino":"Masculino");
            return;
        }
        Toast.makeText(this,getResources().getString(R.string.consulta_no_existe)+": "
                +carnetIdText.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}

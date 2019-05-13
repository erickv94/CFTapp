package dev.grupo5.cftapp.mantenimientos.Estudiante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.modelos.Estudiante;

public class EstudianteActualizarActivity extends AppCompatActivity {
    EditText carnetText;
    EditText nombreText;
    EditText apellidoText;
    private boolean masculino=true;
    private  boolean femenimo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_actualizar);
        setTitle(R.string.estudiantesupdate);
        carnetText=findViewById(R.id.carnetestudiante);
        nombreText=findViewById(R.id.nombreestudiante);
        apellidoText=findViewById(R.id.apellidoestudiante);


    }

    public void radioOnclick(View view){
        boolean checked=((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.masculino_estudiante:
                if (checked)
                    masculino=true;
                femenimo=false;
                break;
            case R.id.feminino_estudiante:
                if (checked)
                    femenimo=true;
                masculino=false;
                break;
        }

    }

    public void actualizarEstudiante(View view){
        EstudianteDB estudianteDB= new EstudianteDB(this);
        Estudiante estudiante= new Estudiante();
        String resultado;
        estudiante.setSexo(masculino?"M":"F");
        estudiante.setNombres(nombreText.getText().toString());
        estudiante.setApellidos(apellidoText.getText().toString());
        estudiante.setCarnet(carnetText.getText().toString());

        resultado= estudianteDB.actualizar(estudiante);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
}

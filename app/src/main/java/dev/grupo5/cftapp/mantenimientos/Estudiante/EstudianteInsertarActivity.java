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

public class EstudianteInsertarActivity extends AppCompatActivity {
    private boolean masculino=true;
    private boolean femenimo=false;
    EditText carnetText;
    EditText nombreText;
    EditText apellidoText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_insertar);
        setTitle(R.string.estudiantesinsert);
        carnetText=findViewById(R.id.carnetestudiante);
        nombreText=findViewById(R.id.nombreestudiante);
        apellidoText=findViewById(R.id.apellidoestudiante);

    }

    public void radioOnclick(View view) {
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

    public void insertarEstudiante(View view){
        EstudianteDB estudianteDB= new EstudianteDB(this);
        Estudiante estudiante= new Estudiante();
        String resultado;
        estudiante.setCarnet(carnetText.getText().toString());
        estudiante.setNombres(nombreText.getText().toString());
        estudiante.setApellidos(apellidoText.getText().toString());
        if(masculino){
            estudiante.setSexo("M");
        }
        else{
            estudiante.setSexo("F");
        }

        resultado=estudianteDB.insertar(estudiante);
        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View view){
        carnetText.setText("");
        nombreText.setText("");
        apellidoText.setText("");

    }
}

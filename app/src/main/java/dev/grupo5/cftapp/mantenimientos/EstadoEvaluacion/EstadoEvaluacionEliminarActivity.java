package dev.grupo5.cftapp.mantenimientos.EstadoEvaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.EstadoEvaluacionDB;
import dev.grupo5.cftapp.modelos.EstadoEvaluacion;

public class EstadoEvaluacionEliminarActivity extends AppCompatActivity {
    EditText idText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_evaluacion_eliminar);
        setTitle(R.string.estadodelete);
        idText= findViewById(R.id.idestado);

    }


    public void eliminarEstado(View view){
        EstadoEvaluacionDB estadoEvaluacionDB= new EstadoEvaluacionDB(this);
        EstadoEvaluacion estadoEvaluacion= new EstadoEvaluacion();
        String resultado;

        if(!idText.getText().toString().isEmpty()) {
            estadoEvaluacion.setIdEstado(Integer.valueOf(idText.getText().toString()));
             resultado = estadoEvaluacionDB.eliminar(estadoEvaluacion);

        }
        else {
            estadoEvaluacion.setIdEstado(Integer.valueOf(idText.getText().toString()));
             resultado = estadoEvaluacionDB.eliminar(estadoEvaluacion);
        }

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

    }


}

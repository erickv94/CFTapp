package dev.grupo5.cftapp.mantenimientos.Estudiantewbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class EstudianteActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_actualizar_wbs);
        setTitle(getResources().getString(R.string.estudiantesupdate)+" webservice");
    }
}

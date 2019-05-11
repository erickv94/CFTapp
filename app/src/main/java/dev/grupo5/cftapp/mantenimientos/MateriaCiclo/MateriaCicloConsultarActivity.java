package dev.grupo5.cftapp.mantenimientos.MateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class MateriaCicloConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo_consultar);
        setTitle(R.string.materiacicloread);

    }
}

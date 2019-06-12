package dev.grupo5.cftapp.mantenimientos.Materiawbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class MateriaActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar_wbs);
        setTitle(getResources().getString(R.string.materiaupdate)+" webservice");
    }
}

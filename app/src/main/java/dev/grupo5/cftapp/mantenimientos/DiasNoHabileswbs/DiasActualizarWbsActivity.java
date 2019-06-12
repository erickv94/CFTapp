package dev.grupo5.cftapp.mantenimientos.DiasNoHabileswbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DiasActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_actualizar_wbs);
        setTitle(getResources().getString(R.string.diasupdate)+" webservice");

    }

}

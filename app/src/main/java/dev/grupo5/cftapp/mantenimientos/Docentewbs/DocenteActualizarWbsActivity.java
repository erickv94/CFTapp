package dev.grupo5.cftapp.mantenimientos.Docentewbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DocenteActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar_wbs);
        setTitle(getResources().getString(R.string.docenteupdate)+" webservice");

    }
}

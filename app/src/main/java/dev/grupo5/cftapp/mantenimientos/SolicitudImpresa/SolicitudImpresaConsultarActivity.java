package dev.grupo5.cftapp.mantenimientos.SolicitudImpresa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class SolicitudImpresaConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresa_consultar);
        setTitle(R.string.solicitudesread);

    }
}

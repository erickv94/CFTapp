package dev.grupo5.cftapp.mantenimientos.TipoDocentewbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class TipoDocenteActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_actualizar_wbs);
        setTitle(getResources().getString(R.string.tipodocenteupdate)+" webservice");
    }
}

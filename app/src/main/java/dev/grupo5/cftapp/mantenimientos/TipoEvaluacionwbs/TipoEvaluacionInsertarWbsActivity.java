package dev.grupo5.cftapp.mantenimientos.TipoEvaluacionwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class TipoEvaluacionInsertarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_insertar_wbs);
        setTitle(getResources().getString(R.string.tipoevaluacioninsert)+" webservice");
    }
}

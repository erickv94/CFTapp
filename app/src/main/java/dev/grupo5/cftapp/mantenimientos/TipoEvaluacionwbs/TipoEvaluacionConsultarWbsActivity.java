package dev.grupo5.cftapp.mantenimientos.TipoEvaluacionwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.os.StrictMode;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;

public class TipoEvaluacionConsultarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_consultar_wbs);
        setTitle(getResources().getString(R.string.tipoevaluacionread)+" webservice");
    }
}

package dev.grupo5.cftapp.mantenimientos.Docentewbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DocenteConsultarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar_wbs);
        setTitle(getResources().getString(R.string.docenteread)+" webservice");

    }
}

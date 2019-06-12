package dev.grupo5.cftapp.mantenimientos.Docentewbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DocenteInsertarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar_wbs);
        setTitle(getResources().getString(R.string.docenteinsert)+" webservice");

    }
}

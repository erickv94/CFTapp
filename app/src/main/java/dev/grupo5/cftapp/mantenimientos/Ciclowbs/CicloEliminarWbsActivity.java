package dev.grupo5.cftapp.mantenimientos.Ciclowbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class CicloEliminarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar_wbs);
        setTitle(getResources().getString(R.string.ciclodelete)+" webservice");

    }
}

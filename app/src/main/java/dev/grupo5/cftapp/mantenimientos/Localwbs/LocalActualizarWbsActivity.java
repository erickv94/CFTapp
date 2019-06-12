package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class LocalActualizarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizarwbs);
        setTitle(getResources().getString(R.string.localupdate)+" webservice");
    }
}

package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class LocalConsultarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar_wbs);
        setTitle(getResources().getString(R.string.localread)+" webservice");
    }
}

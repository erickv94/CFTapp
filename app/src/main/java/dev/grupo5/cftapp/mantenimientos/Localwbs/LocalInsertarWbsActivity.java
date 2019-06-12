package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class LocalInsertarWbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar_wbs);
        setTitle(getResources().getString(R.string.localinsert)+" webservice");
    }
}

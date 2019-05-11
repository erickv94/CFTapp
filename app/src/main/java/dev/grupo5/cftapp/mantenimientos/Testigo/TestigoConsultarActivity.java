package dev.grupo5.cftapp.mantenimientos.Testigo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class TestigoConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testigo_consultar);
        setTitle(R.string.testigoread);

    }
}

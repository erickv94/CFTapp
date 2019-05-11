package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class TipoTramiteEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_eliminar);
        setTitle(R.string.tipotramitedelete);

    }
}

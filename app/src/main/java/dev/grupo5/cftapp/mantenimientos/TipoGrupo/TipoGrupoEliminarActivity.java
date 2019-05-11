package dev.grupo5.cftapp.mantenimientos.TipoGrupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class TipoGrupoEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_eliminar);
        setTitle(R.string.tipogrupodelete);

    }
}

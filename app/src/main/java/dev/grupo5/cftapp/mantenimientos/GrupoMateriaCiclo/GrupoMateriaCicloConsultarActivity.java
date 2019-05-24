package dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class GrupoMateriaCicloConsultarActivity extends AppCompatActivity {
    private static final int permiso=46;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_consultar);
        setTitle(R.string.grupomateriacicloread);

    }
}

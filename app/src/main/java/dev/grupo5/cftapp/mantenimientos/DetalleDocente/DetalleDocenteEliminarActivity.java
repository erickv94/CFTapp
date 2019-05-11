package dev.grupo5.cftapp.mantenimientos.DetalleDocente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DetalleDocenteEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_eliminar);
        setTitle(R.string.detalledocentedelete);

    }
}

package dev.grupo5.cftapp.mantenimientos.DetalleRevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DetalleRevisionEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_revision_eliminar);
        setTitle(R.string.detallerevisiondelete);

    }
}

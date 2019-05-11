package dev.grupo5.cftapp.mantenimientos.DetalleLocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.grupo5.cftapp.R;

public class DetalleLocalEliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local_eliminar);
        setTitle(R.string.detallelocaldelete);

    }
}

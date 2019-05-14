package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteInsertarActivity extends AppCompatActivity {

    TipoTramiteDB helper;
    EditText editNombre;
    EditText editDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_insertar);
        setTitle(R.string.tipotramiteinsert);

        helper = new TipoTramiteDB(this);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
    }

    public void insertarTipoTramite(View v){
        String tipotramitenombre = editNombre.getText().toString();
        String tipotramitedescripcion = editDescripcion.getText().toString();
        String resultado;
        TipoTramite tipoTramite = new TipoTramite();

        tipoTramite.setNombre(tipotramitenombre);
        tipoTramite.setDescripcion(tipotramitedescripcion);
        resultado = helper.insertar(tipoTramite);
        Toast.makeText(this,resultado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTipoTramite(View v){
        editNombre.setText("");
        editDescripcion.setText("");
    }
}

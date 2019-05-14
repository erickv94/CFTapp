package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteConsultarActivity extends AppCompatActivity {
    TipoTramiteDB tipoTramiteDB;
    EditText idtipotramite;
    EditText nombretipotramite;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_consultar);
        setTitle(R.string.tipotramiteread);

        tipoTramiteDB = new TipoTramiteDB(this);

        idtipotramite = (EditText) findViewById(R.id.idtipotramite);
        nombretipotramite = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void consultarTipoTramite(View v){
        TipoTramite tipoTramite = tipoTramiteDB.consultar(idtipotramite.getText().toString());
        if (tipoTramite!= null){
            nombretipotramite.setText(tipoTramite.getNombre());
            descripcion.setText(tipoTramite.getDescripcion());
            return;
        } else
        Toast.makeText(this, "Tipo Tramite con id tipo tramite" + idtipotramite.getText().toString()
        + "no encontrado", Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        nombretipotramite.setText("");
        descripcion.setText("");
    }
}

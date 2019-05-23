package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteActualizarActivity extends AppCompatActivity {

    TipoTramiteDB tipoTramiteDB;
    EditText idtipotText;
    EditText nombretipotramite;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_actualizar);
        setTitle(R.string.tipotramiteupdate);

        idtipotText = findViewById(R.id.idtipotramite);
        nombretipotramite = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void actualizarTipoTramite(View v){
        tipoTramiteDB = new TipoTramiteDB(this);
        TipoTramite tipoTramite = new TipoTramite();
        String resul;
        tipoTramite.setIdTipoTramite(Integer.parseInt(idtipotText.getText().toString()));
        tipoTramite.setNombre(nombretipotramite.getText().toString());
        tipoTramite.setDescripcion(descripcion.getText().toString());

        resul = tipoTramiteDB.actualizar(tipoTramite);
        Toast.makeText(this,resul,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        nombretipotramite.setText("");
        descripcion.setText("");
    }
}

package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteEliminarActivity extends AppCompatActivity {
    TipoTramiteDB tipoTramiteDB;
    EditText idtipoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_eliminar);
        setTitle(R.string.tipotramitedelete);

        idtipoText = (EditText) findViewById(R.id.idtipotramite);
    }

    public void eliminarTipoTramite(View v){
        tipoTramiteDB = new TipoTramiteDB(this);
        TipoTramite tipoTramite = new TipoTramite();

        try {
            tipoTramite.setIdTipoTramite(Integer.valueOf(idtipoText.getText().toString()));
        } catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        String resul = tipoTramiteDB.eliminar(tipoTramite);
        Toast.makeText(this, resul, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTipoTramite(View v){
        idtipoText.setText("");
    }
}


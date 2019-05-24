package dev.grupo5.cftapp.mantenimientos.TipoTramite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.modelos.TipoTramite;

public class TipoTramiteConsultarActivity extends AppCompatActivity {
    TipoTramiteDB tipoTramiteDB;
    EditText idtipotramite;
    EditText nombretipotramite;
    EditText descripcion;
    private static final int  permiso=22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_consultar);
        setTitle(R.string.tipotramiteread);
        verificarPermisos();

        tipoTramiteDB = new TipoTramiteDB(this);

        idtipotramite = (EditText) findViewById(R.id.idbusqueda);
        nombretipotramite = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipotramiteread), Toast.LENGTH_LONG).show();
        }

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

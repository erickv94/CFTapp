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

public class TipoTramiteActualizarActivity extends AppCompatActivity {

    TipoTramiteDB tipoTramiteDB;
    EditText idtipotText;
    EditText nombretipotramite;
    EditText descripcion;
    private static final int  permiso=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tramite_actualizar);
        setTitle(R.string.tipotramiteupdate);
        verificarPermisos();

        idtipotText = findViewById(R.id.idtipotramite);
        nombretipotramite = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipotramiteupdate), Toast.LENGTH_LONG).show();
        }

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

package dev.grupo5.cftapp.mantenimientos.TipoEvaluacion;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;

import dev.grupo5.cftapp.modelos.TipoEvaluacion;


public class TipoEvaluacionInsertarActivity extends AppCompatActivity {


    TipoEvaluacionDB helper;

    EditText editNombre;
    EditText editDescripcion;
    private static final int permiso = 49;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_insertar);

        setTitle(R.string.tipoevaluacioninsert);
        verificarPermisos();

        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.tipoevaluacioninsert), Toast.LENGTH_LONG).show();
        }

    }


    public void insertarTipoEvaluacion(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoDocente.getText().toString());
        TipoEvaluacionDB tipoEvaluacionDB=new TipoEvaluacionDB(this);
        TipoEvaluacion tipoEvaluacion=new TipoEvaluacion();
        String cantidad;

        tipoEvaluacion.setNombre(editNombre.getText().toString());
        tipoEvaluacion.setDescripcion(editDescripcion.getText().toString());

        cantidad=tipoEvaluacionDB.insertar(tipoEvaluacion);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        //editIdTipoDocente.setText("");
        editNombre.setText("");
        editDescripcion.setText("");
    }
}

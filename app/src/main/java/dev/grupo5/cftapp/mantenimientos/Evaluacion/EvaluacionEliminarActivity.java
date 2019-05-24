package dev.grupo5.cftapp.mantenimientos.Evaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.modelos.Evaluacion;

public class EvaluacionEliminarActivity extends AppCompatActivity {
    EditText idText;
    EvaluacionDB evaluacionDB;
    private static final int permiso = 56;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_eliminar);
        setTitle(R.string.evaluaciondelete);
        verificarPermisos();

        evaluacionDB = new EvaluacionDB(this);
        idText = findViewById(R.id.idevaluacion);
    }

    public void eliminarEvaluacion(View v){
        Evaluacion evaluacion = new Evaluacion();
        try {
            evaluacion.setIdEvaluacion(Integer.valueOf(idText.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String re = evaluacionDB.eliminar(evaluacion);
        Toast.makeText(this,re,Toast.LENGTH_SHORT).show();
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.evaluaciondelete), Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarEvaluacion(View v){
        idText.setText("");
    }
}

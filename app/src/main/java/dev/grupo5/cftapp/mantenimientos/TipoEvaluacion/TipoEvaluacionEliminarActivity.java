package dev.grupo5.cftapp.mantenimientos.TipoEvaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;

public class TipoEvaluacionEliminarActivity extends AppCompatActivity {


    EditText editIdTipoEvaluacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion_eliminar);
        setTitle(R.string.tipoevaluaciondelete);

        editIdTipoEvaluacion=findViewById(R.id.editIdTipoEvaluacion);
    }

    public void eliminarTipoEvaluacion(View view){
        TipoEvaluacion tipoEvaluacion= new TipoEvaluacion();
        TipoEvaluacionDB tipoEvaluacionDB= new TipoEvaluacionDB(this);
        String resultado;

        tipoEvaluacion.setIdTipoEvaluacion(Integer.parseInt(editIdTipoEvaluacion.getText().toString()));
        resultado=tipoEvaluacionDB.eliminar(tipoEvaluacion);

        Toast.makeText(this,resultado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        //editIdTipoDocente.setText("");
        editIdTipoEvaluacion.setText("");

}
}

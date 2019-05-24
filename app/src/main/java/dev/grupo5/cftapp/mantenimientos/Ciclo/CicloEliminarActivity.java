package dev.grupo5.cftapp.mantenimientos.Ciclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloEliminarActivity extends AppCompatActivity {

    EditText editIdCiclo;
    private static final int  permiso=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar);
        setTitle(R.string.ciclodelete);
        verificarPermisos();
        editIdCiclo=findViewById(R.id.editIdCiclo);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.ciclodelete), Toast.LENGTH_LONG).show();
        }

    }


    public void eliminarCiclo(View view){
        Ciclo ciclo= new Ciclo();
        CicloDB cicloDB= new CicloDB(this);
        String resultado;

        ciclo.setIdCiclo(Integer.parseInt(editIdCiclo.getText().toString()));
        resultado=cicloDB.eliminar(ciclo);

        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {
        editIdCiclo.setText("");
    }
}

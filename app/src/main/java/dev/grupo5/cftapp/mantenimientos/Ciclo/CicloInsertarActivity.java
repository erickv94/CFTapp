package dev.grupo5.cftapp.mantenimientos.Ciclo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.modelos.Ciclo;

public class CicloInsertarActivity extends AppCompatActivity {

    CicloDB helper;
    EditText editIdCiclo;
    EditText editCiclo;
    EditText editAnio;
    private static final int  permiso=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        verificarPermisos();
        setTitle(R.string.cicloinsert);
        editCiclo = findViewById(R.id.editCiclo);
        editAnio = findViewById(R.id.editAnio);



    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                            +getResources().getString(R.string.cicloinsert), Toast.LENGTH_LONG).show();
        }

    }

    public void insertarCiclo(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoDocente.getText().toString());
        CicloDB cicloDB=new CicloDB(this);
        Ciclo ciclo=new Ciclo();
        String cantidad;
        ciclo.setCiclo(Integer.parseInt(editCiclo.getText().toString()));
        ciclo.setAnio(Integer.parseInt(editAnio.getText().toString()));

        cantidad=cicloDB.insertar(ciclo);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        //editIdTipoDocente.setText("");
        editCiclo.setText("");
        editAnio.setText("");
    }

}

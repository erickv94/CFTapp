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

public class CicloConsultarActivity extends AppCompatActivity {

    EditText editIdCiclo;
    EditText editCiclo;
    EditText editAnio;
    private static final int  permiso=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_consultar);
        setTitle(R.string.cicloread);
        verificarPermisos();
        editIdCiclo=findViewById(R.id.editIdCiclo);
        editCiclo=findViewById(R.id.editCiclo);
        editAnio=findViewById(R.id.editAnio);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.cicloread), Toast.LENGTH_LONG).show();
        }

    }

    public void consultarCiclo(View view) {
        CicloDB cicloDB = new CicloDB(this);
        Ciclo ciclo = cicloDB.consultar(editIdCiclo.getText().toString());
      /*  try {
            System.out.println(ciclo.getIdTipoDocente() + "," + ciclo.getNombre());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        if(ciclo!=null){
            editCiclo.setText(String.valueOf(ciclo.getCiclo()));
            editAnio.setText(String.valueOf(ciclo.getAnio()));
            return;
        }

        Toast.makeText(this,"Resultado no existe" ,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdCiclo.setText("");
        editCiclo.setText("");
        editAnio.setText("");
    }
}

package dev.grupo5.cftapp.mantenimientos.Testigo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.TestigoDB;
import dev.grupo5.cftapp.modelos.Testigo;

public class TestigoEliminarActivity extends AppCompatActivity {
    EditText idText;
    private static final int permiso = 88;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testigo_eliminar_actividad);
        setTitle(R.string.testigodelete);
        verificarPermisos();
        idText = findViewById(R.id.idtestigo);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.testigodelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarTestigo(View view){
        TestigoDB testigoDB= new TestigoDB(this);
        Testigo testigo= new Testigo();
        testigo.setIdTestigo(Integer.valueOf(idText.getText().toString()));
        String resultado=testigoDB.eliminar(testigo);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View view){
        idText.setText("");
    }
}

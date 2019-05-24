package dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;

public class GrupoMateriaCicloEliminarActivity extends AppCompatActivity {
    private static final int permiso=48;
    GrupoMateriaCicloDB grupoMateriaCicloDB;
    EditText idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_eliminar);
        setTitle(R.string.grupomateriaciclodelete);
        verificarPermisos();
        idText = findViewById(R.id.idgrupo);
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);
    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.grupomateriaciclodelete), Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarGrupoMatCiclo(View v){
        GrupoMateriaCiclo grupoMateriaCiclo = new GrupoMateriaCiclo();
        try {
            grupoMateriaCiclo.setIdGrupo(Integer.parseInt(idText.getText().toString()));
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        String re = grupoMateriaCicloDB.eliminar(grupoMateriaCiclo);
        Toast.makeText(this,re,Toast.LENGTH_SHORT).show();
    }
}

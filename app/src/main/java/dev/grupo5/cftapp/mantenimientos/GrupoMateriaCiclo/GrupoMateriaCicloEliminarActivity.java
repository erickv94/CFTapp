package dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;

public class GrupoMateriaCicloEliminarActivity extends AppCompatActivity {
    private static final int permiso=35;
    GrupoMateriaCicloDB grupoMateriaCicloDB;
    EditText idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_eliminar);
        setTitle(R.string.grupomateriaciclodelete);

        idText = findViewById(R.id.idgrupo);
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);
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

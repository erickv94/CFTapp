package dev.grupo5.cftapp.mantenimientos.TipoGrupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.modelos.TipoGrupo;
import dev.grupo5.cftapp.R;

public class TipoGrupoConsultarActivity extends AppCompatActivity {

    EditText editIdTipoGrupo;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_consultar);
        setTitle(R.string.tipogruporead);

        editIdTipoGrupo=findViewById(R.id.editIdTipoGrupo);
        editNombre=findViewById(R.id.editNombre);
    }

    public void consultarTipoGrupo(View view) {
        TipoGrupoDB tipoGrupoDB= new TipoGrupoDB(this);
        TipoGrupo tipoGrupo = tipoGrupoDB.consultar(editIdTipoGrupo.getText().toString());
      /*  try {
            System.out.println(tipoGrupo.getIdTipoGrupo() + "," + tipoGrupo.getNombre());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        if(tipoGrupo!=null){
            editNombre.setText(tipoGrupo.getNombre());
            return;
        }

        Toast.makeText(this,"Resultado no existe" ,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdTipoGrupo.setText("");
        editNombre.setText("");
    }

}


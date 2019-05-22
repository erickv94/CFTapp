package dev.grupo5.cftapp.mantenimientos.TipoGrupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoActualizarActivity extends AppCompatActivity {

    EditText editIdTipoGrupo;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_actualizar);
        setTitle(R.string.tipogrupoupdate);
        editIdTipoGrupo=findViewById(R.id.editIdTipoGrupo);
        editNombre= findViewById(R.id.editNombre);

    }

    public void actualizarTipoGrupo(View view){
        TipoGrupo tipoDocente= new TipoGrupo();
        String registros;
        TipoGrupoDB tipoDocenteDB= new TipoGrupoDB(this);
        tipoDocente.setIdTipoGrupo(Integer.parseInt(editIdTipoGrupo.getText().toString()));
        tipoDocente.setNombre(editNombre.getText().toString());
        registros=tipoDocenteDB.actualizar(tipoDocente);
        Toast.makeText(this,registros, Toast.LENGTH_SHORT).show();
}

}

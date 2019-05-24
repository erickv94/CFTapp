package dev.grupo5.cftapp.mantenimientos.TipoGrupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.modelos.TipoGrupo;

public class TipoGrupoInsertarActivity extends AppCompatActivity {
    TipoGrupoDB helper;
    EditText editIdTipoGrupo;
    EditText editNombre;
    private static final int  permiso=33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_insertar);
        setTitle(R.string.tipogrupoinsert);

        editNombre = findViewById(R.id.editNombre);

    }

    public void insertarTipoGrupo(View v) {
        //Integer idtipodocente= Integer.parseInt(editIdTipoGrupo.getText().toString());
        TipoGrupoDB tipoGrupoDB=new TipoGrupoDB(this);
        TipoGrupo tipoDocente=new TipoGrupo();
        String cantidad;
        tipoDocente.setNombre(editNombre.getText().toString());

        cantidad=tipoGrupoDB.insertar(tipoDocente);
        Toast.makeText(this,cantidad,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        //editIdTipoGrupo.setText("");
        editNombre.setText("");
    }
}

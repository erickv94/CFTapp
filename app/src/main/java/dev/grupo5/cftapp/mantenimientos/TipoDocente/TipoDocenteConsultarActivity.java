package dev.grupo5.cftapp.mantenimientos.TipoDocente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.modelos.TipoDocente;

public class TipoDocenteConsultarActivity extends AppCompatActivity {
    EditText editIdTipoDocente;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_docente_consultar);
        setTitle(R.string.tipodocenteread);

        editIdTipoDocente=findViewById(R.id.editIdTipoDocente);
        editNombre=findViewById(R.id.editNombre);

    }

    public void consultarTipoDocente(View view) {
        TipoDocenteDB tipoDocenteDB = new TipoDocenteDB(this);
        TipoDocente tipoDocente = tipoDocenteDB.consultar(editIdTipoDocente.getText().toString());
      /*  try {
            System.out.println(tipoDocente.getIdTipoDocente() + "," + tipoDocente.getNombre());
        }catch (Exception e){
            e.printStackTrace();
        }*/

      if(tipoDocente!=null){
            editNombre.setText(tipoDocente.getNombre());
            return;
        }

        Toast.makeText(this,"Resultado no existe" ,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdTipoDocente.setText("");
        editNombre.setText("");
    }

}


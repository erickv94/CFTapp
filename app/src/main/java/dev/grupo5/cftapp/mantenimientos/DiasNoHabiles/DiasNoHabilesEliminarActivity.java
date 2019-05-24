package dev.grupo5.cftapp.mantenimientos.DiasNoHabiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DiasNoHabilesDB;
import dev.grupo5.cftapp.modelos.DiasNoHabiles;

public class DiasNoHabilesEliminarActivity extends AppCompatActivity {

    EditText editIdDias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_eliminar);
        setTitle(R.string.diasdelete);

        editIdDias=findViewById(R.id.editIdDias);

    }

    public  void eliminarDiasNoHabiles(View view){
        DiasNoHabilesDB diasNoHabilesDB= new DiasNoHabilesDB(this);
        DiasNoHabiles diasNoHabiles= new DiasNoHabiles();

        try {
            diasNoHabiles.setIdDias(Integer.valueOf(editIdDias.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String resultado=diasNoHabilesDB.eliminar(diasNoHabiles);
        Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdDias.setText("");
    }
}

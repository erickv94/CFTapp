package dev.grupo5.cftapp.mantenimientos.Materia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;

public class MateriaConsultarActivity extends AppCompatActivity {
    MateriaDB materiaDB;
    EditText codigomateriaTargetText;
    EditText nombremateriaText;
    EditText codigomateriaText;
    EditText uvsText;
    private static final int permiso=38;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);
        setTitle(R.string.materiaread);

        codigomateriaTargetText = (EditText) findViewById(R.id.idbusqueda);

        codigomateriaText = (EditText) findViewById(R.id.codigomateria);
        nombremateriaText = (EditText) findViewById(R.id.nombremateria);
        uvsText = (EditText) findViewById(R.id.uvs);
    }

    public void consultarMateria(View v){
        materiaDB = new MateriaDB(this);
        Materia materia;
        materia = materiaDB.consultar(codigomateriaTargetText.getText().toString());
        if (materia != null){
            codigomateriaText.setText(materia.getCodigoMateria());
            nombremateriaText.setText(materia.getNombre());
            uvsText.setText(String.valueOf(materia.getUvs()));
            return;
        } else
            Toast.makeText(this, "Materia con codigo materia" + codigomateriaTargetText.getText().toString()
            + "no existe", Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateria(View v){
        codigomateriaTargetText.setText("");
        nombremateriaText.setText("");
        codigomateriaText.setText("");
        uvsText.setText("");
    }
}

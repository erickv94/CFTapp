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
    EditText idmateriaText;
    EditText nombremateriaText;
    EditText codigomateriaText;
    EditText uvsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);
        setTitle(R.string.materiaread);

        idmateriaText = (EditText) findViewById(R.id.idbusqueda);
        nombremateriaText = (EditText) findViewById(R.id.nombremateria);
        codigomateriaText = (EditText) findViewById(R.id.codigomateria);
        uvsText = (EditText) findViewById(R.id.uvs);
    }

    public void consultarMateria(View v){
        MateriaDB materiaDB = new MateriaDB(this);
        Materia materia;
        materia = materiaDB.consultar(idmateriaText.getText().toString());
        if (materia != null){
            materia.setCodigoMateria(codigomateriaText.getText().toString());
            materia.setNombre(nombremateriaText.getText().toString());
            materia.setUvs(Integer.parseInt(uvsText.getText().toString()));
            return;
        }
        Toast.makeText(this, "Materia con id materia" + idmateriaText.getText().toString()
        + "no existe", Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateria(View v){
        idmateriaText.setText("");
        nombremateriaText.setText("");
        codigomateriaText.setText("");
        uvsText.setText("");
    }
}

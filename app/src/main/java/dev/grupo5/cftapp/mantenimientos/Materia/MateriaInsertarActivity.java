package dev.grupo5.cftapp.mantenimientos.Materia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;

public class MateriaInsertarActivity extends AppCompatActivity {
    MateriaDB materiaDB;
    EditText codigomateriaText;
    EditText nombremateriaText;
    EditText uvsText;
    private static final int permiso=37;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);
        setTitle(R.string.materiainsert);

        materiaDB = new MateriaDB(this);
        codigomateriaText = (EditText) findViewById(R.id.codigomateria);
        nombremateriaText = (EditText) findViewById(R.id.nombremateria);
        uvsText = (EditText) findViewById(R.id.uvs);
    }

    public void insertarMateria(View v){
        Materia materia = new Materia();
        String resultado;
        materia.setCodigoMateria(codigomateriaText.getText().toString());
        materia.setNombre(nombremateriaText.getText().toString());
        materia.setUvs(Integer.parseInt(uvsText.getText().toString()));

        resultado = materiaDB.insertar(materia);
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateria(View v){
        codigomateriaText.setText("");
        nombremateriaText.setText("");
        uvsText.setText("");
    }
}

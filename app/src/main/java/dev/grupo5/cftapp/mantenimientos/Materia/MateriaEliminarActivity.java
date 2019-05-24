package dev.grupo5.cftapp.mantenimientos.Materia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.modelos.Materia;

public class MateriaEliminarActivity extends AppCompatActivity {
    MateriaDB materiaDB;
    EditText codigomatText;
    private static final int permiso=40;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_eliminar);
        setTitle(R.string.materiadelete);

        codigomatText = (EditText) findViewById(R.id.codigomateria);
    }

    public void eliminarMateria(View v){
        materiaDB = new MateriaDB(this);
        Materia materia = new Materia();
        materia.setCodigoMateria(codigomatText.getText().toString());
        String resul = materiaDB.eliminar(materia);
        Toast.makeText(this, resul, Toast.LENGTH_SHORT).show();
    }

    public void limpiarMateria(View v){
        codigomatText.setText("");
    }
}

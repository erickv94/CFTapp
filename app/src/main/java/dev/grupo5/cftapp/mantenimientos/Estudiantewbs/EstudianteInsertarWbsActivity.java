package dev.grupo5.cftapp.mantenimientos.Estudiantewbs;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;

import dev.grupo5.cftapp.databaseWS.Estudiantews;

public class EstudianteInsertarWbsActivity extends AppCompatActivity {
    private boolean masculino=true;
    private boolean femenimo=false;
    EditText carnetText;
    EditText nombreText;
    EditText apellidoText;
    private static final int permiso = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_insertar_wbs);
        setTitle(getResources().getString(R.string.estudiantesinsert)+" webservice");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetText=findViewById(R.id.carnetestudiante);
        nombreText=findViewById(R.id.nombreestudiante);
        apellidoText=findViewById(R.id.apellidoestudiante);
        verificarPermisos();

    }
    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.estudiantesinsert), Toast.LENGTH_LONG).show();
        }

    }

    public void radioOnclick(View view) {
        boolean checked=((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.masculino_estudiante:
                if (checked)
                    masculino=true;
                femenimo=false;
                break;
            case R.id.feminino_estudiante:
                if (checked)
                    femenimo=true;
                masculino=false;
                break;
        }

    }

    public void insertarEstudiante(View view){
            String url="http://eisi.fia.ues.edu.sv/GPO10/VH14006/ws_estudiante_insert.php?" +
                    "nombre=" + nombreText.getText().toString()+
                    "&apellido=" + apellidoText.getText().toString() +
                    "&carnet="+carnetText.getText().toString() +
                    "&sexo=" + (masculino?"M":"F");
            String mensaje= Estudiantews.insertarEstudianteServidor(url,this);

            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();


        }
    public void limpiar(View view){
        carnetText.setText("");
        nombreText.setText("");
        apellidoText.setText("");

    }

}

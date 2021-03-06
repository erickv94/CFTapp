package dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.authguard.Auth;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;

public class GrupoMateriaCicloConsultarActivity extends AppCompatActivity {
    private static final int permiso=46;

    GrupoMateriaCicloDB grupoMateriaCicloDB;
    EditText idtargetText;
    EditText idText;
    EditText docentetext;
    EditText matcText;
    EditText tipoText;
    EditText codText;
    EditText cantidadText;
    EditText capacidadText;

    /*List<String> nombredocente = new ArrayList<String>();
    HashMap<String,Integer> nombredocenteMapeo = new HashMap<String, Integer>();
    List<String> informacionmatc = new ArrayList<String>();
    HashMap<String,Integer> informacionmatcMapeo = new HashMap<String, Integer>();
    List<String> nombretipo = new ArrayList<String>();
    HashMap<String,Integer> nombretipoMapeo = new HashMap<String, Integer>();
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo_consultar);
        setTitle(R.string.grupomateriacicloread);
        verificarPermisos();

        idtargetText = findViewById(R.id.busqueda_grupo);
        idText = findViewById(R.id.idgrupo);
        docentetext = findViewById(R.id.iddocente);
        matcText = findViewById(R.id.idmatciclo);
        tipoText = findViewById(R.id.idtipogrupo);
        codText = findViewById(R.id.codgrupo);
        cantidadText = findViewById(R.id.cantidad);
        capacidadText = findViewById(R.id.capacidad);

    }

    public void verificarPermisos(){

        if(!Auth.userHasPermission(Auth.guard(this),this,permiso)){
            finish();
            Toast.makeText(this,getResources().getString(R.string.no_permisos)+" "
                    +getResources().getString(R.string.grupomateriacicloread), Toast.LENGTH_LONG).show();
        }

    }

    public void consultarGrupoMatCiclo(View v){
        grupoMateriaCicloDB = new GrupoMateriaCicloDB(this);

        String idgrupo = idtargetText.getText().toString();

        GrupoMateriaCiclo grupoMateriaCiclo = grupoMateriaCicloDB.consultar(idgrupo);
        if (grupoMateriaCiclo!=null){
            matcText.setText(String.valueOf(grupoMateriaCiclo.getIdMatCiclo()));
            tipoText.setText(String.valueOf(grupoMateriaCiclo.getIdTipoGrupo()));
            docentetext.setText(String.valueOf(grupoMateriaCiclo.getIdDocente()));
            idText.setText(String.valueOf(grupoMateriaCiclo.getIdGrupo()));
            codText.setText(grupoMateriaCiclo.getCodgrupo());
            capacidadText.setText(String.valueOf(grupoMateriaCiclo.getCapacidadAlumnos()));
            cantidadText.setText(String.valueOf(grupoMateriaCiclo.getCantidadAlumnos()));
        } else
            Toast.makeText(this,"Esta consulta con id grupo: " + idtargetText.getText().toString() + " no existe",Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        idtargetText.setText("");
    }
}

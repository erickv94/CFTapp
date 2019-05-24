package dev.grupo5.cftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.database.AcessoUsuarioDB;
import dev.grupo5.cftapp.database.OpcionCrudDB;
import dev.grupo5.cftapp.database.UsuarioDB;
import dev.grupo5.cftapp.modelos.AccesoUsuario;
import dev.grupo5.cftapp.modelos.OpcionCrud;
import dev.grupo5.cftapp.modelos.Usuario;

public class AuthActivity extends AppCompatActivity {
    EditText userTxt;
    EditText passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userTxt= findViewById(R.id.username);
        passwordTxt= findViewById(R.id.password);

        ExistUserWithAuth();


    }

    public void ExistUserWithAuth(){
        SharedPreferences preferences= getSharedPreferences("auth",Context.MODE_PRIVATE);
        int loginExist=preferences.getInt("userid",0);
        if(loginExist!=0){
            String username=preferences.getString("username","incognito");

            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,getResources().getString(R.string.bienvenida)+" "
                    +username,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cargar_usuarios:
            cargarDatos();


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void iniciarSesion(View view){
        String pass;
        String username;
        Usuario user;
        pass=passwordTxt.getText().toString();
        username=userTxt.getText().toString();

        if(passwordTxt.getText().toString().isEmpty() || userTxt.getText().toString().isEmpty()){

            Toast.makeText(this,getResources().getString(R.string.campos_no_vacios).toString(),Toast.LENGTH_LONG).show();
            return;
        }

        UsuarioDB usuarioDB= new UsuarioDB(this);
        user=usuarioDB.getAuth(username,pass);

        if(user!=null){

            redireccionarMainActivity(user);

        }else{
            Toast.makeText(this,getResources().getString(R.string.user_no_existe),Toast.LENGTH_LONG).show();
        }

    }

    public  void redireccionarMainActivity(Usuario user){
        SharedPreferences sharedPreferences= getSharedPreferences("auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("userid",user.getId());
        editor.putString("username",user.getNombre());
        editor.commit();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,getResources().getString(R.string.bienvenida)+" "
                +sharedPreferences.getString("username","incognito"),Toast.LENGTH_LONG).show();

    }

    private void cargarDatos(){
        UsuarioDB usuarioDB= new UsuarioDB(this);
        OpcionCrudDB opcionCrudDB= new OpcionCrudDB(this);
        AcessoUsuarioDB acessoUsuarioDB= new AcessoUsuarioDB(this);

        usuarioDB.insertar(new Usuario("Admin","admin123"));
        usuarioDB.insertar(new Usuario("Docente","docente123"));


        opcionCrudDB.insertar(new OpcionCrud("Agregar Ciclo",1));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Ciclo",2));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Ciclo",3));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Ciclo",4));


        opcionCrudDB.insertar(new OpcionCrud("Agregar Rol",5));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Rol",6));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Rol",7));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Rol",8));

        opcionCrudDB.insertar(new OpcionCrud("Agregar TipoDocente",9));
        opcionCrudDB.insertar(new OpcionCrud("Consultar TipoDocente",10));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar TipoDocente",11));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar TipoDocente",12));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Docente",13));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Docente",14));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Docente",15));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Docente",16));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Local",17));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Local",18));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Local",19));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Local",20));

        opcionCrudDB.insertar(new OpcionCrud("Agregar TipoTramite",21));
        opcionCrudDB.insertar(new OpcionCrud("Consultar TipoTramite",22));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar TipoTramite",23));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar TipoTramite",24));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Tramite",25));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Tramite",26));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Tramite",27));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Tramite",28));

        opcionCrudDB.insertar(new OpcionCrud("Agregar DetalleDocente",29));
        opcionCrudDB.insertar(new OpcionCrud("Consultar DetalleDocente",30));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar DetalleDocente",31));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar DetalleDocente",32));

        opcionCrudDB.insertar(new OpcionCrud("Agregar TipoGrupo",33));
        opcionCrudDB.insertar(new OpcionCrud("Consultar TipoGrupo",34));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar TipoGrupo",35));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar TipoGrupo",36));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Materia",37));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Materia",38));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Materia",39));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Materia",40));

        opcionCrudDB.insertar(new OpcionCrud("Agregar MateriaCiclo",41));
        opcionCrudDB.insertar(new OpcionCrud("Consultar MateriaCiclo",42));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar MateriaCiclo",43));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar MateriaCiclo",44));

        opcionCrudDB.insertar(new OpcionCrud("Agregar MateriaGrupoCiclo",45));
        opcionCrudDB.insertar(new OpcionCrud("Consultar MateriaGrupoCiclo",46));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar MateriaGrupoCiclo",47));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar MateriaGrupoCiclo",48));

        opcionCrudDB.insertar(new OpcionCrud("Agregar TipoEvaluacion",49));
        opcionCrudDB.insertar(new OpcionCrud("Consultar TipoEvaluacion",50));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar TipoEvaluacion",51));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar TipoEvaluacion",52));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Evaluacion",53));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Evaluacion",54));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Evaluacion",55));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Evaluacion",56));

        opcionCrudDB.insertar(new OpcionCrud("Agregar DetalleLocal",57));
        opcionCrudDB.insertar(new OpcionCrud("Consultar DetalleLocal",58));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar DetalleLocal",59));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar DetalleLocal",60));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Estudiante",61));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Estudiante",62));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Estudiante",63));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Estudiante",64));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Detalle Revision",65));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Detalle Revision",66));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Detalle Revision",67));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Detalle Revision",68));

        opcionCrudDB.insertar(new OpcionCrud("Agregar DetalleSolicitud",69));
        opcionCrudDB.insertar(new OpcionCrud("Consultar DetalleSolicitud",70));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar DetalleSolicitud",71));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar DetalleSolicitud",72));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Dias",73));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Dias",74));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Dias",75));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Dias",76));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Estado",77));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Estado",78));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Estado",79));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Estado",80));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Impresion",81));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Impresion",82));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Impresion",83));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Impresion",84));

        opcionCrudDB.insertar(new OpcionCrud("Agregar Testigo",85));
        opcionCrudDB.insertar(new OpcionCrud("Consultar Testigo",86));
        opcionCrudDB.insertar(new OpcionCrud("Actualizar Testigo",87));
        opcionCrudDB.insertar(new OpcionCrud("Eliminar Testigo",88));

        //all access to admin
        for(int i=1;i<=88;i++){
            acessoUsuarioDB.insertar(new AccesoUsuario(1,i));
        }

        //tramite
        acessoUsuarioDB.insertar(new AccesoUsuario(2,25));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,26));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,27));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,28));
        //evaluacion
        acessoUsuarioDB.insertar(new AccesoUsuario(2,53));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,54));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,55));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,56));
        //detalle revision
        acessoUsuarioDB.insertar(new AccesoUsuario(2,65));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,66));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,67));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,68));
        //detalle evaluacion
        acessoUsuarioDB.insertar(new AccesoUsuario(2,69));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,70));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,71));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,72));

        //estado
        acessoUsuarioDB.insertar(new AccesoUsuario(2,77));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,78));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,79));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,80));

        //impresion
        acessoUsuarioDB.insertar(new AccesoUsuario(2,81));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,82));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,83));
        acessoUsuarioDB.insertar(new AccesoUsuario(2,84));


        Toast.makeText(this, getResources().getString(R.string.bd_llena),Toast.LENGTH_LONG).show();

    }
}

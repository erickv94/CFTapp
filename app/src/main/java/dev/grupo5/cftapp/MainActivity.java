package dev.grupo5.cftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dev.grupo5.cftapp.database.CicloDB;
import dev.grupo5.cftapp.database.DetalleDocenteDB;
import dev.grupo5.cftapp.database.DetalleLocalDB;
import dev.grupo5.cftapp.database.DetalleRevisionDB;
import dev.grupo5.cftapp.database.DetalleSolicitudDB;
import dev.grupo5.cftapp.database.DiasNoHabilesDB;
import dev.grupo5.cftapp.database.DocenteDB;
import dev.grupo5.cftapp.database.EstadoEvaluacionDB;
import dev.grupo5.cftapp.database.EstudianteDB;
import dev.grupo5.cftapp.database.EvaluacionDB;
import dev.grupo5.cftapp.database.GrupoMateriaCicloDB;
import dev.grupo5.cftapp.database.LocalDB;
import dev.grupo5.cftapp.database.MateriaCicloDB;
import dev.grupo5.cftapp.database.MateriaDB;
import dev.grupo5.cftapp.database.ParametroDB;
import dev.grupo5.cftapp.database.RolRevisionDB;
import dev.grupo5.cftapp.database.SolicitudImpresaDB;
import dev.grupo5.cftapp.database.TestigoDB;
import dev.grupo5.cftapp.database.TipoDocenteDB;
import dev.grupo5.cftapp.database.TipoEvaluacionDB;
import dev.grupo5.cftapp.database.TipoGrupoDB;
import dev.grupo5.cftapp.database.TipoTramiteDB;
import dev.grupo5.cftapp.database.TramiteDB;
import dev.grupo5.cftapp.modelos.Ciclo;
import dev.grupo5.cftapp.modelos.DetalleDocente;
import dev.grupo5.cftapp.modelos.DetalleLocal;
import dev.grupo5.cftapp.modelos.DetalleRevision;
import dev.grupo5.cftapp.modelos.DetalleSolicitud;
import dev.grupo5.cftapp.modelos.DiasNoHabiles;
import dev.grupo5.cftapp.modelos.Docente;
import dev.grupo5.cftapp.modelos.EstadoEvaluacion;
import dev.grupo5.cftapp.modelos.Estudiante;
import dev.grupo5.cftapp.modelos.Evaluacion;
import dev.grupo5.cftapp.modelos.GrupoMateriaCiclo;
import dev.grupo5.cftapp.modelos.Local;
import dev.grupo5.cftapp.modelos.Materia;
import dev.grupo5.cftapp.modelos.MateriaCiclo;
import dev.grupo5.cftapp.modelos.Parametro;
import dev.grupo5.cftapp.modelos.RolRevision;
import dev.grupo5.cftapp.modelos.SolicitudImpresa;
import dev.grupo5.cftapp.modelos.Testigo;
import dev.grupo5.cftapp.modelos.TipoDocente;
import dev.grupo5.cftapp.modelos.TipoEvaluacion;
import dev.grupo5.cftapp.modelos.TipoGrupo;
import dev.grupo5.cftapp.modelos.TipoTramite;
import dev.grupo5.cftapp.modelos.Tramite;

public class MainActivity extends AppCompatActivity {

    String[] menu;
    String[] activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = getResources().getStringArray(R.array.main);
        activities=getResources().getStringArray(R.array.activities);

        ListView listViewMain = findViewById(R.id.main_menu);
        listViewMain.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
        clickEventoListView(listViewMain);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                SharedPreferences sharedPreferences= getSharedPreferences("auth", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("userid").remove("username").commit();
                Intent intent= new Intent(this,AuthActivity.class);
                startActivity(intent);
                Toast.makeText(this,getResources().getString(R.string.despedida),Toast.LENGTH_LONG).show();
            case R.id.parametro:

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void clickEventoListView(ListView listViewMain) {

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if (position<menu.length-1){
                    String itemSeleccionado = activities[position];
                    try {
                        Class<?> clase = Class.forName("dev.grupo5.cftapp.menu." + itemSeleccionado);
                        Intent intent = new Intent(getBaseContext(), clase);
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    //insert database code here

                    Context context=MainActivity.this;

                    CicloDB cicloDB= new CicloDB(context);
                    RolRevisionDB rolRevisionDB= new RolRevisionDB(context);
                    TipoDocenteDB tipoDocenteDB = new TipoDocenteDB(context);
                    DocenteDB docenteDB= new DocenteDB(context);
                    LocalDB localDB= new LocalDB(context);
                    TipoTramiteDB tipotramiteDB= new TipoTramiteDB(context);
                    TramiteDB tramiteDB= new TramiteDB(context);
                    DetalleDocenteDB detalleDocenteDB= new DetalleDocenteDB(context);
                    TipoGrupoDB tipoGrupoDB= new TipoGrupoDB(context);
                    MateriaDB materiaDB= new MateriaDB(context);
                    MateriaCicloDB materiaCicloDB= new MateriaCicloDB(context);
                    GrupoMateriaCicloDB grupoMateriaCicloDB= new GrupoMateriaCicloDB(context);
                    TipoEvaluacionDB tipoEvaluacionDB= new TipoEvaluacionDB(context);
                    EvaluacionDB evaluacionDB= new EvaluacionDB(context);
                    DetalleLocalDB detalleLocalDB= new DetalleLocalDB(context);
                    EstudianteDB estudianteDB= new EstudianteDB(context);
                    DetalleRevisionDB detalleRevisionDB= new DetalleRevisionDB(context);
                    DetalleSolicitudDB detalleSolicitudDB= new DetalleSolicitudDB(context);
                    DiasNoHabilesDB diasNoHabilesDB= new DiasNoHabilesDB(context);
                    EstadoEvaluacionDB estadoEvaluacionDB= new EstadoEvaluacionDB(context);
                    ParametroDB parametroDB= new ParametroDB(context);
                    SolicitudImpresaDB solicitudImpresaDB= new SolicitudImpresaDB(context);
                    TestigoDB testigoDB= new TestigoDB(context);



                    //ciclo seeders
                    cicloDB.insertar(new Ciclo(1,2019));
                    cicloDB.insertar(new Ciclo(2,2019));
                    cicloDB.insertar(new Ciclo(1,2020));
                    cicloDB.insertar(new Ciclo(2,2020));

                    //rol revision seeder
                    rolRevisionDB.insertar(new RolRevision("Encargado Principal","Descripcion Rol Revision Encargado Revision Principal"));
                    rolRevisionDB.insertar(new RolRevision("Encargado Secundario","Descripcion Rol Revision Encargado Revision Secundario"));
                    rolRevisionDB.insertar(new RolRevision("Docente Testigo","Descripcion Rol Revision Docente Testigo"));

                    //tipo docente
                    tipoDocenteDB.insertar(new TipoDocente("Ingeniero"));
                    tipoDocenteDB.insertar(new TipoDocente("Licenciado"));
                    tipoDocenteDB.insertar(new TipoDocente("Doctor"));

                    //docente db

                    docenteDB.insertar(new Docente(1,"Juan Jose",
                            "Delgado Barras","CO1","M"));
                    docenteDB.insertar(new Docente(1,"Jaime Abel",
                            "Hurtado Zelaya","CO1","M"));
                    docenteDB.insertar(new Docente(2,"Rosa Maria" ,
                            "Alvarez Rivas","CO1","F"));
                    docenteDB.insertar(new Docente(3,"Carlos Mario",
                            "Delgado Ochoa","CO1","M"));

                    //local db
                    localDB.insertar(new Local("B100","Salon B101",
                            "B101",100));
                    localDB.insertar(new Local("A100","Salon A112",
                            "A112",90));
                    localDB.insertar(new Local("B200","Salon B204",
                            "B204",85));
                    localDB.insertar(new Local("A400","Salon A405",
                            "A405",95));
                    localDB.insertar(new Local("REV","Salon REV10",
                            "REV10",5));
                    localDB.insertar(new Local("REV","Salon REV11",
                            "REV11",5));

                    //tipo tramite
                    tipotramiteDB.insertar(new TipoTramite("Primera Revision",
                            "Revision en primera solicitud"));
                    tipotramiteDB.insertar(new TipoTramite("Segunda Revision",
                            "Revision en segunda solicitud"));

                    //tramites
                    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        tramiteDB.insertar(new Tramite(1,5,simpleDateFormat.parse("11-05-2019")));
                        tramiteDB.insertar(new Tramite(1,6,simpleDateFormat.parse("22-05-2019")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    //Detalle Docente
                    detalleDocenteDB.insertar(new DetalleDocente(1,1,1,
                            true,null));
                    detalleDocenteDB.insertar(new DetalleDocente(2,2,1,
                            false,"ocupado en algo"));

                    detalleDocenteDB.insertar(new DetalleDocente(1,4,2,
                            false,"ocupado en algo importante"));

                    //tipo grupo
                    tipoGrupoDB.insertar(new TipoGrupo("GL"));
                    tipoGrupoDB.insertar(new TipoGrupo("GT"));
                    tipoGrupoDB.insertar(new TipoGrupo("GD"));

                    //materia db
                    materiaDB.insertar(new Materia("Matematica","MAT-115",4));
                    materiaDB.insertar(new Materia("Fisica","FIR-115",4));
                    materiaDB.insertar(new Materia("SysGeo","SIG-115",3));
                    materiaDB.insertar(new Materia("Diseño","DIS-115",4));

                    //materia ciclo
                    materiaCicloDB.insertar(new MateriaCiclo(1,1));
                    materiaCicloDB.insertar(new MateriaCiclo(1,2));
                    materiaCicloDB.insertar(new MateriaCiclo(2,2));
                    materiaCicloDB.insertar(new MateriaCiclo(3,1));
                    materiaCicloDB.insertar(new MateriaCiclo(4,2));

                    //grupo materia ciclo
                    grupoMateriaCicloDB.insertar(new GrupoMateriaCiclo(1,1,1,
                            "GT01",50,70));
                    grupoMateriaCicloDB.insertar(new GrupoMateriaCiclo(1,1,2,
                            "GL01",30,35));
                    grupoMateriaCicloDB.insertar(new GrupoMateriaCiclo(2,2,2,
                            "GT02",50,70));

                    //tipo evaluacion
                    tipoEvaluacionDB.insertar(new TipoEvaluacion("Parcial","Examen parcial"));
                    tipoEvaluacionDB.insertar(new TipoEvaluacion("Lab","Examen Laboratorio"));

                    //evaluacion
                    evaluacionDB.insertar(new Evaluacion(1,1,"Parcial 1",new Date()));
                    evaluacionDB.insertar(new Evaluacion(2,1,"Parcial 2",new Date()));
                    evaluacionDB.insertar(new Evaluacion(3,2,"Lab 2",new Date()));

                    //detalle localbd

                    detalleLocalDB.insertar(new DetalleLocal(1,1,60));
                    detalleLocalDB.insertar(new DetalleLocal(2,2,80));
                    detalleLocalDB.insertar(new DetalleLocal(1,3,70));


                    //estudiante

                    estudianteDB.insertar(new Estudiante("Erick Antonio","Ventura Hurtado","VH14006","M"));
                    estudianteDB.insertar(new Estudiante("Elmer Antonio","Hueso Gonzales","HG14001","M"));
                    estudianteDB.insertar(new Estudiante("Yaneth Zoi","Ventura Gonzalez","VG14002","F"));
                    estudianteDB.insertar(new Estudiante("Juan Antonio","Perez Perez","PP14008","M"));
                    //detalle revision
                    detalleRevisionDB.insertar(new DetalleRevision(1,1,9,"Nota injusta",true));
                    detalleRevisionDB.insertar(new DetalleRevision(2,2,4,"Ejercio estaba completo",true));

                    //detalle solicitud
                    detalleSolicitudDB.insertar(new DetalleSolicitud(1,3,"No presento pruebas",true));
                    detalleSolicitudDB.insertar(new DetalleSolicitud(2,4,"Aceptado se necesita mas pruebas",false));

                    //dias no habiles
                    diasNoHabilesDB.insertar(new DiasNoHabiles(1,"dia madre","dia de las madres",new Date()));
                    diasNoHabilesDB.insertar(new DiasNoHabiles(2,"dia padres","dia de los padres",new Date()));

                    //estado evaluacion
                    estadoEvaluacionDB.insertar(new EstadoEvaluacion(1,1,9.4));
                    estadoEvaluacionDB.insertar(new EstadoEvaluacion(2,2,3.0));
                    estadoEvaluacionDB.insertar(new EstadoEvaluacion(3,3,7.2));

                    //parametro
                    parametroDB.insertar(new Parametro("repetido",2));
                    parametroDB.insertar(new Parametro("revision",3));
                    parametroDB.insertar(new Parametro("ordinario",3));
                    //solicitud impresa
                    solicitudImpresaDB.insertar(new SolicitudImpresa(1,20,
                            "Parciales necesario","Se necesitan para mate",false,new Date(),2,"imp01"));
                    solicitudImpresaDB.insertar(new SolicitudImpresa(2,25,
                            "Parciales necesario","Se necesitan para fisica",true,new Date(),2,"imp02"));

                    testigoDB.insertar(new Testigo(1,4,"Estudiante en funcion de testigo"));
                    testigoDB.insertar(new Testigo(2,3,"Estudiante en funcion de testigo"));
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.bd_llena),Toast.LENGTH_SHORT).show();

                }

                }




        });
    }


}

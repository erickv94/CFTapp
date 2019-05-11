package dev.grupo5.cftapp.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import dev.grupo5.cftapp.R;

public class GrupoMateriaCicloActivity extends AppCompatActivity {
    String[] submenu;
    String[] activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_ciclo);
        setTitle(R.string.materiaGrupoCiclo);
        submenu = getResources().getStringArray(R.array.submenu);
        activities=getResources().getStringArray(R.array.activitiesGrupoMateriaCiclo);
        ListView listView= findViewById(R.id.submenu_grupomateriaciclo);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,submenu));

        clickEventoListView(listView);

    }

    private void clickEventoListView(ListView listViewMain) {

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSeleccionado=activities[position];
                Toast.makeText(GrupoMateriaCicloActivity.this, itemSeleccionado, Toast.LENGTH_SHORT).show();

                try {
                    Class<?> clase= Class.forName("dev.grupo5.cftapp.mantenimientos.GrupoMateriaCiclo."+itemSeleccionado);
                    Intent intent= new Intent(getBaseContext(),clase);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }




            }
        });
    }

}

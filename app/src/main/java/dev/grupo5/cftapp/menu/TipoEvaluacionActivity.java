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

public class TipoEvaluacionActivity extends AppCompatActivity {
    String[] submenu;
    String[] activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evaluacion);
        setTitle(R.string.tipoEvaluacion);

        submenu = getResources().getStringArray(R.array.submenu);
        activities=getResources().getStringArray(R.array.activitiesTipoEvaluacion);
        ListView listView= findViewById(R.id.submenu_tipoevaluacion);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,submenu));

        clickEventoListView(listView);

    }

    private void clickEventoListView(ListView listViewMain) {

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSeleccionado=activities[position];
                Toast.makeText(TipoEvaluacionActivity.this, itemSeleccionado, Toast.LENGTH_SHORT).show();

                try {
                    Class<?> clase= Class.forName("dev.grupo5.cftapp.mantenimientos.TipoEvaluacion."+itemSeleccionado);
                    Intent intent= new Intent(getBaseContext(),clase);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

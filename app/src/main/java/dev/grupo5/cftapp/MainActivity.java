package dev.grupo5.cftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dev.grupo5.cftapp.database.DBHelper;

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
        DBHelper dbHelper= new DBHelper(this);
    }

    private void clickEventoListView(ListView listViewMain) {

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSeleccionado=activities[position];

                try {
                    Class<?> clase= Class.forName("dev.grupo5.cftapp.menu."+itemSeleccionado);
                    Intent intent= new Intent(getBaseContext(),clase);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }




            }
        });
    }


}

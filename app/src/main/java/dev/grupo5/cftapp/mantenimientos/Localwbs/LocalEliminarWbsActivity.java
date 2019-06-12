package dev.grupo5.cftapp.mantenimientos.Localwbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dev.grupo5.cftapp.R;

public class LocalEliminarWbsActivity extends AppCompatActivity {
    String[] submenu, activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar_wbs);
        setTitle(getResources().getString(R.string.localdelete)+" webservice");

    }
}
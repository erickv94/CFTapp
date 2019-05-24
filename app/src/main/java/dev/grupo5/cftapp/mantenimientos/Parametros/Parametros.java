package dev.grupo5.cftapp.mantenimientos.Parametros;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;

public class Parametros extends AppCompatActivity {
    EditText repetidoTxt, diferidoTxt, revisionTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);
        repetidoTxt= findViewById(R.id.parametro1);
        diferidoTxt= findViewById(R.id.parametro2);
        revisionTxt=findViewById(R.id.parametro3);
        DBHelper dbHelper= DBHelper.getSingleton(this);

        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        Cursor cursor= readableDatabase.query("parametro", new String[]{"parametro", "diashabiles"}, null, null, null, null
                , null);

        cursor.moveToFirst();
        repetidoTxt.setText(cursor.getString(1));
        cursor.moveToNext();
        diferidoTxt.setText(cursor.getString(1));
        cursor.moveToNext();
        revisionTxt.setText(cursor.getString(1));

    }

    public void actualizarParametros(View view){

        DBHelper dbHelper= DBHelper.getSingleton(this);

        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();

        writableDatabase.execSQL("update parametro set diashabiles='"
                +repetidoTxt.getText().toString()+"' where parametro='repetido';");

        writableDatabase.execSQL("update parametro set diashabiles='"
                +diferidoTxt.getText().toString()+"' where parametro='diferido';");

        writableDatabase.execSQL("update parametro set diashabiles='"
                +revisionTxt.getText().toString()+"' where parametro='revision';");

        Toast.makeText(this,"Actualizado con exito/ Update successfully",Toast.LENGTH_LONG).show();
    }
}

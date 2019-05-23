package dev.grupo5.cftapp.mantenimientos.SolicitudImpresa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dev.grupo5.cftapp.R;
import dev.grupo5.cftapp.database.DBHelper;
import dev.grupo5.cftapp.database.SolicitudImpresaDB;
import dev.grupo5.cftapp.modelos.SolicitudImpresa;

public class SolicitudImpresaConsultarActivity extends AppCompatActivity {

    EditText editIdSoli;
    EditText editIdDocente;
    EditText editCantidadImp;
    EditText editAsunto;
    EditText editJustificacion;
    CheckBox editAprobadoCheck;
    EditText editFecha;
    EditText editPaginasAnexas;
    EditText editCodImpresion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresa_consultar);
        setTitle(R.string.solicitudesread);

        editIdSoli=findViewById(R.id.editIdSoli);
        editIdDocente=findViewById(R.id.editIdDocente);
        editCantidadImp=findViewById(R.id.editCantidadImp);
        editAsunto=findViewById(R.id.editAsunto);
        editJustificacion=findViewById(R.id.editJustificacion);
        editAprobadoCheck=findViewById(R.id.checkbox);
        editFecha=findViewById(R.id.editFechaSolicitud);
        editPaginasAnexas=findViewById(R.id.editPaginasAnexas);
        editCodImpresion=findViewById(R.id.editCodImpresion);
    }

    public void consultarSolicitudes(View view) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SolicitudImpresaDB solicitudImpresaDB = new SolicitudImpresaDB(this);
        SolicitudImpresa solicitudImpresa = solicitudImpresaDB.consultar(editIdSoli.getText().toString());
        //SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");



        if(solicitudImpresa!=null){





            //editIdSoli.setText(solicitudImpresa.getIdSolicitudImp());
            //editIdDocente.setText(String.valueOf(solicitudImpresa.getIdDocente()));
            editCantidadImp.setText(String.valueOf(solicitudImpresa.getCantidadImpresiones()));
            editAsunto.setText(solicitudImpresa.getAsunto());
            editJustificacion.setText(solicitudImpresa.getJustificacion());
            //editAprobadoCheck.setText(solicitudImpresa.getAprobado()?"Ha sido aprobado":"No ha sido aprobado");
            editPaginasAnexas.setText(String.valueOf(solicitudImpresa.getPaginasAnexas()));
            editCodImpresion.setText(solicitudImpresa.getCodigoImpresion());
            //editFecha.setText(simpleDateFormat.format(solicitudImpresa.getFechasolicitud()));
            return;
        }

        Toast.makeText(this,"Consulta no existe"+" ID: " +editIdSoli+"",Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdSoli.setText("");
        //editCantidadImp.setText("");
        editAsunto.setText("");
        editJustificacion.setText("");
        //editAprobadoCheck.setText("");
        //editPaginasAnexas.setText("");
        editCodImpresion.setText("");
    }
}

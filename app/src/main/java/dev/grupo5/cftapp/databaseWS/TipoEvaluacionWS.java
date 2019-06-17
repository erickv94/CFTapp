package dev.grupo5.cftapp.databaseWS;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class TipoEvaluacionWS {
    public static String obtenerRespuestaPeticion(String url, Context ctx) {

        String respuesta = " ";

        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);

        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static String insertarTipoEvaluacion(String peticion, Context ctx){
        String json=obtenerRespuestaPeticion(peticion,ctx);
        String [] resultado=json.split(";");
        if (resultado[1].equals("{\"resultado\":1}")){
            return "Se inserto con exito en el servidor";
        }else{
            return "No se pudo insertar";
        }

    }

    public static String consultaTipoEvaluacion(String peticion, Context ctx){

        String json = obtenerRespuestaPeticion(peticion,ctx);
        String parseado = json.replace("{","");
        String parseado2 = parseado.replace("}","");
        String parseado3 = parseado2.replace("[","");
        String parseado4 = parseado3.replace("]","");
        String parseado5 = parseado4.replace("\"","");
        String [] resultado = parseado5.split(",");
        if (resultado[0].equals("No existe")){
            return null;
        }else{
            return parseado5;
        }
    }
    public static String actualizarTipoEvaluacion(String peticion, Context ctx){
        String json = obtenerRespuestaPeticion(peticion,ctx);
        String [] resultado=json.split(";");
        if (resultado[1].equals("{\"resultado\":1}")){
            return "Se actualizo con exito en el servidor";
        }else{
            return "No se pudo actualizar";
        }
    }
    public static String eliminarTipoEvaluacion(String peticion, Context ctx){
        String json = obtenerRespuestaPeticion(peticion,ctx);
        String [] resultado=json.split(";");
        if(resultado[1].equals("{\"resultado\":1}")){
            return "Se elimino correctamente";
        }
        else{
            return "No se pudo eliminar";
        }
    }

}

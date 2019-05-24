package dev.grupo5.cftapp.modelos;

public class TipoEvaluacion {
    private int idTipoEvaluacion;
    private String nombre;
    private String descripcion;


    public TipoEvaluacion() {
    }

    public TipoEvaluacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(int idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

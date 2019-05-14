package dev.grupo5.cftapp.modelos;

public class RolRevision {
private int idRol;
private String nombre;
private String descripcion;

    public RolRevision(){

    }

    public RolRevision( String nombre, String descripcion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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

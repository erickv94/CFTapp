package dev.grupo5.cftapp.modelos;

public class Materia {
    private int idMateria;
    private String nombre;
    private String codigoMateria;
    private int uvs;

    public Materia(){}

    public Materia( String nombre, String codigoMateria, int uvs) {
        this.nombre = nombre;
        this.codigoMateria = codigoMateria;
        this.uvs = uvs;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public int getUvs() {
        return uvs;
    }

    public void setUvs(int uvs) {
        this.uvs = uvs;
    }
}

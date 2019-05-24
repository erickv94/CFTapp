package dev.grupo5.cftapp.modelos;

import java.util.Date;

public class DiasNoHabiles {
    private int idDias;
    private int idCiclo;
    private String nombre;
    private String descripcion;
    private Date fecha;

    public DiasNoHabiles() {
    }

    public DiasNoHabiles(int idCiclo, String nombre, String descripcion, Date fecha) {
        this.idCiclo = idCiclo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getIdDias() {
        return idDias;
    }

    public void setIdDias(int idDias) {
        this.idDias = idDias;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

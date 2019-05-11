package dev.grupo5.cftapp.modelos;

public class TipoDocente {
    private int idTipoDocente;
    private String nombre;

    public TipoDocente( String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoDocente() {
        return idTipoDocente;
    }

    public void setIdTipoDocente(int idTipoDocente) {
        this.idTipoDocente = idTipoDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

package dev.grupo5.cftapp.modelos;

public class TipoGrupo {

    private int idTipoGrupo;
    private String nombre;

    public TipoGrupo(String nombre) {

        this.nombre = nombre;
    }

    public int getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(int idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

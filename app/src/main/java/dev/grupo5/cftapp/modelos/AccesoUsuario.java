package dev.grupo5.cftapp.modelos;

public class AccesoUsuario {
    private int idUsuario;
    private int idOpcionCrud;

    public AccesoUsuario(int idUsuario, int idOpcionCrud) {
        this.idUsuario = idUsuario;
        this.idOpcionCrud = idOpcionCrud;
    }

    public AccesoUsuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdOpcionCrud() {
        return idOpcionCrud;
    }

    public void setIdOpcionCrud(int idOpcionCrud) {
        this.idOpcionCrud = idOpcionCrud;
    }
}

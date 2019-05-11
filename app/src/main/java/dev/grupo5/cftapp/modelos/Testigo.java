package dev.grupo5.cftapp.modelos;

public class Testigo {
    private int idTestigo;
    private int idTramite;
    private int idEstudiante;
    private String justificacion;

    public Testigo( int idTramite, int idEstudiante, String justificacion) {
        this.idTramite = idTramite;
        this.idEstudiante = idEstudiante;
        this.justificacion = justificacion;
    }

    public int getIdTestigo() {
        return idTestigo;
    }

    public void setIdTestigo(int idTestigo) {
        this.idTestigo = idTestigo;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}

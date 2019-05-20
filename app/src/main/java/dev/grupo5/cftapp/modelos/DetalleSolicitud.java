package dev.grupo5.cftapp.modelos;

public class DetalleSolicitud {
    private int idTramite;
    private int idEstudiante;
    private String motivo;
    private Boolean esRechazado;

    public DetalleSolicitud(int idTramite, int idEstudiante, String motivo, Boolean esRechazado) {
        this.idTramite = idTramite;
        this.idEstudiante = idEstudiante;
        this.motivo = motivo;
        this.esRechazado = esRechazado;
    }

    public DetalleSolicitud(){}

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Boolean getEsRechazado() {
        return esRechazado;
    }

    public void setEsRechazado(Boolean esRechazado) {
        this.esRechazado = esRechazado;
    }
}

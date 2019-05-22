package dev.grupo5.cftapp.modelos;

public class DetalleDocente {
    private int idRol;
    private int idDocente;
    private int idTramite;
    private boolean asistencia;
    private String motivoInasistencia;

    public DetalleDocente(int idRol, int idDocente, int idTramite, boolean asistencia, String motivoInasistencia) {
        this.idRol = idRol;
        this.idDocente = idDocente;
        this.idTramite = idTramite;
        this.asistencia = asistencia;
        this.motivoInasistencia = motivoInasistencia;
    }

    public DetalleDocente() {

    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getMotivoInasistencia() {
        return motivoInasistencia;
    }

    public void setMotivoInasistencia(String motivoInasistencia) {
        this.motivoInasistencia = motivoInasistencia;
    }
}

package dev.grupo5.cftapp.modelos;

public class DetalleRevision {
    private int idTramite;
    private int idEstudiante;
    private double resultado;
    private String motivo;
    private boolean asistencia;

    public DetalleRevision(){}

    public DetalleRevision(int idTramite, int idEstudiante, double resultado, String motivo, boolean asistencia) {
        this.idTramite = idTramite;
        this.idEstudiante = idEstudiante;
        this.resultado = resultado;
        this.motivo = motivo;
        this.asistencia = asistencia;
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

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}

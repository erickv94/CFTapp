package dev.grupo5.cftapp.modelos;

public class EstadoEvaluacion {
    private int idEstado;
    private int idEvaluacion;
    private int idEstudiante;
    private double nota;

    public EstadoEvaluacion(int idEvaluacion, int idEstudiante, double nota) {

        this.idEvaluacion = idEvaluacion;
        this.idEstudiante = idEstudiante;
        this.nota = nota;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

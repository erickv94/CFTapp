package dev.grupo5.cftapp.modelos;

import java.util.Date;

public class Evaluacion {
    private int idEvaluacion;
    private int idGrupo;
    private int idTipoEvaluacion;
    private String nombreEvaluacion;
    private Date fecha;

    public Evaluacion( int idGrupo, int idTipoEvaluacion, String nombreEvaluacion, Date fecha) {

        this.idGrupo = idGrupo;
        this.idTipoEvaluacion = idTipoEvaluacion;
        this.nombreEvaluacion = nombreEvaluacion;
        this.fecha = fecha;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(int idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

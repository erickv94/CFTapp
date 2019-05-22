package dev.grupo5.cftapp.modelos;

public class DetalleLocal {
    private int idLocal;
    private int idEvaluacion;
    private int cantidadAlumnos;

    public DetalleLocal(){}

    public DetalleLocal(int idLocal, int idEvaluacion, int cantidadAlumnos) {
        this.idLocal = idLocal;
        this.idEvaluacion = idEvaluacion;
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }
}

package dev.grupo5.cftapp.modelos;

public class Local {
    private int idLocal;
    private String codigoEdificio;
    private String nombreLocal;
    private String codigoLocal;
    private int capacidad;

    public Local(){}

    public Local( String codigoEdificio, String nombreLocal, String codigoLocal, int capacidad) {

        this.codigoEdificio = codigoEdificio;
        this.nombreLocal = nombreLocal;
        this.codigoLocal = codigoLocal;
        this.capacidad = capacidad;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getCodigoEdificio() {
        return codigoEdificio;
    }

    public void setCodigoEdificio(String codigoEdificio) {
        this.codigoEdificio = codigoEdificio;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}

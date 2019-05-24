package dev.grupo5.cftapp.modelos;

public class OpcionCrud {
    private String descripcion;
    private int numCrud;

    public OpcionCrud(String descripcion, int numCrud) {
        this.descripcion = descripcion;
        this.numCrud = numCrud;
    }

    public OpcionCrud() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumCrud() {
        return numCrud;
    }

    public void setNumCrud(int numCrud) {
        this.numCrud = numCrud;
    }
}

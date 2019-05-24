package dev.grupo5.cftapp.modelos;

import java.util.Date;

public class Ciclo {

    private int idCiclo;
    private int ciclo;
    private int anio;

    public Ciclo() {
    }

    public Ciclo(int ciclo, int anio) {

        this.ciclo = ciclo;
        this.anio = anio;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }



}

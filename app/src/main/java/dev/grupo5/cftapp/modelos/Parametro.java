package dev.grupo5.cftapp.modelos;

public class Parametro {
    private String parametro;
    private int diasHabiles;

    public Parametro(){}

    public Parametro(String parametro, int diasHabiles) {
        this.parametro = parametro;
        this.diasHabiles = diasHabiles;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public int getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(int diasHabiles) {
        this.diasHabiles = diasHabiles;
    }
}

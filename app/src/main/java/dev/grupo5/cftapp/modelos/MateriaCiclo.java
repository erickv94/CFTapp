package dev.grupo5.cftapp.modelos;

public class MateriaCiclo
{
    private int idMatCiclo;
    private int idMateria;
    private int idCiclo;



    public MateriaCiclo(int idMateria, int idCiclo) {
        this.idMateria = idMateria;
        this.idCiclo = idCiclo;
    }

    public MateriaCiclo() {
    }

    public int getIdMatCiclo() {
        return idMatCiclo;
    }

    public void setIdMatCiclo(int idMatCiclo) {
        this.idMatCiclo = idMatCiclo;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }
}

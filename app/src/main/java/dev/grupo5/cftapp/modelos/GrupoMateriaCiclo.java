package dev.grupo5.cftapp.modelos;

public class GrupoMateriaCiclo {
    private int idGrupo;
    private int idDocente;
    private int idMatCiclo;
    private int idTipoGrupo;
    private String codgrupo;
    private int cantidadAlumnos;
    private int capacidadAlumnos;

    public GrupoMateriaCiclo(){}
    public GrupoMateriaCiclo( int idDocente, int idMatCiclo, int idTipoGrupo, String codgrupo, int cantidadAlumnos, int capacidadAlumnos) {
        this.idDocente = idDocente;
        this.idMatCiclo = idMatCiclo;
        this.idTipoGrupo = idTipoGrupo;
        this.codgrupo = codgrupo;
        this.cantidadAlumnos = cantidadAlumnos;
        this.capacidadAlumnos = capacidadAlumnos;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdMatCiclo() {
        return idMatCiclo;
    }

    public void setIdMatCiclo(int idMatCiclo) {
        this.idMatCiclo = idMatCiclo;
    }

    public int getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(int idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getCodgrupo() {
        return codgrupo;
    }

    public void setCodgrupo(String codgrupo) {
        this.codgrupo = codgrupo;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public void setCapacidadAlumnos(int capacidadAlumnos) {
        this.capacidadAlumnos = capacidadAlumnos;
    }
}

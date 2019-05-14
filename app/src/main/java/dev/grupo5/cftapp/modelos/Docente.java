package dev.grupo5.cftapp.modelos;

public class Docente {
    private int idDocente;
    private int idTipoDocente;
    private String nombre;
    private String apellidos;
    private String codDocente;
    private  String sexo;

    public Docente() {
    }

    public Docente(int idTipoDocente, String nombre, String apellidos, String codDocente, String sexo) {

        this.idTipoDocente = idTipoDocente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codDocente = codDocente;
        this.sexo = sexo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdTipoDocente() {
        return idTipoDocente;
    }

    public void setIdTipoDocente(int idTipoDocente) {
        this.idTipoDocente = idTipoDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(String codDocente) {
        this.codDocente = codDocente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

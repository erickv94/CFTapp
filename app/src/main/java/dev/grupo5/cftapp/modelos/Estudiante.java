package dev.grupo5.cftapp.modelos;

public class Estudiante {
    private int idEstudiante;
    private String nombres;
    private String apellidos;
    private String carnet;
    private String sexo;

    public Estudiante(String nombres, String apellidos, String carnet, String sexo) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carnet = carnet;
        this.sexo = sexo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

package dev.grupo5.cftapp.modelos;

public class Usuario {
    private String nombre;
    private String password;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario() {
    }

    public Usuario(String n,String p){
        this.nombre=n;
        this.password=p;
    }

}

package dev.grupo5.cftapp.modelos;

import java.util.Date;

public class SolicitudImpresa {
    private  int idSolicitudImp;
    private int idDocente;
    private int cantidadImpresiones;
    private String asunto;
    private String justificacion;
    private boolean aprobado;
    private Date fechasolicitud;
    private int paginasAnexas;
    private String codigoImpresion;

    public SolicitudImpresa() {
    }

    public SolicitudImpresa(int idDocente, int cantidadImpresiones, String asunto, String justificacion, boolean aprobado, Date fechasolicitud, int paginasAnexas, String codigoImpresion) {
        this.idDocente = idDocente;
        this.cantidadImpresiones = cantidadImpresiones;
        this.asunto = asunto;
        this.justificacion = justificacion;
        this.aprobado = aprobado;
        this.fechasolicitud = fechasolicitud;
        this.paginasAnexas = paginasAnexas;
        this.codigoImpresion = codigoImpresion;
    }

    public int getIdSolicitudImp() {
        return idSolicitudImp;
    }

    public void setIdSolicitudImp(int idSolicitudImp) {
        this.idSolicitudImp = idSolicitudImp;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getCantidadImpresiones() {
        return cantidadImpresiones;
    }

    public void setCantidadImpresiones(int cantidadImpresiones) {
        this.cantidadImpresiones = cantidadImpresiones;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public int getPaginasAnexas() {
        return paginasAnexas;
    }

    public void setPaginasAnexas(int paginasAnexas) {
        this.paginasAnexas = paginasAnexas;
    }

    public String getCodigoImpresion() {
        return codigoImpresion;
    }

    public void setCodigoImpresion(String codigoImpresion) {
        this.codigoImpresion = codigoImpresion;
    }
}

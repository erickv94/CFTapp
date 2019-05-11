package dev.grupo5.cftapp.modelos;

import java.util.Date;

public class Tramite {
    private int idTipoTramite;
    private int idTramite;
    private int idLocal;
    private Date fechaSolicitud;

    public Tramite(int idTipoTramite, int idLocal, Date fechaSolicitud) {
        this.idTipoTramite = idTipoTramite;
        this.idLocal = idLocal;
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdTipoTramite() {
        return idTipoTramite;
    }

    public void setIdTipoTramite(int idTipoTramite) {
        this.idTipoTramite = idTipoTramite;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}

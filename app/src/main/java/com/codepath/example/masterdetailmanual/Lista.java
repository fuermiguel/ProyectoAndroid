package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

public class Lista {

    public String foto_detalle;
    public String descripcion_detalle;
    public String id_deporte;

    public Lista( String foto, String descripcion) {
        this.foto_detalle = foto;
        this.descripcion_detalle = descripcion;
    }

    public String getFoto_detalle() {
        return foto_detalle;
    }

    public void setFoto_detalle(String foto_detalle) {
        this.foto_detalle = foto_detalle;
    }

    public String getDescripcion_detalle() {
        return descripcion_detalle;
    }

    public void setDescripcion_detalle(String descripcion_detalle) {
        this.descripcion_detalle = descripcion_detalle;
    }
}

package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

public class Deporte {

    public String nombre;
    public String foto_deporte;

    public Deporte(String nombre, String foto_deporte) {
        this.nombre = nombre;
        this.foto_deporte = foto_deporte;
    }

    public String getFoto_deporte() {
        return foto_deporte;
    }

    public void setFoto_deporte(String foto_deporte) {
        this.foto_deporte = foto_deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

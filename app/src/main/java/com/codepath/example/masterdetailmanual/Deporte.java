package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

public class Deporte {
    private String id_Deporte;
    private String nombre;

    public Deporte(String id_Deporte, String nombre) {
        this.id_Deporte = id_Deporte;
        this.nombre = nombre;
    }

    public String getId_Deporte() {
        return id_Deporte;
    }

    public void setId_Deporte(String id_Deporte) {
        this.id_Deporte = id_Deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Deporte{" +
                "id_Deporte='" + id_Deporte + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

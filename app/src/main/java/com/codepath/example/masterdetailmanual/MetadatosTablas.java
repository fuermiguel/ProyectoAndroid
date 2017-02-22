package com.codepath.example.masterdetailmanual;

import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by fuerm on 21/02/2017.
 */

public class MetadatosTablas {

    interface  ColumnasDeporte {
        String ID = "id";
        String NOMBRE = "nombre";
    }

    interface ColumnasLista {
        String ID = "id";
        String FOTO = "foto";
        String DETALLE = "detalle";
        String ID_DEPORTE = "id_deporte";
    }

    public static class Deportes implements ColumnasDeporte{
        public static String generarIdDeporte(){
            return "D-" + UUID.randomUUID().toString();
        }

    }

    public static class Lista implements ColumnasLista{
        public static String generarIdLista(){
            return "L-" + UUID.randomUUID().toString();
        }
    }
}

package com.codepath.example.masterdetailmanual;
/*
    Clase Contract, que especifica explícitamente el diseño del esquema
    de forma sistemática y autodocumentada.
 */
import android.provider.BaseColumns;

import java.util.UUID;

/**
 * Created by fuerm on 21/02/2017.
 */

public class CheckListContract {

    //El constructor se hace privado para no poder instanciar la clase de manera accidental
    private CheckListContract(){}

    public static class CheckListDeporte implements BaseColumns {
        /*todo el identificador no lo pongo ¿Creo que se usa con la interface BaseColumns .Añadir el id de la tabla como unique y cuando creo la tabla añado basecolumns_id como clave primaria.
        */
        public  static final String TABLE_NAME = "deporte";
        public  static final String COLUMN_NOMBRE= "nombre" ;
        public static String generarIdDeporte(){
            return "D-" + UUID.randomUUID().toString();
        }

    }

    public static class ChekListLista implements BaseColumns {
        public  static final String TABLE_NAME = "lista";
        public  static final String COLUMN_FOTO= "foto" ;
        public  static final String COLUMN_DETALLE= "detalle" ;
        public  static final String COLUMN_ID_DEPORTE =  "id_deporte" ;
        public static String generarIdLista(){
            return "L-" + UUID.randomUUID().toString();
        }
    }

}

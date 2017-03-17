package com.codepath.example.masterdetailmanual;
/*
    Clase Contract, que especifica explícitamente el diseño del esquema de la base de datos
    de forma sistemática y autodocumentada.
 */

import android.provider.BaseColumns;

import java.util.UUID;

/**
 * Created by fuerm on 21/02/2017.
 */

public class CheckListContract {

    //El constructor se hace privado para no poder instanciar la clase de manera accidental
    private CheckListContract() {
    }

    public static class CheckListDeporte implements BaseColumns {

        public static final String TABLE_NAME = "deporte";
        public static final String COLUMN_FOTO = "foto_deporte";
        public static final String COLUMN_NAME = "nombre";

    }

    public static class ChekListLista implements BaseColumns {
        public static final String TABLE_NAME = "lista";
        public static final String COLUMN_FOTO = "foto_lista";
        public static final String COLUMN_DETALLE = "descripcion";
        public static final String COLUMN_ID_DEPORTE = "id_deporte";

    }

}

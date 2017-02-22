package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/*
    Patrón Singleton Para SQLite
    Crea una nueva clase llamada OperacionesBD.java e implementa un patrón singleton. Esto significa,
    poner su constructor principal como privado, definir un miembro estático de la clase y generar un
    método estático que permita la obtención del único miembro.
 */

public final class OperacionesBD {

    private static ChekListDbHelper baseDatos;

    private static OperacionesBD instancia = new OperacionesBD();

    private OperacionesBD() {
    }

    public static OperacionesBD obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new ChekListDbHelper(contexto);
        }
        return instancia;
    }

    /*
        Método que devuelve un cursor con la lista de deportes
     */
    public ArrayList<String> consultarDeportes( ){
      //  ChekListDbHelper admin = new ChekListDbHelper(contexto);
        SQLiteDatabase bd = baseDatos.getReadableDatabase();
        Cursor fila = bd.rawQuery(
                "select " +  CheckListContract.Deportes.NOMBRE +
                        " from " +  ChekListDbHelper.Tablas.DEPORTE, null);
        ArrayList deportes = new ArrayList();


        if (fila.moveToFirst()) {
            do {
                deportes.add(fila.getString(0));
            } while (fila.moveToNext());
        }
        bd.close();
        return deportes;
    }
//todo el método de abajo es de referencia para hacer el insertar deporte
    public String insertarFormaPago(FormaPago formaPago) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idFormaPago = FormasPago.generarIdFormaPago();

        ContentValues valores = new ContentValues();
        valores.put(FormasPago.ID, idFormaPago);
        valores.put(FormasPago.NOMBRE, formaPago.nombre);

        return db.insertOrThrow(Tablas.FORMA_PAGO, null, valores) > 0 ? idFormaPago : null;
    }

    public void insertarDeporte(String nombre){
        //todo método insertar deporte
        //Una vez hechi este método podemos probar con la activity normal.
    }
    public void insertarDatosPrueba(){
        //todo insertar deportes y listas
        // Inserción Clientes
       // String cliente1 = datos.insertarCliente(new Cliente(null, "Veronica", "Del Topo", "4552000"));
       // String cliente2 = datos.insertarCliente(new Cliente(null, "Carlos", "Villagran", "4440000"));

    }
}
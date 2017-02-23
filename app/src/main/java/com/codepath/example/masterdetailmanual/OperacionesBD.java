package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//todo estudiar como se maneja la base de datos con singleton
public final class OperacionesBD {


    /*
    Cuando inserto un deporte se genera un id de deporte
     */
    public String insertarDeporte(Deporte deporte,Context context){
        //todo insertar deportes y listas
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getWritableDatabase();

        // Generar Pk
        String id_Deporte = CheckListContract.CheckListDeporte.generarIdDeporte();

        ContentValues valores = new ContentValues();
        valores.put(CheckListContract.CheckListDeporte._ID, id_Deporte);
        valores.put(CheckListContract.CheckListDeporte.COLUMN_NOMBRE, deporte.nombre);

        // Insertar deporte
        db.insertOrThrow(CheckListContract.CheckListDeporte.TABLE_NAME, null, valores);

        return id_Deporte;

    }

/*
    public void insertarDeporte(String nombre){
        //todo método insertar deporte
        //Una vez hechi este método podemos probar con la activity normal.
    }*/

    /*
        Método que devuelve un cursor con la lista de deportes
     */
    /*
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
*/

}
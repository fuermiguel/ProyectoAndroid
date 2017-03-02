package com.codepath.example.masterdetailmanual;

/**
 * Created by fuerm on 21/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public final class OperacionesBD {


    /*
    Cuando inserto un deporte se genera un id de deporte
     */
    public void insertarDeporte(Deporte deporte, Context context) {
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CheckListContract.CheckListDeporte.COLUMN_NAME, deporte.nombre);

        // Insertar deporte
        db.insertOrThrow(CheckListContract.CheckListDeporte.TABLE_NAME, null, valores);

    }

    public void insertarLista(Lista lista, Context context, String nombreDeporte) {

        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getWritableDatabase();

        //Tengo que consultar el _ID del deporte para ponerlo en el campo id_deporte de la tabla lista
        int id_deporte = obtenerIdPorNombre(nombreDeporte, context);

        //Lo hago si existe el deporte
        if (id_deporte > 0) {


            ContentValues valores = new ContentValues();
            valores.put(CheckListContract.ChekListLista.COLUMN_FOTO, lista.foto);
            valores.put(CheckListContract.ChekListLista.COLUMN_DETALLE, lista.detalle);


            valores.put(CheckListContract.ChekListLista.COLUMN_ID_DEPORTE, id_deporte);

            // Insertar lista
            db.insertOrThrow(CheckListContract.ChekListLista.TABLE_NAME, null, valores);
        }

    }

    public Cursor obtenerDeportes(Context context) {
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getReadableDatabase();

        String sql = String.format("SELECT * FROM %s ", CheckListContract.CheckListDeporte.TABLE_NAME);

        //String[] selectionArgs = {};

        return db.rawQuery(sql, null);

    }

    private int obtenerIdPorNombre(String nombre, Context context) {
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getReadableDatabase();

        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                CheckListContract.CheckListDeporte.TABLE_NAME, CheckListContract.CheckListDeporte.COLUMN_NAME);

        String[] selectionArgs = {nombre};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor.moveToNext()) {
            return cursor.getInt(1);
        } else {
            return -1;
        }

    }

    //todo hacer obtenerlista para un deporte dado.

}
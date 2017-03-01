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
    public String insertarDeporte(Deporte deporte,Context context){
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getWritableDatabase();

        // Generar Pk
        String id_deporte = CheckListContract.CheckListDeporte.generarIdDeporte();

        ContentValues valores = new ContentValues();
        valores.put(CheckListContract.CheckListDeporte.ID_DEPORTE, id_deporte);
        valores.put(CheckListContract.CheckListDeporte.COLUMN_NAME, deporte.nombre);

        // Insertar deporte
        db.insertOrThrow(CheckListContract.CheckListDeporte.TABLE_NAME, null, valores);

        return id_deporte;
    }

    public String insertarLista(Lista lista,Context context){
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getWritableDatabase();

        // Generar Pk
        String id_lista = CheckListContract.ChekListLista.generarIdLista();

        ContentValues valores = new ContentValues();
        valores.put(CheckListContract.ChekListLista.ID_LISTA, id_lista);
        valores.put(CheckListContract.ChekListLista.COLUMN_FOTO, lista.foto);
        valores.put(CheckListContract.ChekListLista.COLUMN_FOTO, lista.detalle);
        valores.put(CheckListContract.ChekListLista.COLUMN_FOTO, lista.id_deporte);

        // Insertar deporte
        db.insertOrThrow(CheckListContract.CheckListDeporte.TABLE_NAME, null, valores);

        return id_lista;
    }

    public Cursor obtenerDeportes(Context context) {
        SQLiteDatabase db = ChekListDbHelper.getInstance(context).getReadableDatabase();

        String sql = String.format("SELECT * FROM %s ", CheckListContract.CheckListDeporte.TABLE_NAME);

        //String[] selectionArgs = {};

        return db.rawQuery(sql, null);

    }

    //todo hacer obtenerlista para un deporte dado.

}
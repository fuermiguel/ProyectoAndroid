package com.codepath.example.masterdetailmanual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by diego on 12/10/2016.
 */
/*
    Patrón Singleton Para SQLite
    Crea una nueva clase llamada ChekListDbHelper e implementa un patrón singleton. Esto significa,
    poner su constructor principal como privado, definir un miembro estático de la clase y generar un
    método estático que permita la obtención del único miembro.
 */

public class ChekListDbHelper extends SQLiteOpenHelper {

    private static ChekListDbHelper sInstance =  null;

    private static final String NOMBRE_BASE_DATOS = "cheking.db";

    //Si se cambia la base de datos, hay que incrementar la versión
    private static final int VERSION_ACTUAL = 1;


    public static synchronized ChekListDbHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new ChekListDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private ChekListDbHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
       // this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL UNIQUE, " +
                "%s TEXT NOT NULL UNIQUE)",
                CheckListContract.CheckListDeporte.TABLE_NAME,
                CheckListContract.CheckListDeporte._ID,
                CheckListContract.CheckListDeporte.ID_DEPORTE,
                CheckListContract.CheckListDeporte.COLUMN_NAME));

        db.execSQL(String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL UNIQUE,  " +
                "%s TEXT NOT NULL, " +
                "%s TEXT NOT NULL," +
                "%s TEXT NOT NULL," +
                "FOREIGN KEY(%s) REFERENCES %s(%s) )",
                CheckListContract.ChekListLista.TABLE_NAME,
                CheckListContract.ChekListLista._ID,
                CheckListContract.ChekListLista.ID_LISTA,
                CheckListContract.ChekListLista.COLUMN_FOTO,
                CheckListContract.ChekListLista.COLUMN_DETALLE,
                CheckListContract.ChekListLista.COLUMN_ID_DEPORTE,
                CheckListContract.ChekListLista.COLUMN_ID_DEPORTE,
                CheckListContract.CheckListDeporte.TABLE_NAME,
                CheckListContract.CheckListDeporte.ID_DEPORTE
                ));

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CheckListContract.CheckListDeporte.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CheckListContract.ChekListLista.TABLE_NAME);

        onCreate(db);
    }

}

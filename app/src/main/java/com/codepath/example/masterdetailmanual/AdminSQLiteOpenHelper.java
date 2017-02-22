package com.codepath.example.masterdetailmanual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by diego on 12/10/2016.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "cheking.db";
    private static final int VERSION_ACTUAL = 1;
    private final Context contexto;

    interface Tablas {
        String DEPORTE = "deporte";
        String LISTA = "lista";
    }

    interface Referencias {

        String ID_DEPORTE = String.format("REFERENCES %s(%s)",
                Tablas.DEPORTE, MetadatosTablas.Deportes.ID);

    }

    public AdminSQLiteOpenHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = context;
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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL)",
                Tablas.DEPORTE, BaseColumns._ID, MetadatosTablas.Deportes.ID));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,  %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL %s )",
                Tablas.LISTA, BaseColumns._ID,
                MetadatosTablas.Lista.FOTO, MetadatosTablas.Lista.DETALLE,MetadatosTablas.Lista.ID_DEPORTE,Referencias.ID_DEPORTE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.DEPORTE);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.LISTA);

        onCreate(db);
    }
}

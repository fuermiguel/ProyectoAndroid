package com.codepath.example.masterdetailmanual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by diego on 12/10/2016.
 */

public class ChekListDbHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "cheking.db";

    //Si se cambia la base de datos, hay que incrementar la versión
    private static final int VERSION_ACTUAL = 1;

    //private final Context contexto;

    public ChekListDbHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
       // this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL)",
                CheckListContract.CheckListDeporte.TABLE_NAME,
                CheckListContract.CheckListDeporte._ID,
                CheckListContract.CheckListDeporte.COLUMN_NOMBRE));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,  %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL," +
                        "FOREIGN KEY(%s) REFERENCES %s(%s) )",
                CheckListContract.ChekListLista.TABLE_NAME,
                CheckListContract.ChekListLista._ID,
                CheckListContract.ChekListLista.COLUMN_FOTO,
                CheckListContract.ChekListLista.COLUMN_DETALLE,
                CheckListContract.ChekListLista.COLUMN_ID_DEPORTE,
                CheckListContract.ChekListLista.COLUMN_ID_DEPORTE,
                CheckListContract.CheckListDeporte.TABLE_NAME,
                CheckListContract.CheckListDeporte._ID
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

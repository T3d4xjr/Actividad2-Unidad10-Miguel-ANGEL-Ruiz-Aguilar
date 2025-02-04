package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuariosSQLiteOpenHelper extends SQLiteOpenHelper {
    // Sentencia SQL para crear la tabla "usuarios"
    String sqlCreate = "CREATE TABLE IF NOT EXISTS usuarios (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT NOT NULL, " +
            "puntuacion INTEGER NOT NULL);";

    public UsuariosSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(sqLiteDatabase);
    }
}

package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private SQLiteOpenHelper dbHelper;

    public DatabaseHelper(Context context) {
        dbHelper = new SQLiteOpenHelper(context, "PuntuacionesDB", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS puntuaciones (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "puntuacion INTEGER NOT NULL);");

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS puntuaciones");
                onCreate(sqLiteDatabase);

            }
        };  // Cambié el nombre de la base de datos
    }

    // Método para agregar una nueva puntuación
    public void addScore(String nombre, int puntuacion) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("puntuacion", puntuacion);
        db.insert("puntuaciones", null, values);  // Cambié el nombre de la tabla a "puntuaciones"
        db.close();
    }

    // Método para obtener todas las puntuaciones
    public List<Score> getAllScores() {
        List<Score> scoreList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("puntuaciones", null, null, null, null, null, "puntuacion DESC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"));
                scoreList.add(new Score(id, nombre, puntuacion));  // Cambié de "Usuario" a "Score"
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scoreList;
    }

    // Método para obtener puntuaciones filtradas por nombre
    public List<Score> getScoresByName(String nameFilter) {
        List<Score> scoreList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("puntuaciones", null, "nombre LIKE ?", new String[]{"%" + nameFilter + "%"}, null, null, "puntuacion DESC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"));
                scoreList.add(new Score(id, nombre, puntuacion));  // Cambié de "Usuario" a "Score"
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scoreList;
    }

    // Método para actualizar una puntuación
    public void updateScore(int id, String newName, int newScore) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", newName);
        values.put("puntuacion", newScore);
        db.update("puntuaciones", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para eliminar una puntuación
    public void deleteScore(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("puntuaciones", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

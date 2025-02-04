package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    String sqlCreate = "CREATE TABLE IF NOT EXISTS user_scores (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "score INTEGER, " +
            "timestamp TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, "scoresDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_scores");
        onCreate(db);
    }

    // Método para insertar la puntuación en la base de datos
    public void insertScore(String name, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_TIMESTAMP, System.currentTimeMillis());
        db.insert("user_scores", null, values);
    }

    // Método para obtener todas las puntuaciones
    public Cursor getAllScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, name, score, timestamp FROM user_scores", null);
    }

    // Método para obtener puntuaciones filtradas por nombre
    public Cursor getFilteredScores(String filterName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT _id, name, score, timestamp FROM user_scores WHERE name LIKE ?";
        return db.rawQuery(query, new String[]{"%" + filterName + "%"});
    }

    // Método para actualizar la puntuación de un registro
    public void updateScore(int id, String newName, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_SCORE, newScore); // Ahora también actualizamos la puntuación
        db.update("user_scores", values, "_id = ?", new String[]{String.valueOf(id)});
    }


    // Método para eliminar una puntuación de la base de datos
    public void deleteScore(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user_scores", "_id = ?", new String[]{String.valueOf(id)});
    }
}

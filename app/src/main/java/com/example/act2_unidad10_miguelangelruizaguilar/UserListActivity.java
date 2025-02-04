package com.example.act2_unidad10_miguelangelruizaguilar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserListActivity extends AppCompatActivity {

    ListView lvScores;
    EditText etFilterName, etNewScore;
    Button btnFilter, btnGoBack, btnEdit, btnDelete;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
    int selectedScoreId = -1;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        lvScores = findViewById(R.id.lvScores);
        etFilterName = findViewById(R.id.etFilterName);
        etNewScore = findViewById(R.id.etNewScore); // Campo para nueva puntuación
        btnFilter = findViewById(R.id.btnFilter);
        btnGoBack = findViewById(R.id.btnGoBack);
        btnEdit = findViewById(R.id.btnEdit); // Botón de editar
        btnDelete = findViewById(R.id.btnDelete); // Botón de eliminar

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        // Al hacer clic en el botón de filtrar, filtramos por nombre
        btnFilter.setOnClickListener(v -> {
            String filterName = etFilterName.getText().toString().trim();
            cursor = filterName.isEmpty() ? dbHelper.getAllScores() : dbHelper.getFilteredScores(filterName);
            displayScores(cursor);
        });

        // Mostrar todas las puntuaciones al iniciar la actividad
        cursor = dbHelper.getAllScores();
        displayScores(cursor);

        // Al hacer clic en un item, seleccionamos el id y lo preparamos para modificar o eliminar
        lvScores.setOnItemClickListener((parent, view, position, id) -> {
            cursor.moveToPosition(position); // Mover al item seleccionado
            selectedScoreId = cursor.getInt(cursor.getColumnIndex("_id")); // Obtener el ID del registro seleccionado
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            etFilterName.setText(name); // Poner el nombre del usuario en el EditText
            etNewScore.setText(String.valueOf(score)); // Poner la puntuación en el EditText de puntuación
            Toast.makeText(UserListActivity.this, "Seleccionado para editar/eliminar", Toast.LENGTH_SHORT).show();
        });

        // Cuando el usuario haga clic en el botón de editar
        btnEdit.setOnClickListener(v -> {
            String newName = etFilterName.getText().toString().trim();
            String scoreStr = etNewScore.getText().toString().trim();
            if (selectedScoreId != -1 && !newName.isEmpty() && !scoreStr.isEmpty()) {
                int newScore = Integer.parseInt(scoreStr); // Convertir la puntuación ingresada en int
                dbHelper.updateScore(selectedScoreId, newName, newScore); // Actualizar tanto el nombre como la puntuación
                Toast.makeText(UserListActivity.this, "Puntuación y nombre actualizados", Toast.LENGTH_SHORT).show();
                cursor = dbHelper.getAllScores();
                displayScores(cursor);
            } else {
                Toast.makeText(UserListActivity.this, "Por favor, selecciona un registro para editar y asegúrate de ingresar los datos", Toast.LENGTH_SHORT).show();
            }
        });

        // Cuando el usuario haga clic en el botón de eliminar
        btnDelete.setOnClickListener(v -> {
            if (selectedScoreId != -1) {
                dbHelper.deleteScore(selectedScoreId);  // Eliminar el registro
                Toast.makeText(UserListActivity.this, "Puntuación eliminada", Toast.LENGTH_SHORT).show();
                cursor = dbHelper.getAllScores();
                displayScores(cursor);
            } else {
                Toast.makeText(UserListActivity.this, "Por favor, selecciona un registro para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        // Volver a la pantalla principal
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(UserListActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void displayScores(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            String[] fromColumns = { DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_SCORE, DatabaseHelper.COLUMN_TIMESTAMP };
            int[] toViews = { R.id.tvName, R.id.tvScore, R.id.tvTimestamp };

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_score,
                    cursor,
                    fromColumns,
                    toViews,
                    0
            );
            lvScores.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No hay puntuaciones para mostrar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}

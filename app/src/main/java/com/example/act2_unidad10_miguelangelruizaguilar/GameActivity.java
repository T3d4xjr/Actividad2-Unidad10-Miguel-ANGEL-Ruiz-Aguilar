package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    TextView tvScore;
    Button btnSaveScore, btnGoBack;
    int score;
    String userName;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvScore = findViewById(R.id.tvScore);
        btnSaveScore = findViewById(R.id.btnSaveScore);
        btnGoBack = findViewById(R.id.btnGoBack);

        // Obtener el nombre de usuario desde la Intent
        userName = getIntent().getStringExtra("user_name");

        // Instanciar la base de datos
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        // Generar una puntuación aleatoria
        score = (int) (Math.random() * 1000);
        tvScore.setText("Puntuación: " + score);

        // Guardar la puntuación al presionar el botón
        btnSaveScore.setOnClickListener(v -> {
            if (userName != null && !userName.isEmpty()) {
                dbHelper.insertScore(userName, score);
                Toast.makeText(GameActivity.this, "Puntuación guardada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GameActivity.this, "Por favor ingresa un nombre de usuario", Toast.LENGTH_SHORT).show();
            }
        });

        // Volver a la pantalla principal
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

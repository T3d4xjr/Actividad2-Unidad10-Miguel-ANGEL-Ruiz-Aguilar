package com.example.act2_unidad10_miguelangelruizaguilar;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnStartGame, btnUserList;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnUserList = findViewById(R.id.btnUserlist);

        dbHelper = new DatabaseHelper(this);

        btnStartGame.setOnClickListener(v -> {
            String name = etName.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("user_name", name);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
            }
        });
        btnUserList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        });
    }
}


package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private EditText etNombre;
    private Button btnJugar, btnVerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        btnJugar = findViewById(R.id.btnJugar);
        btnVerUsuarios = findViewById(R.id.btnVerUsuarios);

        btnJugar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            if (!nombre.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
            }
        });

        btnVerUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.act2_unidad10_miguelangelruizaguilar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DataActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listView;
    private EditText etFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);  // Asegúrate de que el layout sea el correcto

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        etFiltro = findViewById(R.id.etFiltro);

        cargarPuntuaciones();  // Método para cargar puntuaciones
    }

    // Cargar puntuaciones de la base de datos
    public void cargarPuntuaciones() {
        String filtro = etFiltro.getText().toString();
        List<Score> puntuaciones = filtro.isEmpty() ? dbHelper.getAllScores() : dbHelper.getScoresByName(filtro);

        // Crear un adaptador para las puntuaciones
        ScoreAdapter adapter = new ScoreAdapter(this, puntuaciones);
        listView.setAdapter(adapter);
    }

    // Método para filtrar las puntuaciones
    public void onFiltrar(View view) {
        cargarPuntuaciones();  // Vuelve a cargar las puntuaciones con el filtro
    }
}

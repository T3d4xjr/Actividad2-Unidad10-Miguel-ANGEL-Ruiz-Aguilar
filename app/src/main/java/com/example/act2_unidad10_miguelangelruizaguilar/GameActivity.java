package com.example.act2_unidad10_miguelangelruizaguilar;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        gameView.onTouch(x);  // Controlar la paleta usando el toque
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }
}

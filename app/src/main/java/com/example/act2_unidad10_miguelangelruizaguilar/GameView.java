package com.example.act2_unidad10_miguelangelruizaguilar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class GameView extends View {
    private Paint paint;
    private Ball ball;
    private Paddle paddle;
    private Block[] blocks;
    private Handler handler;
    private boolean isGameRunning;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        ball = new Ball(500, 100, 10, Color.RED);
        paddle = new Paddle(400, 800, 200, 30, Color.BLUE);
        blocks = new Block[20];

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(100 + (i % 5) * 150, 50 + (i / 5) * 50, 100, 30, Color.GREEN);
        }

        handler = new Handler();
        isGameRunning = true;
        startGameLoop();
    }

    private void startGameLoop() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    updateGame();
                    invalidate();
                    handler.postDelayed(this, 30);
                }
            }
        }, 30);
    }

    private void updateGame() {
        ball.update();
        paddle.update();
        checkCollisions();
    }

    private void checkCollisions() {
        // Comprobar si la pelota toca la paleta
        if (ball.collidesWith(paddle)) {
            ball.reverseYDirection();
        }

        // Comprobar si la pelota toca los bloques
        for (Block block : blocks) {
            if (block != null && ball.collidesWith(block)) {
                block.setDestroyed(true);  // Destruir bloque
                ball.reverseYDirection();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        // Dibujar la pelota
        ball.draw(canvas, paint);

        // Dibujar la paleta
        paddle.draw(canvas, paint);

        // Dibujar bloques
        for (Block block : blocks) {
            if (block != null && !block.isDestroyed()) {
                block.draw(canvas, paint);
            }
        }
    }

    public void onTouch(float x) {
        paddle.setX(x - paddle.getWidth() / 2);  // Mover la paleta con el toque
    }

    public void pauseGame() {
        isGameRunning = false;
    }

    public void resumeGame() {
        isGameRunning = true;
        startGameLoop();
    }
}
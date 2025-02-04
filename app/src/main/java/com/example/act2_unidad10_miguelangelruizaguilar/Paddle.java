package com.example.act2_unidad10_miguelangelruizaguilar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Paddle {
    private float x, y, width, height;
    private int color;

    public Paddle(float x, float y, float width, float height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void update() {
        // Puedes agregar control para el movimiento de la paleta aquí
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getWidth() {
        return width;
    }

    public float getY() {
        return y;
    }
}

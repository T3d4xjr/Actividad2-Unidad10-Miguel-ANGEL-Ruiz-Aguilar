package com.example.act2_unidad10_miguelangelruizaguilar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block {
    private float x, y, width, height;
    private int color;
    private boolean isDestroyed;

    public Block(float x, float y, float width, float height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isDestroyed = false;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (!isDestroyed) {
            paint.setColor(color);
            canvas.drawRect(x, y, x + width, y + height, paint);
        }
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}


package com.example.act2_unidad10_miguelangelruizaguilar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float x, y, radius;
    private int color;
    private float dx, dy;

    public Ball(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.dx = 5;  // Velocidad de la pelota en el eje X
        this.dy = 5;  // Velocidad de la pelota en el eje Y
    }

    public void update() {
        x += dx;
        y += dy;

        // Rebote en las paredes
        if (x <= 0 || x >= 1000) dx = -dx;
        if (y <= 0 || y >= 800) dy = -dy;
    }

    public void reverseYDirection() {
        dy = -dy;
    }

    public boolean collidesWith(Paddle paddle) {
        return x > paddle.getX() && x < paddle.getX() + paddle.getWidth() && y + radius > paddle.getY();
    }

    public boolean collidesWith(Block block) {
        return x > block.getX() && x < block.getX() + block.getWidth() && y - radius < block.getY() + block.getHeight() && y + radius > block.getY();
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }
}

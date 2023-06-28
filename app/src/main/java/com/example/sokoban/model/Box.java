package com.example.sokoban.model;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Paint mPaint, Canvas canvas) {

        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        int xc = getX1() + 450;
        int yc = getY1();
        int height = getHeight1();
        int width = getWidth1();

        canvas.drawRect(xc  - width/2, yc + height / 2, xc + width / 2, yc - height /2, mPaint);
        canvas.drawLine(xc - width / 2, yc - height / 2, xc + width / 2, yc + height / 2, mPaint);
        canvas.drawLine(xc - width / 2, yc + height / 2, xc + width / 2, yc - height / 2, mPaint);
    }

    @Override
    public void move(int x, int y) {
        this.x1 += x;
        this.y1 += y;
    }
}

package com.example.sokoban.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;


public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Paint mPaint, Canvas canvas) {
        mPaint.setColor(Color.rgb(205, 105, 30));
        mPaint.setStyle(Paint.Style.FILL);
        int xc = getX1() + 450;
        int yc = getY1();
        int height = getHeight1();
        int width = getWidth1();

        canvas.drawRect(xc - width / 2, yc + height / 2, xc + width / 2, yc - height / 2, mPaint);

    }
}

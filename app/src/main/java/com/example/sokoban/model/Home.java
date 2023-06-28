package com.example.sokoban.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



public class Home extends GameObject {

    public Home(int x, int y) {
        super(x, y);
        this.height1 = 7;
        this.width1 = 7;
    }

    @Override
    public void draw(Paint mPaint, Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        int xc = getX1() + 450;
        int yc = getY1();
        int height = getHeight1();
        int width = getWidth1();

        canvas.drawRect(xc  - width/2, yc + height / 2, xc + width / 2, yc - height /2, mPaint);
    }
}

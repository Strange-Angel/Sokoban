package com.example.sokoban.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public abstract class GameObject {
    int x1;
    int y1;
    int width1;
    int height1;

    public GameObject(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        width1 = Model.FIELD_CELL_SIZE;
        height1 = Model.FIELD_CELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height) {

        this.x1 = x;
        this.y1 = y;
        this.width1 = width;
        this.height1 = height;
    }

    public abstract void draw(Paint mPaint, Canvas canvas);



    public void setX(int x) {
        this.x1 = x;
    }



    public void setY(int y) {
        this.y1 = y;
    }



    public void setWidth(int width) {
        this.width1 = width;
    }



    public void setHeight(int height) {
        this.height1 = height;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getWidth1() {
        return width1;
    }

    public int getHeight1() {
        return height1;
    }
}

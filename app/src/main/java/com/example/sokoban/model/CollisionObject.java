package com.example.sokoban.model;

import android.content.Context;

abstract public class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                return ((x1 - Model.FIELD_CELL_SIZE) == gameObject.getX1()) && (y1 == gameObject.getY1());
            case RIGHT:
                return ((x1 + Model.FIELD_CELL_SIZE) == gameObject.getX1()) && (y1 == gameObject.getY1());
            case UP:
                return ((x1 == gameObject.getX1() && (y1 - Model.FIELD_CELL_SIZE) == gameObject.getY1()));
            case DOWN:
                return ((x1 == gameObject.getX1() && (y1 + Model.FIELD_CELL_SIZE) == gameObject.getY1()));
        }
        return false;
    }
}

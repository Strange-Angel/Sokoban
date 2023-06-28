package com.example.sokoban.model;


import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.FileOutputStream;

import com.example.sokoban.MainActivity;
import com.example.sokoban.controller.EventListener;

import java.io.*;

public class Model {
    public static final int FIELD_CELL_SIZE = 50;

    private GameObjects gameObjects;
    public static int currentLevel = 1;
    private EventListener eventListener;
    LevelLoader levelLoader;
    Context mContext;


    public Model(Context context) {
        mContext = context;

        AssetManager asset = context.getResources().getAssets();
        try {
            InputStream res = asset.open("levels.txt");
            levelLoader = new LevelLoader(res);
        } catch (Exception e) {
        }
    }



    public void saveGame() {
        FileOutputStream fos = null;
        try {
            fos = mContext.openFileOutput("save.txt", Context.MODE_PRIVATE);
            Integer l = currentLevel;
            fos.write(l.toString().getBytes());
            fos.close();
        } catch (IOException e) {

        }


    }


    public int loadGame() {
        String level = null;
        FileInputStream fin = null;
        try {
            fin = mContext.openFileInput("save.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            level = new String(bytes);
            fin.close();
            currentLevel = Integer.parseInt(level);


        } catch (Exception e) {

        }
        return currentLevel;

    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)) {
            return;
        }

        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }

        int dx = direction == Direction.LEFT ? -FIELD_CELL_SIZE : (direction == Direction.RIGHT ? FIELD_CELL_SIZE : 0);
        int dy = direction == Direction.UP ? -FIELD_CELL_SIZE : (direction == Direction.DOWN ? FIELD_CELL_SIZE : 0);
        gameObjects.getPlayer().move(dx, dy);

        checkCompletion();
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
        saveGame();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }


    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                for (Box item : gameObjects.getBoxes()) {
                    if (!box.equals(item)) {
                        if (box.isCollision(item, direction)) {
                            return true;
                        }
                    }
                    if (checkWallCollision(box, direction)) {
                        return true;
                    }
                }
                int dx = direction == Direction.LEFT ? -FIELD_CELL_SIZE : (direction == Direction.RIGHT ? FIELD_CELL_SIZE : 0);
                int dy = direction == Direction.UP ? -FIELD_CELL_SIZE : (direction == Direction.DOWN ? FIELD_CELL_SIZE : 0);
                box.move(dx, dy);
            }
        }
        return false;
    }

    public void checkCompletion() {
        //eventListener.levelCompleted(currentLevel);
        int numberOfHomes = gameObjects.getHomes().size();
        int numberOfHomesWithBox = 0;

        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (box.getX1() == home.getX1() && box.getY1() == home.getY1()) {
                    numberOfHomesWithBox++;
                }
            }
        }

        if (numberOfHomesWithBox == numberOfHomes) {
            eventListener.levelCompleted(currentLevel);
        }
    }


    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }
}

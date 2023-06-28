package com.example.sokoban.controller;

import android.content.Context;

import com.example.sokoban.model.Direction;
import com.example.sokoban.model.GameObjects;
import com.example.sokoban.model.Model;
import com.example.sokoban.view.Draw2D;
import com.example.sokoban.view.OnSwipeTouchListener;

public class Controller implements EventListener {
    private Draw2D draw2D;
    private Model model;
    private  Context mContext;
    private OnSwipeTouchListener onSwipeTouchListener;


    public Controller(Context context) {
        mContext = context;
        model = new Model(context);
        model.restart();
        draw2D = new Draw2D(this, context);
        draw2D.init();
        draw2D.setEventListener(this);
        model.setEventListener(this);
        draw2D.getField().setEventListener(this);
        onSwipeTouchListener = new OnSwipeTouchListener(context);
        onSwipeTouchListener.setField(draw2D.getField());
        onSwipeTouchListener.setEventListener(this);
        draw2D.getField().setOnTouchListener(onSwipeTouchListener);
        loadGame();
        draw2D.simulateTouch();


    }


    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }


    @Override
    public void move(Direction direction) {
        model.move(direction);
        draw2D.update();
    }

    @Override
    public void restart() {
        model = new Model(mContext);
        model.setEventListener(this);
        model.restart();
        draw2D.update();
    }

    @Override
    public void startNextLevel() {
        model = new Model(mContext);
        model.setEventListener(this);
        model.startNextLevel();
        draw2D.update();
    }


    @Override
    public void levelCompleted(int level) {
        draw2D.completed(level);
    }

    public Draw2D getDraw2D() {
        return draw2D;
    }


    @Override
    public void saveGame() {
        model.saveGame();
        //draw2D.save();
    }

    @Override
    public void loadGame() {
        model = new Model(mContext);
        model.setEventListener(this);
        int l = model.loadGame();
        model.restartLevel(l);
        draw2D.update();
        draw2D.load(l);
    }

    @Override
    public void loadGame(int l) {
        model = new Model(mContext);
        model.setEventListener(this);
        model.restartLevel(l);
        Model.currentLevel = l;
        draw2D.update();
        draw2D.load(l);
        saveGame();
    }

    @Override
    public OnSwipeTouchListener getOnSwipeTouchListener() {
        return onSwipeTouchListener;
    }
}

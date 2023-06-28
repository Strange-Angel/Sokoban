package com.example.sokoban.controller;


import com.example.sokoban.model.Direction;
import com.example.sokoban.model.Model;
import com.example.sokoban.view.OnSwipeTouchListener;

public interface EventListener {
     void move(Direction direction);
     void restart();
     void startNextLevel();
     void levelCompleted(int level);
     void saveGame();
     void loadGame();
     void loadGame(int l);
     OnSwipeTouchListener getOnSwipeTouchListener();
}

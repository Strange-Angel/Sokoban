package com.example.sokoban.model;


import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Set<Wall> walls;
    private Set<Home> homes;
    private Player player;
    private Set<Box> boxes;

    public GameObjects(Set<Wall> walls,Set<Box> boxes,  Set<Home> homes, Player player ) {
        this.walls = walls;
        this.homes = homes;
        this.player = player;
        this.boxes = boxes;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allObjects = new HashSet<>();
        allObjects.addAll(boxes);
        allObjects.add(player);
        allObjects.addAll(homes);
        allObjects.addAll(walls);
        return allObjects;
    }
}

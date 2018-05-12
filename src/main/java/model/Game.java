package model;

public abstract class Game {

    public Field[][] table;

    public Game(int width, int height) {
        this.table = new Field[width][height];
    }

    public abstract void shoot(int x, int y);
    public abstract boolean isOver();

    public int getWidth(){
        return table[0].length;
    }

    public int getHeight(){
        return table.length;
    }

}

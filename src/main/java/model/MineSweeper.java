package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSweeper extends Game {

    private static int minesCounterHelper = 20; // 20 = every 20th field is mine, 1 = every single field is mine

    private boolean isOver = false;
    private boolean win = false;

    public boolean isWin() {
        return win;
    }

    public MineSweeper(int width, int height) {
        super(width,height);
        Random rn = new Random();
        int x, y;
        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                table[i][j] = new MineSweeperField();
            }
        }

        int mines = width * height / minesCounterHelper;
        while (mines > 0)
        {
            x = rn.nextInt(height);
            y = rn.nextInt(width);
            if (!((MineSweeperField)table[x][y]).isMined())
            {
                ((MineSweeperField)table[x][y]).setMined(true);
                mines--;
            }
        }

        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                ((MineSweeperField)table[i][j]).setMinesNear(countMinesNear(i,j));
                ((MineSweeperField)table[i][j]).setFieldText();
            }
        }
    }

    public void flag(int x, int y){
        ((MineSweeperField)table[x][y]).flag();
    }

    public void shoot(int x, int y) {
        int ypos = y;
        int xpos = x;
        if (!((MineSweeperField)table[xpos][ypos]).isFlagged() && !((MineSweeperField)table[xpos][ypos]).isRevealed())
        {
            Recursive(xpos, ypos);
            isOver = ((MineSweeperField)table[xpos][ypos]).isMined();
        }
        if (CountRemaining() <= getWidth()*getHeight()/minesCounterHelper)
        {
            win = true;
        }
    }

    public boolean isOver() {
        return isOver;
    }

    private int countMinesNear(int xpos, int ypos)
    {
        int mines = 0;
        int ymin = Math.max(0, ypos - 1);
        int ymax = Math.min(getHeight() - 1, ypos + 1);
        int xmin = Math.max(0, xpos - 1);
        int xmax = Math.min(getWidth() - 1, xpos + 1);
        for (int x = xmin; x <= xmax; x++)
        {
            for (int y = ymin; y <= ymax; y++)
            {
                if (((MineSweeperField)table[x][y]).isMined()){
                    mines++;
                }
            }
        }
        if (((MineSweeperField)table[xpos][ypos]).isMined()){
            mines--;
        }
        return mines;
    }

    private void Recursive(int x, int y)
    {
        if (y >= 0 && y < getWidth() && x >= 0 && x < getHeight() && // valid coords AND
                !((MineSweeperField)table[x][y]).isMined() && !((MineSweeperField)table[x][y]).isFlagged() && // not mine AND not flagged AND
                !((MineSweeperField)table[x][y]).isRevealed()) // unknown AND
        {
            ((MineSweeperField)table[x][y]).setRevealed(true);
            if (((MineSweeperField)table[x][y]).getMinesNear() == 0) // 0 mines nearby
            {
                Recursive(x, y - 1);
                Recursive(x - 1, y);
                Recursive(x + 1, y);
                Recursive(x, y + 1);
            }
        }
    }

    private int CountRemaining()
    {
        int remaining = 0;
        for (int x = 0; x < getHeight(); x++)
        {
            for (int y = 0; y < getWidth(); y++)
            {
                if (!((MineSweeperField)table[x][y]).isRevealed()){
                    remaining++;
                }
            }
        }
        return remaining;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                s += (table[i][j].text + " ");
            }
            s += "\n";
        }
        return s;
    }
}

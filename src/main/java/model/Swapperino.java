package model;

public class Swapperino extends Game {

    public static int helper = 50;

    public Swapperino(int width, int height) {
        super(width,height);
        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                table[i][j] = new FillerField();
            }
        }
    }

    public void shoot(int x, int y) {
        if (x >= 0 && y >= 0 && x <= getHeight() && y <= getWidth())
        {
            ((FillerField)table[x][y]).swap();

            if (x < getHeight() - 1){
                ((FillerField)table[x+1][y]).swap();
            }
            if (x > 0){
                ((FillerField)table[x-1][y]).swap();
            }
            if (y < getWidth() - 1){
                ((FillerField)table[x][y+1]).swap();
            }
            if (y > 0){
                ((FillerField)table[x][y-1]).swap();
            }
        }
    }

    public int filledWith(char c){
        int count = 0;
        for (int i = 0; i < getHeight(); i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                if (table[i][j].text == c)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isOver() {
        return filledWith('-') <= helper ? true : false;
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

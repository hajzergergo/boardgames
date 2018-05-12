package model;

public class MineSweeperField extends Field {

    private int minesNear;
    private boolean flagged;
    private boolean mined;
    private boolean revealed;

    public MineSweeperField() {
        this.flagged = false;
        this.mined = false;
        this.revealed = false;
    }

    public int getMinesNear() {
        return minesNear;
    }

    public void setMinesNear(int minesNear) {
        this.minesNear = minesNear;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public void reveal(){
        if (!revealed){
            revealed = true;
        }
    }

    public void flag(){
        flagged = !flagged;
    }

    public void setFieldText()
    {
        if (flagged)
        {
            text = 'F';
            return;
        }
        if (mined)
        {
            text = 'M';
            return;
        }
        text = Character.forDigit(minesNear,10);
        return;
    }
}

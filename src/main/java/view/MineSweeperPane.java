package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Game;
import model.Swapperino;

public class MineSweeperPane extends StackPane {

    private static final int FIELD_SIZE = 40;

    private int x;
    private int y;
    public Text tx;
    private boolean revealed;

    private Rectangle border = new Rectangle(38, 38);

    public static int getFieldSize() {
        return FIELD_SIZE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTx(Text tx) {
        this.tx = tx;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }


    public MineSweeperPane(char c, int x, int y) {
        this.x = x;
        this.y = y;
        this.tx = new Text(c + "");
        border.setStroke(Color.LIGHTGRAY);
        border.setFill(Paint.valueOf("#ffffff"));

        getChildren().addAll(border, tx);

        tx.setFont(Font.font(18));
        tx.setVisible(false);

        setTranslateX(x * FIELD_SIZE);
        setTranslateY(y * FIELD_SIZE);
    }

    public Text getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx.setText(tx);
    }

    public void showTx(){
        tx.setVisible(true);
    }
}

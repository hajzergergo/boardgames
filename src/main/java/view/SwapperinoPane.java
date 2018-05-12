package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Game;
import model.Swapperino;

public class SwapperinoPane extends StackPane {

    private static final int FIELD_SIZE = 40;

    private Text tx;
    private Rectangle border = new Rectangle(38, 38);

    public SwapperinoPane(char c, int x, int y) {
        this.tx = new Text(c + "");
        border.setStroke(Color.LIGHTGRAY);
        border.setFill(Paint.valueOf("#ffffff"));

        getChildren().addAll(border, tx);

        tx.setFont(Font.font(18));

        setTranslateX(x * FIELD_SIZE);
        setTranslateY(y * FIELD_SIZE);
    }

    public Text getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx.setText(tx);
    }
}

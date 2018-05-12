package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import org.springframework.util.StopWatch;
import toplist.DomParser;
import toplist.Player;
import view.SwapperinoPane;
import view.MineSweeperPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public void initialize(URL location, ResourceBundle resources) {

    }

    private
    SwapperinoPane[][] swapperinoPanes;
    MineSweeperPane[][] mineSweeperPanes;

    private Player player;

    @FXML
    private Label label;

    @FXML
    private TextField usernameTF;

    @FXML
    private void handleStartMineSweeperBtn(ActionEvent e){
        Stage stage = (Stage) label.getScene().getWindow();
        Parent root = null;

        MineSweeper g = new MineSweeper(10,10);
        root = createMineSweeperContent(g);

        Scene scene = new Scene(root);

        stage.setTitle("Mine sweeper");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleStartSwapperinoBtn(ActionEvent e){
        Stage stage = (Stage) label.getScene().getWindow();
        Parent root = null;

        Swapperino g = new Swapperino(10,10);
        root = createSwapperinoContent(g);

        Scene scene = new Scene(root);

        stage.setTitle("Swapperino");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label msLB;

    public Parent createSwapperinoContent(Swapperino g) {
        Pane root = new Pane();
        root.setPrefSize(400, 400);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Game over");

        String playerName = usernameTF.getText();
        swapperinoPanes = new SwapperinoPane[g.getHeight()][g.getWidth()];
        StopWatch stopWatch = new StopWatch();

        for (int x = 0; x < g.getHeight(); x++) {
            for (int y = 0; y < g.getWidth(); y++) {
                swapperinoPanes[x][y] = new SwapperinoPane(g.table[x][y].getText(),x,y);
                root.getChildren().add(swapperinoPanes[x][y]);
                int finalX = x;
                int finalY = y;
                swapperinoPanes[x][y].setOnMouseClicked(e -> {
                    g.shoot(finalX, finalY);
                    refreshSwapperinoContent(g);
                    if (g.isOver()) {
                        stopWatch.stop();
                        DomParser.writeToTopSwapperino(new Player(playerName,10000-(int)stopWatch.getTotalTimeSeconds()));
                        alert.setContentText(playerName + ": " + (10000-(int)stopWatch.getTotalTimeSeconds()));
                        alert.showAndWait();
                        goToMainScreen("swapperino");
                    }
                });
            }
        }

        stopWatch.start();
        return root;
    }

    public Parent createMineSweeperContent(MineSweeper g) {
        Pane root = new Pane();
        root.setPrefSize(400, 400);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Game over");

        String playerName = usernameTF.getText();
        mineSweeperPanes = new MineSweeperPane[g.getHeight()][g.getWidth()];
        StopWatch stopWatch = new StopWatch();

        for (int x = 0; x < g.getHeight(); x++) {
            for (int y = 0; y < g.getWidth(); y++) {
                mineSweeperPanes[x][y] = new MineSweeperPane(g.table[x][y].getText(),x,y);
                root.getChildren().add(mineSweeperPanes[x][y]);
                int finalX = x;
                int finalY = y;
                mineSweeperPanes[x][y].setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.SECONDARY)){
                        g.flag(finalX,finalY);
                        rightClickOnField(g);
                    }
                    else{
                        g.shoot(finalX, finalY);
                    }
                    refreshMineSweeperContent(g);
                    if (g.isWin()){
                        stopWatch.stop();
                        DomParser.writeToTopMineSweeper(new Player(playerName,10000-(int)stopWatch.getTotalTimeSeconds()));
                        alert.setContentText("YOU WIN  " + playerName + ": " + (10000-(int)stopWatch.getTotalTimeSeconds()));
                        alert.showAndWait();
                        goToMainScreen("minesweeper");
                    }
                    if (g.isOver()){
                        stopWatch.stop();
                        alert.setContentText("YOU LOSE  " + playerName);
                        alert.showAndWait();
                        goToMainScreen("minesweeper");
                    }
                });
            }
        }

        stopWatch.start();
        return root;
    }

    public void refreshSwapperinoContent(Game g){
        for (int x = 0; x < g.getHeight(); x++) {
            for (int y = 0; y < g.getWidth(); y++) {
                swapperinoPanes[x][y].setTx(g.table[x][y].getText()+"");
            }
        }
    }
    public void refreshMineSweeperContent(Game g){
        for (int x = 0; x < g.getHeight(); x++) {
            for (int y = 0; y < g.getWidth(); y++) {

                if(((MineSweeperField)g.table[x][y]).isRevealed()){
                    mineSweeperPanes[x][y].setTx(g.table[x][y].getText()+"");
                    mineSweeperPanes[x][y].tx.setVisible(true);
                }
            }
        }
    }
    public void rightClickOnField(Game g){
        for (int x = 0; x < g.getHeight(); x++) {
            for (int y = 0; y < g.getWidth(); y++) {
                if (((MineSweeperField)g.table[x][y]).isFlagged()){
                    mineSweeperPanes[x][y].tx.setText("F");
                    mineSweeperPanes[x][y].tx.setVisible(true);
                }
                else{
                    mineSweeperPanes[x][y].setTx(g.table[x][y].getText()+"");
                    mineSweeperPanes[x][y].tx.setVisible(false);
                }

            }
        }
    }


    public void handleTopTen(ActionEvent actionEvent) {
        Stage stage = (Stage) label.getScene().getWindow();

        FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/topten.fxml"));
        try {
            Parent root = fl.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("mainscreen.css");

            stage.setTitle("Top 10");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ListView minesweeperListV;

    public void handleMinesweeperGetBtn(ActionEvent actionEvent) {
        swapperinoListV.setOpacity(0);
        minesweeperListV.setOpacity(1);
        minesweeperListV.setItems(FXCollections.observableArrayList(DomParser.readFromTopMindeSweeper()));
    }

    @FXML
    private ListView swapperinoListV;

    public void handleSwapperinoBtn(ActionEvent actionEvent) {
        minesweeperListV.setOpacity(0);
        swapperinoListV.setOpacity(1);
        swapperinoListV.setItems(FXCollections.observableArrayList(DomParser.readFromTopSwapperino()));
    }

    public void handleGoBackBtn(ActionEvent actionEvent) {
        goToMainScreen("");
    }

    public void goToMainScreen(String s){
        try {
            Stage stage;
            if (s.equals("swapperino"))
                stage = (Stage) swapperinoPanes[0][0].getScene().getWindow();
            else if (s.equals("minesweeper"))
                stage = (Stage) mineSweeperPanes[0][0].getScene().getWindow();
            else
                stage = (Stage) swapperinoListV.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("mainscreen.css");

            stage.setTitle("Board Games");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

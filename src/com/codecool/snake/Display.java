package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import java.awt.event.ActionListener;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    private Text text;

    public Display(Pane pane) {
        displayPane = pane;
    }

    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }

    public void showString( String textOfValue, String value, int xCoordinate, int yCoordinate, String fontType, int fontSize) {
        this.text = new Text();
        text.setText(textOfValue + value);
        text.setFont(Font.font(fontType, fontSize));
        text.setFill(Color.WHITE);
        text.setX(xCoordinate);
        text.setY(yCoordinate);
        displayPane.getChildren().add(text);
    }

    public void updateString( String textOfValue, String value) {
        text.setText(textOfValue + value);
    }

    public void addRestartButton() {
        Button button = new Button("Restart");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Globals.getInstance().game.reStartGame();
            }
        });


        button.setLayoutX(1700);
        button.setLayoutY(980);
        displayPane.getChildren().add(button);
    }
}

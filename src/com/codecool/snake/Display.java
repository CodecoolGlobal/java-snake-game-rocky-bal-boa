package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();

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

    public void showString(String string, int x, int y) {
        Text text = new Text();
        text.setText("Current health:" + string);
        text.setX(x);
        text.setY(y);
        displayPane.getChildren().add(text);
    }
}

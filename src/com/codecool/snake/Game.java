package com.codecool.snake;

import com.codecool.snake.entities.enemies.ExtraEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.EggPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.codecool.snake.entities.snakes.Snake;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    public int score;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        this.score = 0;

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(10);
        spawnPowerUps(10);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().display.showString( "Current health: ", Integer.toString(snake.getHealth()), 100, 100,"Verdana", 50);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        Globals.getInstance().display.addRestartButton();

    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    public void reStartGame() {
        Globals.getInstance().stopGame();
        Globals.getInstance().display.clear();
        Globals.getInstance().display.addRestartButton();
        init();
        start();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            new SimpleEnemy();
            new ExtraEnemy();
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) {
            new SimplePowerUp();
            new EggPowerUp();
        }
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}

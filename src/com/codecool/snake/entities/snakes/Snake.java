package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Display;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import java.util.Random;


public class Snake implements Animatable {
    private static float speed = 2;
    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;



      public static void setSpeed(float speed) {
        Snake.speed = speed;

    }

    public void setHealth(int health) {
        this.health = health;
    }

  
    public int getHealth() {
        return health;
    }

  
    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
        Random rand = new Random();
        int randomNum = rand.nextInt(200);
        if (randomNum == 0) {
            Globals.getInstance().game.spawnEnemies(1);
        } else if (randomNum == 4) {
            Globals.getInstance().game.spawnPowerUps(1);
        }
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().display.clear();
            Globals.getInstance().game.setTableBackground(new Image("gameover.png"));
            Globals.getInstance().display.showString("Your Score:  ", Integer.toString(Globals.getInstance().game.score),600, 980, "Verdana", 100);
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}

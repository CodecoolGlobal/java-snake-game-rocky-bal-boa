package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Random;

public class ExtraEnemy extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();

    public ExtraEnemy() {
        super(-20);

        setImage(Globals.getInstance().getImage("ExtraEnemy"));

        boolean isColliding;

        do {
            isColliding = false;
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
            List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
            for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
                GameEntity objToCheck = gameObjs.get(idxToCheck);
                if (objToCheck.getBoundsInParent().intersects(this.getBoundsInParent())) {
                    isColliding = true;
                    break;
                }
            }
        } while (isColliding);


        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 2;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            Snake.setSpeed(2);
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
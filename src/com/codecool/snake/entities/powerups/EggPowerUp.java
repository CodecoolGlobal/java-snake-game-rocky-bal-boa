package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class EggPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public EggPowerUp() {
        setImage(Globals.getInstance().getImage("egg"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            Globals.getInstance().game.score++;
            Snake.setSpeed(4);
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
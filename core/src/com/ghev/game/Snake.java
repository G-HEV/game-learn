package com.ghev.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final Texture texture;
    private  List<GridPoint2> snake_segments = new ArrayList<>();
    private MovementDirection direction;
    private float timeSinceLastMove;

    public Snake(Texture texture) {
        this.texture = texture;

        direction = MovementDirection.RIGHT;
        snake_segments.add(new GridPoint2(90,30));
        snake_segments.add(new GridPoint2(75,30));
        snake_segments.add(new GridPoint2(60,30));
        snake_segments.add(new GridPoint2(45,30));
        snake_segments.add(new GridPoint2(30,30));

    }

    public void draw(Batch batch){
        for(GridPoint2 position : snake_segments){
            batch.draw(texture,position.x,position.y);
        }
    }

    private void changeDirection(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && direction != MovementDirection.DOWN){
            direction = MovementDirection.UP;
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S) && direction != MovementDirection.UP){
            direction = MovementDirection.DOWN;
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A) && direction != MovementDirection.RIGHT){
            direction= MovementDirection.LEFT;
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && direction != MovementDirection.LEFT){
            direction = MovementDirection.RIGHT;
        }
    }

    public void move(float deltaTime) {
        changeDirection();
        timeSinceLastMove += deltaTime;

        if (timeSinceLastMove > 0.1) {
            timeSinceLastMove = 0;

            move_logic();
        }
    }
    public void move_logic() {

        int segmentWight= texture.getWidth();
        int segmentHeight = texture.getHeight();
        int lastWindowSegmentX = Gdx.graphics.getWidth() - segmentWight;
        int lastWindowSegmentY = Gdx.graphics.getHeight() - segmentHeight;

                for (int i = snake_segments.size() - 1; i > 0; i--) {
                    snake_segments.get(i).set(snake_segments.get(i - 1));
                }
                GridPoint2 head_snake = snake_segments.get(0);

                switch (direction) {
                    case LEFT: {
                        head_snake.x = (head_snake.x==0) ? lastWindowSegmentX : head_snake.x - segmentWight;
                        break;
                    }
                    case UP: {
                        head_snake.y= (head_snake.y == lastWindowSegmentY) ? 0 : head_snake.y + segmentHeight;
                        break;
                    }
                    case DOWN: {
                        head_snake.y= (head_snake.y== 0) ? lastWindowSegmentY : head_snake.y - segmentHeight;
                        break;
                    }
                    case RIGHT: {
                        head_snake.x = (head_snake.x==lastWindowSegmentX) ? 0 : head_snake.x + segmentWight;
                        break;
                    }
                    default:
                        break;
                }
            }
        }

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
    private  List<GridPoint2> snakeSegments = new ArrayList<>();
    private MovementDirection direction;
    private float timeSinceLastMove;


    private boolean changingDirection=false;



    public Snake(Texture texture) {
        this.texture = texture;

        direction = MovementDirection.RIGHT;
        snakeSegments.add(new GridPoint2(90,30));
        snakeSegments.add(new GridPoint2(75,30));
        snakeSegments.add(new GridPoint2(60,30));
        snakeSegments.add(new GridPoint2(45,30));
        snakeSegments.add(new GridPoint2(30,30));


    }

    public void draw(Batch batch){
        for(GridPoint2 position : snakeSegments){
            batch.draw(texture,position.x,position.y);
        }
    }

    private void changeDirection(){
        MovementDirection fixDirection = direction;

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && direction != MovementDirection.DOWN){

            fixDirection = MovementDirection.UP;

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S) && direction != MovementDirection.UP){

            fixDirection = MovementDirection.DOWN;

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A) && direction != MovementDirection.RIGHT){

            fixDirection= MovementDirection.LEFT;

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && direction != MovementDirection.LEFT){

            fixDirection = MovementDirection.RIGHT;
        }
        if(fixDirection!=direction){
            direction = fixDirection;
            changingDirection=false;
        }
    }



    public void move(float deltaTime) {
        if(changingDirection) {
            changeDirection();
        }
        timeSinceLastMove += deltaTime;

        if (timeSinceLastMove > 0.1) {
            timeSinceLastMove = 0;
            changingDirection=true;
            move_logic();
        }
    }
    public void move_logic() {

        int segmentWight= texture.getWidth();
        int segmentHeight = texture.getHeight();

        int lastWindowSegmentX = Gdx.graphics.getWidth() - segmentWight;
        int lastWindowSegmentY = Gdx.graphics.getHeight() - segmentHeight;

                for (int i = snakeSegments.size() - 1; i > 0; i--) {
                    snakeSegments.get(i).set(snakeSegments.get(i - 1));
                }
                GridPoint2 head_snake = head();

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
            public boolean isFoundPoint(GridPoint2 point){
        return head().equals(point);
            }

            public void addSegment(){

            snakeSegments.add(new GridPoint2(snakeSegments.get(snakeSegments.size()-1)));
            }

    private GridPoint2 head() {
        return snakeSegments.get(0);
    }
    public boolean isCollision(){

        for (int i = snakeSegments.size() - 1; i > 0; i--) {
            if( snakeSegments.get(i).equals(head())){
                return true;
            }
        }
        return false;
    }
    public List<GridPoint2> allPositionSnakeSegments(){
        System.out.println("snake: "+snakeSegments);
        return new ArrayList<>(snakeSegments);
    }

}

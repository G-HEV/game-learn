package com.ghev.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;


public class Point {

    private final Texture texture;
    private final GridPoint2 position;

    private Snake snake;

    public Point(Texture texture) {
        this.texture = texture;
        this.position = new GridPoint2();
        this.snake = new Snake(texture);

        randomizePosition(snake);
    }
    public void draw( Batch batch){
        batch.draw(texture,position.x,position.y);
    }

    public void randomizePosition(Snake snakePositionSegments) {

        int numberOfXPositions =
                Gdx.graphics.getWidth() / texture.getWidth();
        int numberOfYPositions =
                Gdx.graphics.getHeight() / texture.getHeight();
        this.position.set(
                (int) (Math.random() * numberOfXPositions) * texture.getWidth(),
                (int) (Math.random() * numberOfYPositions) * texture.getHeight());
        while(snakePositionSegments.allPositionSnakeSegments()
                .stream()
                .anyMatch(segment -> segment.equals(position))){
            randomizePosition(snakePositionSegments);
        }
    }


    public GridPoint2 getPosition() {
        return this.position;
    }
}

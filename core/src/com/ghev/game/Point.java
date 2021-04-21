package com.ghev.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Point {

    private final Texture texture;
    private final GridPoint2 position;

    public Point(Texture texture) {
        this.texture = texture;
        this.position = new GridPoint2();

        randomizePosition();
    }
    public void draw( Batch batch){
        batch.draw(texture,position.x,position.y);
    }

    public void randomizePosition() {

        int numberOfXPositions =
                Gdx.graphics.getWidth() / texture.getWidth();
        int numberOfYPositions =
                Gdx.graphics.getHeight() / texture.getHeight();
        this.position.set(
                (int) (Math.random() * numberOfXPositions) * texture.getWidth(),
                (int) (Math.random() * numberOfYPositions) * texture.getHeight());
    }

    public GridPoint2 getPosition() {
        return this.position;
    }
}
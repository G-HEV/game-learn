package com.ghev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameApp extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture playground;
	private Texture pointImg;
	private boolean gameOver;

	private Point point;
	private Snake snake;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	@Override
	public void create () {

		pointImg = new Texture("star.jpg");
		playground= new Texture("podzial_na_kwadraty.png");
		snakeImg = new Texture("snake.png");
		batch = new SpriteBatch();
		snake =  new Snake(snakeImg);
		point = new Point(pointImg);
		gameOver = snake.isCollision();


	}


	@Override
	public void render () {
		if(!snake.isCollision()) {
			gameOver= false;
			snake.move(Gdx.graphics.getDeltaTime() / 2);

			if (snake.isFoundPoint(point.getPosition())) {
				snake.addSegment();
				point.randomizePosition();
			}
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			batch.draw(playground, 0, 0);
			point.draw(batch);
			snake.draw(batch);

			batch.end();
		}else{
			gameOver = true;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.R) || (Gdx.input.isKeyPressed(Input.Keys.R))){
			restart();
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		playground.dispose();
		snakeImg.dispose();
		pointImg.dispose();

	}

	public void restart(){
		snake = new Snake(snakeImg);
		point.randomizePosition();

	}

}

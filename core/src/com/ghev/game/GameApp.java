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

	private Snake snake;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;


	@Override
	public void create () {
		playground= new Texture("podzial_na_kwadraty.png");
		snakeImg = new Texture("snake.png");
		batch = new SpriteBatch();
		snake =  new Snake(snakeImg);



	}


	@Override
	public void render () {
		snake.move(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();

		batch.draw(playground,0,0);
		snake.draw(batch);

		batch.end();


	}

	@Override
	public void dispose() {
		batch.dispose();
		playground.dispose();
		snakeImg.dispose();

	}
}
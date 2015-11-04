package com.rajatgang.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MyGame extends ApplicationAdapter implements ApplicationListener {

	static MyGame _game = null;
	SpriteBatch batch;
	Texture texture;
	Texture texture_health_border;
	Texture health_inner;
	Texture texture_hero;
	Sprite sprite;
	Sprite sprite_health_border;
	Sprite sprite_health;
	Sprite sprite_hero;
	float x = 150;
	float b = 100;
	public float game_width, game_height; /* height and width of frame */
	Enemy enemy;
	Bullet bullet;
	monster monst = null; // Monster class object
	Bulletpool bullets = null;
	boolean alive = true;
	public Rectangle playerBounds = new Rectangle();
	float width = 200, height = 200;
	float health_border_width = 200, health_border_height = 30;
	float health_border_x = 8, health_border_y = 440;
	float health_inner_width = 175, health_inner_height = 5;
	float health_inner_x = 30, health_inner_y = 453;

	public MyGame() {
		_game = this;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		texture = new Texture("space_background1.jpg");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture_health_border = new Texture("healthborder.png");
		texture_health_border.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		health_inner = new Texture("healthtexture.png");
		health_inner.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite_health_border = new Sprite(texture_health_border);
		sprite_health = new Sprite(health_inner);
		texture_hero = new Texture("heroism.png");
		texture_hero.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite_hero = new Sprite(texture_hero);
		sprite_hero.setSize(width, height);

		playerBounds.x = 10;
		playerBounds.y = x;
		playerBounds.width = width;
		playerBounds.height = height;

		Gdx.input.setInputProcessor(new MyInputProcessor(this));

		game_width = Gdx.graphics.getWidth();
		game_height = Gdx.graphics.getHeight();
		System.out.println("Width = " + game_width + "game height = "
				+ game_height);

		monst = new monster();
		bullets = new Bulletpool();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationAdapter#render()
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sprite.setPosition(b, 5);
		sprite_hero.setPosition(5, x);
		playerBounds.setPosition(5, x);
		sprite_health_border.setPosition(health_border_x, health_border_y);
		sprite_health_border.setSize(health_border_width, health_border_height);
		sprite_health.setPosition(health_inner_x, health_inner_y);
		sprite_health.setSize(health_inner_width, health_inner_height);
		batch.begin();
		

		if (alive == true) {

			sprite.draw(batch);

			bullets.bulletfire();
			sprite_hero.draw(batch);
			sprite_health_border.draw(batch);
			sprite_health.draw(batch);
			monst.update();
		}
		else{
			
		}
		
		

		batch.end();

		updatemotion();
		scrollbackground();

		checkCollision();
	}

	private void checkCollision() {

		Array<Enemy> pool = monst.enemypool;
		for (Enemy e : pool) {
			if (e.bound.overlaps(playerBounds)) {
				health_inner_width -= 1;
				if (health_inner_width == 0) {
					alive = false;
				}
			}
		}

	}

	/* Infinite Scroll of background image */
	int scrollbackground() {
		b = b - 0.5f;
		return 0;
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		texture_hero.dispose();
		// background.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	/*-------Moving Hero Space Ship UP and  DOWN---------------*/
	boolean upMove;
	boolean downMove;

	int updatemotion() {
		if (upMove) {
			if (x + sprite_hero.getHeight() < Gdx.graphics.getHeight())
				x += 150 * Gdx.graphics.getDeltaTime();

		}

		if (downMove) {
			if (x > 0)
				x -= 150 * Gdx.graphics.getDeltaTime();

		}
		return 0;
	}

	public int setmoveupward(boolean t) {
		if (downMove && t)
			downMove = false;
		upMove = t;

		return 0;
	}

	public int setmovedownward(boolean t) {
		if (upMove && t)
			upMove = false;
		downMove = t;
		return 0;
	}

	public static MyGame getInstance() {
		return _game;
	}
}

/*---------- INPUT PROCESSOR for taking input or key press*/
class MyInputProcessor implements InputProcessor {
	MyGame game;

	public MyInputProcessor(MyGame g) {
		game = g;
	}

	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.UP:
			game.setmoveupward(true);
			break;
		case Keys.DOWN:
			game.setmovedownward(true);
			break;
		}
		return true;
	}

	/*------------Acting on key Press-----------*/
	public boolean keyUp(int keycode) {

		switch (keycode) {
		case Keys.UP:
			game.setmoveupward(false);
			break;
		case Keys.DOWN:
			game.setmovedownward(false);
			break;
		}
		return true;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	public boolean mouseMoved(int x, int y) {
		return false;
	}

	public boolean scrolled(int amount) {
		return false;
	}

}

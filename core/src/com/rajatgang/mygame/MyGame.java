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
	Texture texture_hero;
	Sprite sprite;
	Sprite sprite_hero;
	float x = 150;
	float b = 100;
	public float game_width, game_height; /* height and width of frame */
	Enemy enemy;
	Bullet bullet;
	monster monst = null; // Monster class object
	Bulletpool bullets = null;
	
	Rectangle playerBounds = new Rectangle();
	float width = 200, height = 150;
	
	public MyGame() {
		_game = this;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		texture = new Texture("space_background1.jpg");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
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
		System.out.println("Width = " +game_width+"game height = "+game_height);
		
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
		
		batch.begin();
		sprite.draw(batch);
		sprite_hero.draw(batch);
		
		monst.update();
		bullets.bulletfire();

		batch.end();

		updatemotion();
		scrollbackground();

		checkCollision();
	}

	private void checkCollision() {
		
		Array<Enemy> pool = monst.enemypool;
		for(Enemy e : pool){
			if(e.bound.overlaps(playerBounds)){
				System.out.println("LOL");
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

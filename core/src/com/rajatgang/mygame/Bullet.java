package com.rajatgang.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Bullet {
	MyGame game = MyGame.getInstance();
	public Rectangle Bulletbound = new Rectangle();
	Texture bullet_texture;
	Sprite bullet_sprite;
	// float x;
	// float bullet_x_axis = (game.height) / 2, bullet_y_axis = game.x;
	float bullet_height = 10, bullet_width = 2.5f;

	Enemy enemy = null;
	monster monst = null;

	Array<Bullet> bulletstock;

	public Bullet(Array<Bullet> bulletpool) {
		bulletstock = bulletpool;
		create();
	}

	public void create() {
		bullet_texture = new Texture("bullet.png");
		bullet_texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bullet_sprite = new Sprite(bullet_texture);
		bullet_sprite.setSize(bullet_height, bullet_width);

		Bulletbound.x = MathUtils.random(game.game_width,
				game.game_width * 1.4f);
		Bulletbound.y = MathUtils.random(0, game.game_height);

		Bulletbound.x = game.playerBounds.x + game.playerBounds.width
				- MathUtils.random(0, game.playerBounds.width / 2);
		Bulletbound.y = game.playerBounds.y + game.playerBounds.height * 0.25f;

		Bulletbound.width = bullet_width;
		Bulletbound.height = bullet_height;

		monst = game.getMonster();

		for (Bullet b : bulletstock) {
			if (b.Bulletbound.overlaps(Bulletbound)) {
				Bulletbound.x += bullet_width;
				// bound.y += bullet_height / 10.5f;
			}
		}

	}

	public void render() {
		bullet_sprite.setPosition(Bulletbound.x, Bulletbound.y);
		//Bulletbound.setPosition(Bulletbound.x, Bulletbound.y);
		bullet_sprite.draw(game.batch);
		scrollbullet();

		if (Bulletbound.x + bullet_sprite.getHeight() > game.game_width) {
			Bulletbound.x = game.playerBounds.x + game.playerBounds.width
					- MathUtils.random(0, game.playerBounds.width / 2);
			Bulletbound.y = game.playerBounds.y + game.playerBounds.height
					* 0.25f;

		}
		gunshot();

	}

	private void gunshot() {

		Array<Enemy> pool = monst.enemypool;
		for (Enemy e : pool) {
			System.out.println("out gunshot"+e.bound.x+" ,"+Bulletbound.x);
			if (e.bound.overlaps(Bulletbound)) {
				System.out.println("in gunshot");
				e.enemylife = false;
			}
		}
	}

	void scrollbullet() {
		Bulletbound.x += 3.5f;
	}
}
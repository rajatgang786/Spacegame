package com.rajatgang.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Bullet {
	MyGame game = MyGame.getInstance();
	Texture bullet_texture;
	Sprite bullet_sprite;
	float x, y;
	float bullet_x_axis = (game.height) / 2;
	float bullet_y_axis = game.x;
	float bullet_height = 10, bullet_width = 2.5f;
	Rectangle rectangle = new Rectangle();
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
		
		rectangle.x = x = MathUtils.random(game.game_width,game.game_width*1.4f);
		rectangle.y = y = MathUtils.random(0, game.game_height);
		rectangle.width = bullet_width;
		rectangle.height = bullet_height;
		

		for (Bullet b : bulletstock) {
			System.out.println("line 39");
			if (b.rectangle.overlaps(b.rectangle)) {
				System.out.println("41");
				rectangle.x = x += bullet_width;
				rectangle.y = y += bullet_height * 1.5f;
				System.out.println("44");
			}
		}
	}

	public void render() {
		bullet_sprite.setPosition(bullet_x_axis + 105, bullet_y_axis + 37);
		bullet_sprite.draw(game.batch);
		scrollbullet(); 
		
		if (bullet_y_axis + bullet_sprite.getHeight() > game.game_width) {
			bullet_y_axis += 800;
		}
		
		rectangle.x = x;
		rectangle.y = y;
	}

	void scrollbullet() {

		bullet_x_axis += .5f;
	}
}
package com.rajatgang.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Bullet {
	MyGame game = MyGame.getInstance();
	Rectangle rectangle = new Rectangle();
	Texture bullet_texture;
	Sprite bullet_sprite;
	//float x;
	float width = 5 , height = 5;
	float bullet_x_axis = (game.height) / 2,bullet_y_axis = game.x;
	float bullet_height = 10, bullet_width = 2.5f;
	
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
				

		for (Bullet b : bulletstock) {
			System.out.println("i m sexy");
			if (b.rectangle.overlaps(rectangle)) {
				System.out.println("i m");
				rectangle.x = bullet_x_axis += bullet_width;
				rectangle.y = bullet_y_axis  += bullet_height / 10.5f;
			}
		}
	}

	public void render() {
		bullet_sprite.setPosition(bullet_x_axis + 106, bullet_y_axis + 40);
		bullet_sprite.draw(game.batch);
		scrollbullet(); 
		
		if (bullet_x_axis + bullet_sprite.getHeight() > game.game_width) {
			bullet_x_axis -= 550;
		}
	}

	void scrollbullet() {
		bullet_x_axis += 1.5f;
	}
}
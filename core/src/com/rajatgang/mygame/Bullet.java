package com.rajatgang.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Bullet {
	MyGame game = MyGame.getInstance();
	Rectangle bound = new Rectangle();
	Texture bullet_texture;
	Sprite bullet_sprite;
	//float x;
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
				
		bound.x = MathUtils.random(game.game_width,game.game_width*1.4f);
		bound.y = MathUtils.random(0, game.game_height);
		
		bound.x = game.playerBounds.x + game.playerBounds.width - MathUtils.random(0 ,game.playerBounds.width/2);
		bound.y = game.playerBounds.y + game.playerBounds.height*0.25f;

		bound.width = bullet_width;
		bound.height = bullet_height;
		
		for (Bullet b : bulletstock) {
			System.out.println("i m sexy");
			if (b.bound.overlaps(bound)) {
				System.out.println("i m");
				bound.x += bullet_width;
				//bound.y += bullet_height / 10.5f;
			}
		}
	}

	public void render() {
		bullet_sprite.setPosition(bound.x, bound.y);
		bullet_sprite.draw(game.batch);
		scrollbullet(); 
		
		if (bound.x + bullet_sprite.getHeight() > game.game_width) {
			bound.x = game.playerBounds.x + game.playerBounds.width - MathUtils.random(0 ,game.playerBounds.width/2);
			bound.y = game.playerBounds.y + game.playerBounds.height*0.25f;

		}
	}

	void scrollbullet() {
		bound.x += 10.5f;
	}
}
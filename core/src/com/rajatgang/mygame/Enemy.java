package com.rajatgang.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Enemy {
	MyGame game = MyGame.getInstance();
	public boolean enemylife = true;
	Texture texture;
	Sprite enemy_sprite;
	float x, y;
	float width = 50, height = 50;
	
	public Rectangle bound = new Rectangle();

	Array<Enemy> pool;

	public Enemy(Array<Enemy> enemypool) {
		pool = enemypool;
		create();
	}
	

	public void create() {
		texture = new Texture("enemy.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		enemy_sprite = new Sprite(texture);
		enemy_sprite.setSize(width, height);

		bound.x = x = MathUtils.random(game.game_width, game.game_width * 1.4f);
		bound.y = y = MathUtils.random(0, game.game_height);
		bound.width = width;
		bound.height = height;
		
		

		for (Enemy e : pool) {
			if (e.bound.overlaps(bound)) {
				bound.x = x += width * 6;
				bound.y = y += height;
			}
		}

	}

	public void render() {
		enemy_sprite.setPosition(x, y);
		//bound.setPosition(x,y);
		if(enemylife == true){
			enemy_sprite.draw(game.batch);
		}
		if (x + enemy_sprite.getWidth() < 0) {
			x += 700;
		}

		enemy_moving();

		bound.x = x;
		bound.y = y;
	}

	int enemy_moving() {
		x = x - 1.0f;
		return 0;
	}


}

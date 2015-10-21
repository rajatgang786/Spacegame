package com.rajatgang.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Enemy {
	MyGame game = MyGame.getInstance();
	Texture texture;
	Sprite enemy_sprite;
	float x , y ;
	

	public	Enemy(){
			create();
		}
	
	public void create() {	
		texture = new Texture("download.jpg");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		enemy_sprite = new Sprite(texture);
		x = MathUtils.random(game.game_width,game.game_width*2);
		y = MathUtils.random(0, game.game_height);
	}


	public int render() {
		enemy_sprite.setPosition(x, y);
		enemy_sprite.draw(game.batch);
		
		if(x + enemy_sprite.getWidth() < 0){
			x +=600; 
		}
		
		enemy_moving();
		
		return 0;
	}

	int enemy_moving() {
		x = x - 0.5f;
		return 0;
	}

}

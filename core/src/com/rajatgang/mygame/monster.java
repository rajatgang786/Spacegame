package com.rajatgang.mygame;

import com.badlogic.gdx.utils.Array;

public class monster {
	
	Array<Enemy> enemypool = new Array<Enemy>();
	
	MyGame game = MyGame.getInstance();
	
	
	public monster(){
		for(int i=0;i<10;i++)
		{
		enemypool.add(new Enemy());
		}
	}

	public void update() {
		for(Enemy e: enemypool)
		{
			e.render();
		}
		
	}
}

package com.rajatgang.mygame;

import com.badlogic.gdx.utils.Array;

public class monster {

	public Array<Enemy> enemypool = new Array<Enemy>(); // object of enemy

	MyGame game = MyGame.getInstance();

	public monster() { // Array of monster
		for (int i = 0; i <= 15; i++) {
			enemypool.add(new Enemy(enemypool));
		}
	}

	public void update() {
		for (Enemy e : enemypool) {
			e.render();
		}

	}
}

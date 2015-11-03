package com.rajatgang.mygame;

import com.badlogic.gdx.utils.Array;

public class Bulletpool {
	public Array<Bullet> bulletpool = new Array<Bullet>();
	MyGame game = MyGame.getInstance();

	public Bulletpool() {
		for (int i = 0; i <=15; i++) {
			bulletpool.add(new Bullet(bulletpool));
		}
	}

	public void bulletfire() {
		for (Bullet b : bulletpool) {
			b.render();
		}
	}
}

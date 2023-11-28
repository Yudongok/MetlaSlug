package project;

import java.util.Random;

public class Enemy {
	// Enemy Point
	protected int x, y;
	protected int life;
	protected int damage;
	protected int speed;

	public Enemy(int a, int b, int c) {
		Random random = new Random();

		this.life = a;
		this.damage = b;
		this.speed = c;
		x = random.nextInt(920);
	}

	public void trace(int characterX, int characterY) {
		if (characterX > this.x && characterY > this.y) {
			this.x += 1;
			this.y += 1;
		}
		if (characterX < this.x && characterY > this.y) {
			this.x -= 1;
			this.y += 1;
		}
		if (characterX > this.x && characterY < this.y) {
			this.x += 1;
			this.y -= 1;
		}
		if (characterX < this.x && characterY < this.y) {
			this.x -= 1;
			this.y -= 1;
		}
	}

	public void attacked(int characterDamage) {
		if (characterDamage > this.life) {
			this.life = 0;
		}
		this.life -= characterDamage;
	}

	public void attack() {
		// will be make this code
	}

	public int getEnemy_X() {
		return this.x;
	}
}

class Gunner extends Enemy {
	public Gunner() {
		// life, damage, speed
		super(80, 10, 20);
	}

}

class Boomber extends Enemy {
	public Boomber() {
		super(60, 20, 10);
	}
}

class Helicopter extends Enemy {
	public Helicopter() {
		super(500, 80, 40);
	}
}
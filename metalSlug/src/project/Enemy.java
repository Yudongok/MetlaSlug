package project;

import java.util.Random;

public class Enemy {
	// Enemy Point
	protected int x, y;
	protected int life;
	protected int damage;
	protected int speed;
	protected boolean death = false;
	private boolean attack = false;
	// 0 is Right
	private int direction = 0;
	private int attackRange_R = x + 40 + 100;
	private int attackRange_L = x + 40 - 100;

	public Enemy(int a, int b, int c) {
		Random random = new Random();

		this.life = a;
		this.damage = b;
		this.speed = c;
		this.x = random.nextInt(920);
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

	public boolean isAttack(int hero_X) {
		// hero is Left
		if (hero_X + 80 <= this.attackRange_L) {
			attack = true;
			this.direction = 1;
			return this.attack;
		}
		// hero is Right
		else if (this.attackRange_R <= hero_X) {
			attack = true;
			this.direction = 0;
			return this.attack;
		} else
			return attack;
	}

	public int getEnemy_Direction() {
		return this.direction;
	}

	public void setEnemy_Direction(int direction) {
		this.direction = direction;
	}

	public int getEnemy_X() {
		return this.x;
	}

	public void setEnemy_X(int x) {
		this.x = x;
	}

	public int getEnemy_Y() {
		return this.y;
	}

	public void setEnemy_Y(int y) {
		this.y = y;
	}

	public int getEnemy_Life() {
		return this.life;
	}

	public void setEnemy_Life(int life) {
		this.life = life;
	}

	public int getEnemy_Damage() {
		return this.damage;
	}

	public boolean isDead() {
		if (this.life <= 0) {
			this.death = true;
		}
		return this.death;
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
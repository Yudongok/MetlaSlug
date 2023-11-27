package project;

public class Character {
	// ĳ���� �̵� ��ǥ
	protected int x, y;
	protected int life;
	protected int damage;
	protected int speed;

	public Character(int a, int b, int c) {
		this.life = a;
		this.damage = b;
		this.speed = c;
	}

	public void setHeroMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getHero_X() {
		return this.x;
	}

	public int getHero_Y() {
		return this.y;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getLife() {
		return this.life;
	}

	public int getDamage() {
		return this.damage;
	}

	public void attack() {

	}
}

//Marco Life up(life, damage, speed)
class Marco extends Character {
	public Marco() {
		super(120, 20, 5);
	}

}

//Tarma Damage up
class Tarma extends Character {
	public Tarma() {
		super(100, 30, 5);
	}
}

//Eri Speed up
class Eri extends Character {
	public Eri() {
		super(100, 20, 8);
	}
}

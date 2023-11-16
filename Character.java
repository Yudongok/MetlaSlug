package project;

public class Character {
	//캐릭터 이동 좌표
	protected int x, y;
	protected int life;
	protected int damage;
	protected int speed;
	
	public Character(int a, int b, int c) {
		this.life = a;
		this.damage = b;
		this.speed = c;
	}
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void attack() {
		
	}
}

class Marco extends Character{
	public Marco() {
		super(120, 20, 30);
	}

}

class Tarma extends Character{
	public Tarma() {
		super(100, 30, 30);
	}
}

class Eri extends Character{
	public Eri() {
		super(100, 20, 40);
	}
}

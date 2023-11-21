package project;

public class Enemy {
	//적 이동 좌표
	protected int x, y;
	protected int life;
	protected int damage;
	protected int speed;
		
	public Enemy(int a, int b, int c) {
		this.life = a;
		this.damage = b;
		this.speed = c;
	}
	
	public void move(int characterX, int characterY) {
		if(characterX > this.x && characterY > this.y) {
			this.x += 1;
			this.y += 1;
		}
		if(characterX < this.x && characterY > this.y) {
			this.x -= 1;
			this.y += 1;
		}
		if(characterX > this.x && characterY < this.y) {
			this.x += 1;
			this.y -= 1;
		}
		if(characterX < this.x && characterY < this.y) {
			this.x -= 1;
			this.y -= 1;
		}
	}
		
	public void attack() {
			
	}

}

class Gunner extends Enemy{
	public Gunner() {
		super(80, 10, 20);
	}
	public void attack(int characterDamage) {
		if(characterDamage > this.life) {
			//현재 캐릭터 죽음
			this.life = 0;
		}
		this.life -= characterDamage;
	}
}

class Boomber extends Enemy{
	public Boomber() {
		super(60, 20, 10);
	}
	public void attack(int characterDamage) {
		if(characterDamage > this.life) {
			//현재 캐릭터 죽음
			this.life = 0;
		}
		this.life -= characterDamage;
	}
}

class Helicopter extends Enemy{
	public Helicopter() {
		super(500, 80, 40);
	}
	public void attack(int characterDamage) {
		if(characterDamage > this.life) {
			//현재 캐릭터 죽음
			this.life = 0;
		}
		this.life -= characterDamage;
	}
}
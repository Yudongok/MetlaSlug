package project;

public class Bullet {
	public int x;
	public int y;
	public int bulletDirection;

	public Bullet(int x, int y, int bulletDirection) {
		this.x = x;
		this.y = y;
		this.bulletDirection = bulletDirection;
	}

	public int getBullet_Y() {
		return this.y;
	}

}

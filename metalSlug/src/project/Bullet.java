package project;

public class Bullet {
	public int x;
	public final int y;
	public boolean bulletDirection;

	public Bullet(int x, int y, boolean bulletDirection) {
		this.x = x;
		this.y = y;
		this.bulletDirection = bulletDirection;
	}
	
	public int getBullet_Y() {
		return this.y;
	}
}

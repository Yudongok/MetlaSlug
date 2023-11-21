package project;

public class item {
	protected int bullet;
	protected int damage;
	protected int heal;
	
	//머신건, 로켓 아이템 생성자
	public item(int x, int y) {
		this.bullet = x;
		this.damage = y;
	}
	
	//힐팩 생성자
	public item(int a) {
		this.heal = a;
	}

}

class MachineGun extends item{
	public MachineGun() {
		super(100, 40);
	}
}

class Rocket extends item{
	public Rocket() {
		super(50, 60);
	}
}

class HealKit extends item{
	public HealKit() {
		super(50);
	}
}

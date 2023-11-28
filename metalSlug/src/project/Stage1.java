package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Stage1_Panel extends JPanel {
	BufferedImage heroImage = null;
	SelectPanel selectPanel = new SelectPanel(null, null, null);
	private Character hero;
	// make player on left floor
	private final int START_X = 100;
	private final int START_Y = 500;
	private int heroImage_X, heroImage_Y;
	private int selectedCharacter = 0;
	private int jumpHeight = 80;
	private int gravity = 5;
	private int bulletSpeed = 20;
	private int speed;
	private int life;
	private int damage;
	private int bullet_Y;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	// if pressed "C" then bulletFired == true
	private boolean bulletFired = false;
	// if direction == true (Right)
	private boolean direction = true;
	private Timer jumpTimer;
	private Timer moveTimer_R;
	private Timer moveTimer_L;
	private Timer bulletTimer;

	public void setNum(int num) {
		this.selectedCharacter = num;
		updateCharacter();
	}

	// selectCharacter
	public void updateCharacter() {
		if (selectedCharacter == 0) {
			hero = new Marco();
			try {
				heroImage = ImageIO.read(new File("images/Marco_.jpg"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		} else if (selectedCharacter == 1) {
			hero = new Tarma();
			try {
				heroImage = ImageIO.read(new File("images/Tarma_.jpg"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		} else if (selectedCharacter == 2) {
			hero = new Eri();
			try {
				heroImage = ImageIO.read(new File("images/Eri_.png"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		}
		speed = hero.getSpeed();
		damage = hero.getDamage();
		life = hero.getLife();
	}

	public Stage1_Panel() {
		// setNum(1);

		heroImage_X = START_X;
		heroImage_Y = START_Y;
		jumpTimer = new Timer(20, new ActionListener() {
			int jumpCount = 0;
			boolean isJumping = false;

			public void actionPerformed(ActionEvent e) {
				if (!isJumping) {
					isJumping = true;
					jumpCount = 0;
				}

				if (jumpCount < jumpHeight) {
					heroImage_Y -= gravity;
					jumpCount += gravity;
					hero.setHeroMove(heroImage_X, heroImage_Y);
				} else {
					if (heroImage_Y != START_Y) {
						heroImage_Y += gravity;
						hero.setHeroMove(heroImage_X, heroImage_Y);
					}

					else {
						jumpTimer.stop();
						isJumping = false;
						hero.setHeroMove(heroImage_X, heroImage_Y);
					}

				}
				repaint();
			}

		});
		moveTimer_R = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroImage_X == 900) {
					moveTimer_R.stop();
					hero.setHeroMove(heroImage_X, heroImage_Y);
				} else {
					heroImage_X += speed;
					hero.setHeroMove(heroImage_X, heroImage_Y);
				}
				repaint();
			}
		});
		moveTimer_L = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroImage_X == 0) {
					moveTimer_L.stop();
					hero.setHeroMove(heroImage_X, heroImage_Y);
				} else {
					heroImage_X -= speed;
					hero.setHeroMove(heroImage_X, heroImage_Y);
				}
				repaint();
			}
		});

		bulletTimer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveBullets();
				repaint();
			}
		});

		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch (keycode) {
				case KeyEvent.VK_UP:
					// aim up
					break;
				case KeyEvent.VK_DOWN:
					// player down
					break;
				case KeyEvent.VK_LEFT:
					// move left
					direction = false;
					move_L();
					break;
				case KeyEvent.VK_RIGHT:
					// move right
					direction = true;
					move_R();
					break;
				case KeyEvent.VK_Z:
					// jump
					jump();
					break;
				case KeyEvent.VK_C:
					bullet_Y = heroImage_Y + 40;
					fireBullet();
					break;
				}
				repaint();
			}

			public void keyReleased(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch (keycode) {
				case KeyEvent.VK_UP:
					// aim up
					break;
				case KeyEvent.VK_DOWN:
					// player down
					break;
				case KeyEvent.VK_LEFT:
					// move left
					moveTimer_L.stop();
					break;
				case KeyEvent.VK_RIGHT:
					// move right
					moveTimer_R.stop();
					break;
				case KeyEvent.VK_Z:
					// jump
					break;
				case KeyEvent.VK_C:
					break;
				}
				repaint();

			}

			public void keyTyped(KeyEvent arg0) {

			}

			public void jump() {
				// if isJumping == true then don't more jump
				if (heroImage_Y == START_Y) {
					jumpTimer.start();
				}
			}

			public void move_R() {
				moveTimer_R.start();
			}

			public void move_L() {
				moveTimer_L.start();
			}

			public void fireBullet() {
				bulletFired = true;
				Bullet bullet = new Bullet(direction ? heroImage_X + 80 : heroImage_X, bullet_Y, direction);
				bullets.add(bullet);
				bulletTimer.start();
			}
		});

		this.requestFocus();
		setFocusable(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (heroImage != null) {
			g.drawImage(heroImage, heroImage_X, heroImage_Y, 80, 100, this);
			g.setColor(Color.BLACK);
			g.fillRect(0, START_Y + 100, 1000, 20);
		}

		if (bulletFired) {
			for (Bullet bullet : bullets) {
				g.setColor(Color.BLACK);
				g.drawOval(bullet.x, bullet.getBullet_Y(), 10, 10);
				g.setColor(Color.YELLOW);
				g.fillOval(bullet.x, bullet.getBullet_Y(), 10, 10);

			}

		}
	}

	private void moveBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.x >= 0 && bullet.x <= getWidth()) {
				if (bullet.bulletDirection) {
					bullet.x += bulletSpeed;
				} else {
					bullet.x -= bulletSpeed;
				}
				bullets.set(i, bullet);
			} else { // ��硫댁�� 踰��대�� 珥����� 由ъ�ㅽ�몄���� ��嫄�
				bullets.remove(i);
				i--; // 由ъ�ㅽ�� ����媛� ������ 諛�由щ��濡� �몃�깆�� 蹂댁��
			}
		}
	}
}

public class Stage1 extends JFrame {
	// static�� �ъ�⑺��硫� 媛�泥대�� ���깊��吏� ��怨��� ������ ��洹� 媛���
	static Stage1_Panel stagePanel;

	public Stage1() {
		setSize(1000, 700);
		setTitle("Stage1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stagePanel = new Stage1_Panel();
		add(stagePanel);
		setVisible(true);

	}

	public static void main(String[] args) {
		Stage1 stage1 = new Stage1();
	}

}

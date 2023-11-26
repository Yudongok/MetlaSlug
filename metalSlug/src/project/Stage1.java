package project;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Stage1_Panel extends JPanel {
	BufferedImage heroImage = null;
	SelectPanel selectPanel = new SelectPanel(null, null, null);
	// make player on left floor
	private final int START_X = 100;
	private final int START_Y = 500;
	private int heroImage_x, heroImage_y;
	private int selectedCharacter = 0;
	private int jumpHeight = 50;
	private int gravity = 2;
	private Timer jumpTimer;

	public void setNum(int num) {
		this.selectedCharacter = num;
		updateCharacter();
	}

	public void updateCharacter() {
		if (selectedCharacter == 0) {
			Marco hero = new Marco();
			try {
				heroImage = ImageIO.read(new File("images/Marco_.jpg"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		} else if (selectedCharacter == 1) {
			Tarma hero = new Tarma();
			try {
				heroImage = ImageIO.read(new File("images/Tarma_.jpg"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		} else if (selectedCharacter == 2) {
			Eri hero = new Eri();
			try {
				heroImage = ImageIO.read(new File("images/Eri_.png"));
			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		}
	}

	public Stage1_Panel() {
		heroImage_x = START_X;
		heroImage_y = START_Y;
		jumpTimer = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroImage_y > START_Y - jumpHeight && jumpTimer.isRunning()) {
					heroImage_y -= gravity;
				} else {
					jumpTimer.stop();
					if (heroImage_y < START_Y) {
						heroImage_y += gravity;
					}
					if (heroImage_y > START_Y) {
						heroImage_y = START_Y;
					}
				}
				repaint();
			}

		});
		jumpTimer.start();
		addKeyListener(new KeyListener() {
			boolean jumpKeyPressed = false;

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
					heroImage_x -= 5;
					break;
				case KeyEvent.VK_RIGHT:
					// move right
					heroImage_x += 5;
					break;
				case KeyEvent.VK_Z:
					// jump
					jumpKeyPressed = true;
					break;
				case KeyEvent.VK_C:
					// shoot
					break;
				}
				repaint();
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_Z) {
					// 'z' 키 릴리즈시, 점프
					if (jumpKeyPressed) {
						jump();
						jumpKeyPressed = false;
					}
				}
			}

			public void keyTyped(KeyEvent arg0) {

			}

			public void jump() {
				if (heroImage_y == START_Y) {
					jumpTimer.stop();
					jumpTimer.start();
				}
			}

		});

		this.requestFocus();
		setFocusable(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (heroImage != null) {
			g.drawImage(heroImage, heroImage_x, heroImage_y, 80, 100, this);
		}
	}
}

public class Stage1 extends JFrame {
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

package project;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Stage1_Panel extends JPanel{
	BufferedImage heroImage = null;
	SelectPanel selectPanel = new SelectPanel(null, null, null);
	//바닥 왼쪽에 캐릭터 생성
	int heroImage_x = 100, heroImage_y = 600;
	
	public Stage1_Panel(){
		int num = selectPanel.getNum();
		if(num == 0) {
			Marco hero = new Marco();
			try {
				heroImage = ImageIO.read(new File("images/Marco_.jpg"));
			}catch(IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		}
		else if(num == 1) {
			Tarma hero = new Tarma();
			try {
				heroImage = ImageIO.read(new File("images/Tarma_.jpg"));
			}catch(IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		}
		else {
			Eri hero = new Eri();
			try {
				heroImage = ImageIO.read(new File("images/Eri_.png"));
			}catch(IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
		}
		
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch(keycode) {
				case KeyEvent.VK_UP:
					//위쪽 조준하는거 구현
					break;
				case KeyEvent.VK_DOWN:
					//웅크리는 이미지 삽입
					break;
				case KeyEvent.VK_LEFT:
					heroImage_x -= 5;
					//오른쪽으로 움직이는 이미지 삽입
					break;
				case KeyEvent.VK_RIGHT:
					heroImage_x += 5;
					//왼쪽으로 움직이는 이미지 삽입
					break;
				case KeyEvent.VK_Z:
					heroImage_y -= 20;
					//타이머를 이용하여 점프한 후 다시 내려오는거 구현
					break;
				case KeyEvent.VK_C:
					//총알 쏘는거 구현
					break;
				}
				repaint();
			}
			
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent arg0) {
				
			}
		
		});
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(heroImage != null) {
			g.drawImage(heroImage, heroImage_x, heroImage_y, 80, 100, this);
		}
	}
}


public class Stage1 extends JFrame{
	public Stage1() {
		setSize(1000, 700);
		setTitle("Stage1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(new Stage1_Panel());
	}
	public static void main(String[] args) {
		Stage1 stage1 = new Stage1();
	}
	
}

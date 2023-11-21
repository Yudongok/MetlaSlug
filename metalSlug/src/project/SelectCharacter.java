package project;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SelectPanel extends JPanel implements ActionListener{
	private JButton btn1, btn2, btn3;
	private BufferedImage img_Marco, img_Tarma, img_Eri;
	private int num = 0;
	
	public SelectPanel(BufferedImage img1, BufferedImage img2, BufferedImage img3) {
		this.img_Marco = img1;
		this.img_Tarma = img2;
		this.img_Eri = img3;
		setLayout(null);
		btn1 = new JButton("MARCO");
		btn2 = new JButton("TAMA");
		btn3 = new JButton("ERI");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		btn1.setBounds(150, 600, 80, 30);
		btn2.setBounds(470, 600, 80, 30);
		btn3.setBounds(770, 600, 80, 30);
		
		add(btn1);
		add(btn2);
		add(btn3);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(img_Marco != null && img_Tarma != null && img_Eri != null) {
			g.drawImage(img_Marco, 50, 0, 300, 700, null);
			g.drawImage(img_Tarma, 350, 0, 300, 700, null);
			g.drawImage(img_Eri, 650, 0, 300, 700, null);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) {
			Stage1 stage1 = new Stage1();
			this.num = 0;
		}
		if(e.getSource() == btn2) {
			Stage1 stage1 = new Stage1();
			this.num = 1;
		}
		if(e.getSource() == btn3) {
			Stage1 stage1 = new Stage1();
			this.num = 2;
		}
	}
	
	public int getNum() {
		return num;
	}
	
}

public class SelectCharacter extends JFrame{
	public SelectCharacter() {
		setSize(1000, 700);
		setTitle("캐릭터 선택화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BufferedImage img_Marco = null;
		BufferedImage img_Tarma = null;
		BufferedImage img_Eri = null;
		try {
			img_Marco = ImageIO.read(new File("images/Marco.png"));
			img_Tarma = ImageIO.read(new File("images/Tarma.png"));
			img_Eri = ImageIO.read(new File("images/Eri.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		add(new SelectPanel(img_Marco, img_Tarma, img_Eri));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SelectCharacter selectcharacter = new SelectCharacter();
	}
	
}
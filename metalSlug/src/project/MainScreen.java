package project;

import java.awt.Dimension;
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

class MyPanel extends JPanel implements ActionListener {
	private JButton startBtn;
	private JButton endBtn;
	private BufferedImage backgroundImage;

	public MyPanel(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
		setLayout(new FlowLayout());
		startBtn = new JButton("시작");
		endBtn = new JButton("종료");
		startBtn.addActionListener(this);
		endBtn.addActionListener(this);

		// 버튼의 크기를 조절하는 객체
		Dimension buttonSzie = new Dimension(80, 50);
		startBtn.setPreferredSize(buttonSzie);
		endBtn.setPreferredSize(buttonSzie);
		add(startBtn);
		add(endBtn);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
			System.out.println("스테이지 1로 넘어감");
			// 스테이지1 맵 만든 후 넘어가는 작업 추가
		}
		if (e.getSource() == endBtn) {
			System.exit(0);
		}
	}
}

public class MainScreen extends JFrame {
	public MainScreen() {
		setSize(500, 500);
		setTitle("메탈슬러그 메인화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BufferedImage backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("images/MetalSlugMainScreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		add(new MyPanel(backgroundImage));
		setVisible(true);
	}

	public static void main(String[] args) {
		MainScreen mainScreen = new MainScreen();
	}
}
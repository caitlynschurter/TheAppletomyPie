//Evil Lily's Password: JesusChrist123!
import java.awt.Dimension;
import javax.swing.JFrame;

public class TheAppleToMyPie {
	JFrame frame;
	static final int _width = 600;
	static final int _height = 800;
	
	GamePanel gamePanel;

	public static void main(String[] args) {
		new TheAppleToMyPie().setup();
	}

	TheAppleToMyPie() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}

	void setup() {
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.getContentPane().setPreferredSize(new Dimension(_width, _height));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(_width, _height);
		frame.pack();
		
		gamePanel.startGame();
		
		
	}

	
}

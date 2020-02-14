//Evil Lily's Password: ChristJesus321?
import java.awt.Dimension;
import javax.swing.JFrame;

public class TheAppleToMyPie {
	JFrame frame;
	static final int width = 600;
	static final int height = 800;
	
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
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.pack();
		
		gamePanel.startGame();	
	}
}

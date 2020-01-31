
//Evil Lily's Password: JesusChrist123!
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;

	Font titleFont;
	Font titleFontEnter;
	Font titleFontSpace;

	Font gameOverFont;
	Font gameOverFontScore;
	Font gameOverFontRestart;

	Basket basket;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);

		titleFont = new Font("French Script MT", Font.PLAIN, 72);
		titleFontEnter = new Font("Freestyle Script", Font.PLAIN, 48);
		titleFontSpace = new Font("Freestyle Script", Font.PLAIN, 48);

		gameOverFont = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontScore = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontRestart = new Font("Freestyle Script", Font.PLAIN, 48);

		basket = new Basket(250, 700, 50, 50);
	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {
	}

	void updateGameState() {
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		int sw = g.getFontMetrics().stringWidth("The Apple To My Pie");
		int sx = TheAppleToMyPie.width /2 - sw/2;
		g.drawString("The Apple To My Pie", sx, 150);
		g.setFont(titleFontEnter);
		sw = g.getFontMetrics().stringWidth("Press ENTER to Start!");
		sx = TheAppleToMyPie.width /2 - sw/2;
		g.drawString("Press ENTER to Start!", sx, 400);
		g.setFont(titleFontSpace);
		sw = g.getFontMetrics().stringWidth("Press SPACE for Instructions");
		sx = TheAppleToMyPie.width /2 - sw/2;
		g.drawString("Press SPACE for Instructions", sx, 500);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);

		basket.update();
		basket.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.setColor(Color.black);
		g.setFont(gameOverFont);
		g.drawString("Game Over", 220, 150);
		g.setFont(gameOverFontScore);
		g.drawString("You Completed " + " Recipes", 100, 200);
		g.setFont(gameOverFontRestart);
		g.drawString("Press ENTER to start again!", 100, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		switch (currentState) {
		case MENU_STATE:
			drawMenuState(g);
			break;
		case GAME_STATE:
			drawGameState(g);
			break;
		case END_STATE:
			drawEndState(g);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("hi");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == END_STATE) {
				basket = new Basket(250, 700, 50, 50);
				System.out.println("hello");
			}
			currentState++;
		}

		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			basket.down();
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			basket.up();
		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			basket.right();
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			basket.left();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("keyReleased");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("keyTyped");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		switch (currentState) {
		case MENU_STATE:
			updateMenuState();
			break;
		case GAME_STATE:
			updateGameState();
			break;
		case END_STATE:
			updateEndState();
			break;
		}

	}

}

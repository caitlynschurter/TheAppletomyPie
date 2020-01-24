
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
	Font gameOverFontKills;
	Font gameOverFontRestart;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		
		titleFont = new Font("Arial", Font.PLAIN, 48);
		titleFontEnter = new Font("Arial", Font.PLAIN, 36);
		titleFontSpace = new Font("Arial", Font.PLAIN, 36);
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
		g.fillRect(0, 0, TheAppleToMyPie._width, TheAppleToMyPie._height);
		g.setFont(titleFont);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, TheAppleToMyPie._width, TheAppleToMyPie._height);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, TheAppleToMyPie._width, TheAppleToMyPie._height);

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
		// System.out.println("keyPressed");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == END_STATE) {
				//use for later for Game Over
			}

			currentState++;
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
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

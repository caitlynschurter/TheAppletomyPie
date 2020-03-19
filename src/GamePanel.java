
//Evil Lily's Password: ChristJesus321?
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
	Timer timer;

	public static int counter = 60 * 60;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	boolean INST_STATE = false;
	int currentState = MENU_STATE;

	Font titleFont;
	Font titleFontEnter;
	Font titleFontSpace;

	Font instFontTOP;
	Font instFontTOP2;
	Font instFontMID;
	Font instFontMID2;
	Font instFontBOTTOM;
	Font instFontBOTTOM2;

	Font scoreFont;
	Font appleFont;
	Font flourFont;
	Font sugarFont;

	Font gameOverFont;
	Font gameOverFontScore;
	Font gameOverFontRestart;

	Basket basket;
	boolean rightPressed = false;
	boolean leftPressed = false;

	ObjectManager manager;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);

		titleFont = new Font("Freestyle Script", Font.PLAIN, 72);
		titleFontEnter = new Font("Freestyle Script", Font.PLAIN, 48);
		titleFontSpace = new Font("Freestyle Script", Font.PLAIN, 48);

		instFontTOP = new Font("Courier NEw", Font.BOLD, 24);
		instFontMID = new Font("Courier NEw", Font.BOLD, 24);
		instFontBOTTOM = new Font("Courier NEw", Font.BOLD, 24);
		instFontTOP2 = new Font("Courier NEw", Font.BOLD, 24);
		instFontMID2 = new Font("Courier NEw", Font.BOLD, 24);
		instFontBOTTOM2 = new Font("Courier NEw", Font.BOLD, 24);

		scoreFont = new Font("Courier New", Font.PLAIN, 28);

		gameOverFont = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontScore = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontRestart = new Font("Freestyle Script", Font.PLAIN, 48);

		basket = new Basket(250, 700, 50, 50);

		manager = new ObjectManager(basket);
	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {
	}

	void updateGameState() {
		basket.leftPressed = leftPressed;
		basket.rightPressed = rightPressed;
		manager.update();
		manager.manageIngredients();
		manager.purgeObjects();
		manager.checkCollision();

		counter--;

		if (counter <= 0) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		if (INST_STATE == true) {
			drawInstructions(g);
		}

		else {
			g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
			g.setColor(Color.WHITE);
			g.setFont(titleFont);
			int sw = g.getFontMetrics().stringWidth("The Apple To My Pie");
			int sx = TheAppleToMyPie.width / 2 - sw / 2;
			g.drawString("The Apple To My Pie", sx, 150);
			g.setFont(titleFontEnter);
			sw = g.getFontMetrics().stringWidth("Press ENTER to Start!");
			sx = TheAppleToMyPie.width / 2 - sw / 2;
			g.drawString("Press ENTER to Start!", sx, 400);
			g.setFont(titleFontSpace);
			sw = g.getFontMetrics().stringWidth("Press SPACE for Instructions");
			sx = TheAppleToMyPie.width / 2 - sw / 2;
			g.drawString("Press SPACE for Instructions", sx, 500);
		}
	}

	void drawInstructions(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.setColor(Color.BLACK);

		// Use the LEFT and RIGHT
		g.setFont(instFontTOP);
		int sw = g.getFontMetrics().stringWidth("Use the LEFT and RIGHT");
		int sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("Use the LEFT and RIGHT", sx, 100);

		// arrow keys to catch ingredients
		g.setFont(instFontTOP2);
		sw = g.getFontMetrics().stringWidth("arrow keys to catch ingredients");
		sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("arrow keys to catch ingredients", sx, 140);

		// Finish the recipes,
		g.setFont(instFontMID);
		sw = g.getFontMetrics().stringWidth("Finish the recipes,");
		sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("Finish the recipes,", sx, 200);

		// but don't overstock on ingredients
		g.setFont(instFontMID2);
		sw = g.getFontMetrics().stringWidth("but don't overstock on ingredients");
		sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("but don't overstock on ingredients", sx, 240);

		// You have 60 seconds, but you can
		g.setFont(instFontBOTTOM);
		sw = g.getFontMetrics().stringWidth("You have 60 seconds, but you can");
		sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("You have 60 seconds, but you can", sx, 300);

		// earn back time by completing recipes
		g.setFont(instFontBOTTOM2);
		sw = g.getFontMetrics().stringWidth("earn back time by completeing recipes");
		sx = TheAppleToMyPie.width / 2 - sw / 2;
		g.drawString("earn back time by completeing recipes", sx, 340);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);

		basket.update();
		manager.draw(g);
		drawScoreboard(g);
		drawTimer(g);
	}

	void drawScoreboard(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, TheAppleToMyPie.width, 60);

		g.setFont(scoreFont);
		g.setColor(Color.BLACK);
		int scoreWidth = g.getFontMetrics().stringWidth("Score: " + manager.score + " ");
		g.drawString("Score: " + manager.score, TheAppleToMyPie.width - scoreWidth, 40);

		g.setFont(appleFont);
		g.setColor(Color.BLACK);
		g.drawString("" + manager.applesCaught + "/3", 20, 40);

		g.setFont(flourFont);
		g.setColor(Color.BLACK);
		g.drawString("" + manager.flourCaught + "/1", 120, 40);

		g.setFont(sugarFont);
		g.setColor(Color.BLACK);
		g.drawString("" + manager.sugarCaught + "/2", 220, 40);
	}

	void drawTimer(Graphics g) {
		float timeLeft = (float) counter / 3600;
		float barWidth = timeLeft * TheAppleToMyPie.width;

		if (timeLeft >= 0.5) {
			g.setColor(Color.GREEN);
		}

		else if (timeLeft > 0.25) {
			g.setColor(Color.YELLOW);
		}

		else {
			if ((int) (timeLeft * 100 * 2.5) % 2 == 0) {
				g.setColor(Color.RED);
			}

			else {
				g.setColor(Color.LIGHT_GRAY);
			}
		}

		g.fillRect(0, 55, (int) barWidth, 5);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.setColor(Color.black);
		g.setFont(gameOverFont);
		g.drawString("Game Over", 220, 150);
		g.setFont(gameOverFontScore);
		g.drawString("You Completed " + manager.score + " Recipes", 100, 200);
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
		// System.out.println("keyPressed");
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {

			if (currentState == END_STATE) {
				basket = new Basket(250, 700, 50, 50);
				// System.out.println("hello");
				manager = new ObjectManager(basket);
			}
			currentState++;
		}

		if (currentState == GAME_STATE) {
			switch (code) {
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				rightPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				leftPressed = false;
				rightPressed = true;
				break;
			}
		}

		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}

		if (currentState == MENU_STATE) {
			if (code == KeyEvent.VK_SPACE) {
				if (INST_STATE == false) {
					INST_STATE = true;

					System.out.println("Show Instructions");
				}

				else if (INST_STATE == true) {
					INST_STATE = false;
					System.out.println("Hide Instructions");
				}

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("keyReleased");
		int code = e.getKeyCode();

		if (currentState == GAME_STATE) {
			switch (code) {
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// System.out.println("keyTyped");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("actionPerformed");
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

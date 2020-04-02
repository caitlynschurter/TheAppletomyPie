
//Evil Lily's Password: ChristJesus321?
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final static int GAME_TIME = 60 * 60;
	public static int counter = GAME_TIME;

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

	public static BufferedImage titleBackground;
	public static BufferedImage inst;
	public static BufferedImage background;
	public static BufferedImage end;
	public static BufferedImage apple;
	public static BufferedImage flour;
	public static BufferedImage sugar;
	public static BufferedImage emptyTin;
	public static BufferedImage pieTin;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);

		try {
			titleBackground = ImageIO.read(this.getClass().getResourceAsStream("LOAP Background - Title.png"));
			background = ImageIO.read(this.getClass().getResourceAsStream("LOAP Background - Game.png"));
			end = ImageIO.read(this.getClass().getResourceAsStream("LOAP Background - End.png"));
			inst = ImageIO.read(this.getClass().getResourceAsStream("LOAP Background - Inst.png"));
			apple = ImageIO.read(this.getClass().getResourceAsStream("LOAP-Apple.png"));
			flour = ImageIO.read(this.getClass().getResourceAsStream("LOAP-FLOUR.png"));
			sugar = ImageIO.read(this.getClass().getResourceAsStream("LOAP-Sugar.png"));
			emptyTin = ImageIO.read(this.getClass().getResourceAsStream("LOAP-EmptyTin.png"));
			pieTin = ImageIO.read(this.getClass().getResourceAsStream("LOAP-PieTin.png"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		titleFont = new Font("Freestyle Script", Font.PLAIN, 72);
		titleFontEnter = new Font("Freestyle Script", Font.PLAIN, 48);
		titleFontSpace = new Font("Freestyle Script", Font.PLAIN, 48);

		instFontTOP = new Font("Courier New", Font.BOLD, 24);
		instFontMID = new Font("Courier New", Font.BOLD, 24);
		instFontBOTTOM = new Font("Courier New", Font.BOLD, 24);
		instFontTOP2 = new Font("Courier New", Font.BOLD, 24);
		instFontMID2 = new Font("Courier New", Font.BOLD, 24);
		instFontBOTTOM2 = new Font("Courier New", Font.BOLD, 24);

		scoreFont = new Font("Courier New", Font.PLAIN, 28);

		gameOverFont = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontScore = new Font("Freestyle Script", Font.PLAIN, 60);
		gameOverFontRestart = new Font("Freestyle Script", Font.PLAIN, 48);

		basket = new Basket(250, 700, Basket.WIDTH, Basket.HEIGHT);

		manager = new ObjectManager(basket);
	}

	void startGame() {
		basket = new Basket(250, 700, Basket.WIDTH, Basket.HEIGHT);
		// System.out.println("hello");
		manager = new ObjectManager(basket);
		counter = GAME_TIME;
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
		// g.setColor(Color.BLUE);

		if (INST_STATE == true) {
			drawInstructions(g);
		}

		else {
			g.drawImage(titleBackground, 0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height, null);
			/*
			 * g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
			 * g.setColor(Color.WHITE); g.setFont(titleFont); int sw =
			 * g.getFontMetrics().stringWidth("The Apple To My Pie"); int sx =
			 * TheAppleToMyPie.width / 2 - sw / 2; g.drawString("The Apple To My Pie", sx,
			 * 150); g.setFont(titleFontEnter); sw =
			 * g.getFontMetrics().stringWidth("Press ENTER to Start!"); sx =
			 * TheAppleToMyPie.width / 2 - sw / 2; g.drawString("Press ENTER to Start!", sx,
			 * 400); g.setFont(titleFontSpace); sw =
			 * g.getFontMetrics().stringWidth("Press SPACE for Instructions"); sx =
			 * TheAppleToMyPie.width / 2 - sw / 2;
			 * g.drawString("Press SPACE for Instructions", sx, 500);
			 */
		}
	}

	void drawInstructions(Graphics g) {
		// g.setColor(Color.WHITE);
		// g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.drawImage(inst, 0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height, null);
	}

	void drawGameState(Graphics g) {
		// g.setColor(Color.CYAN);
		// g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.drawImage(background, 0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height, null);

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

		g.drawImage(apple, 70, 15, 30, 30, null);
		g.drawImage(flour, 170, 15, 30, 30, null);
		g.drawImage(sugar, 270, 15, 30, 30, null);
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

	void drawPie(Graphics g) {
		g.drawImage(GamePanel.pieTin, basket._x++, basket._y, basket._width, basket._height, null);
	}

	void drawEndState(Graphics g) {
		// g.setColor(Color.PINK);
		// g.fillRect(0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height);
		g.drawImage(end, 0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height, null);
		g.setColor(Color.black);
		g.setFont(gameOverFontScore);
		g.drawString("" + manager.score, 300, 400);
		/*
		 * g.drawImage(background, 0, 0, TheAppleToMyPie.width, TheAppleToMyPie.height,
		 * null); g.setColor(Color.black); g.setFont(gameOverFont);
		 * g.drawString("Game Over", 220, 150); g.setFont(gameOverFontScore);
		 * g.drawString("You Completed " + manager.score + " Recipes", 100, 200);
		 * g.setFont(gameOverFontRestart); g.drawString("Press ENTER to start again!",
		 * 100, 500);
		 */
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
				
			}
			else if (currentState == MENU_STATE) {
				startGame();
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

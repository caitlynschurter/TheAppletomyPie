
//Lily's password is ChristJesus321?
import java.awt.Color;
import java.awt.Graphics;

public class Basket extends GameObject {

	boolean rightPressed = false;
	boolean leftPressed = false;
	int speed;

	Basket(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 8;
	}

	public void update() {
		if(leftPressed == true) {
			left();
		}
		
		if(rightPressed == true) {
			right();
		}
		
		super.update();
	}

	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(_x, _y, _width, _height);
	}

	public void right() {
		_x += speed;

		if (_x > TheAppleToMyPie.width - _width) {
			_x = TheAppleToMyPie.width - _width;
		}

	}

	public void left() {
		_x -= speed;
		if (_x < 0) {
			_x = 0;
		}
	}
	
}

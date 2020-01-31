
//Lily's password is JesusChrist123!
import java.awt.Color;
import java.awt.Graphics;

public class Basket extends GameObject {

	int speed;

	Basket(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}

	public void update() {
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

	public void down() {
		_y += speed;
		if (_y > TheAppleToMyPie.height - _height) {
			_y = TheAppleToMyPie.height - _height;
		}
	}

	public void up() {
		_y -= speed;
		if (_y < 0) {
			_y = 0;
		}
	}
	
}


//Lily's password is ChristJesus321?
import java.awt.Color;
import java.awt.Graphics;

public class Pie extends GameObject {

	int speed;

	Pie(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 8;
	}

	public void update() {
		_x += speed;

		if (_x > TheAppleToMyPie.width) {
			isVisible = false;
		}
		super.update();
	}

	public void draw(Graphics g) {
		// g.setColor(Color.DARK_GRAY);
		// g.fillRect(_x, _y, _width, _height);

		g.drawImage(GamePanel.pieTin, _x, _y, _width, _height, null);
	}

}

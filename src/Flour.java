import java.awt.Color;
import java.awt.Graphics;

public class Flour extends GameObject{

	int speed;

	Flour(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
	}

	public void update() {
		super.update();
		_y = _y + speed;
	}

	public void draw(Graphics g) {
		// System.out.println("hi :)");
		g.setColor(Color.GRAY);
		g.drawRect(_x, _y, _width, _height);
	}
}

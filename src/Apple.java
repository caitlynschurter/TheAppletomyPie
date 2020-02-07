
//Evil Lily's password is JesusChrist123!
import java.awt.Color;
import java.awt.Graphics;

public class Apple extends GameObject {

	Apple(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	public void update() {
		super.update();
		_y++;
	}

	public void draw(Graphics g) {
		System.out.println("hi :)");
		g.setColor(Color.RED);
		g.drawRect(_x, _y, _width, _height);
	}
}

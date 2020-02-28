//Evil Lily's password is ChristJesus321?
import java.awt.Color;
import java.awt.Graphics;

public class Sugar extends GameObject {

	int speed;
	
	Sugar(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
	}

	
	public void update() {
		super.update();
		_y=_y + speed;
	}

	public void draw(Graphics g) {
		//System.out.println("hi :)");
		g.setColor(Color.WHITE);
		g.drawRect(_x, _y, _width, _height);
	}
}

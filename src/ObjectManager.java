
//Evil Lily's password is ChristJesus321?
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Basket _basket;

	ArrayList<Apple> apples;

	long itemTimer;
	int itemSpawnTime;

	ObjectManager(Basket basket) {
		_basket = basket;
		apples = new ArrayList<Apple>();
		
		itemTimer = 0;
		itemSpawnTime = 5000;
	}

	void update() {
		for (Apple a : apples) {
			a.update();
		}
	}

	void draw(Graphics g) {
		_basket.draw(g);

		for (Apple a : apples) {
			a.draw(g);
		}
		
		for (Basket b : basket) {
			b.draw(g);
		}
	}

	void addApple(Apple apple) {
		apples.add(apple);
	}

	public void manageIngredients() {
		
		if (System.currentTimeMillis() - itemTimer >= itemSpawnTime) {
			
			addApple(new Apple(new Random().nextInt(TheAppleToMyPie.width), 0, 50, 50));

			itemTimer = System.currentTimeMillis();
		}
	}

	void purgeObjects() {
		for (int i = apples.size() - 1; i >= 0; i--) {
			if (!apples.get(i).isIntact) {
				apples.remove(i);
			}
		}
	}
	
	void checkCollision() {
		for (Apple a : apples) {
			if (_basket.collisionBox.intersects(a.collisionBox)) {
				_basket.isIntact = false;
			}
		}
	}
}

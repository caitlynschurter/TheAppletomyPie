
//Evil Lily's password is ChristJesus321?
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Basket _basket;

	ArrayList<Apple> apples;

	long itemTimer;
	int itemSpawnTime;

	int score = 0;
	int applesCaught = 0;
	int flourCaught = 0;
	int eggsCaught = 0;

	ObjectManager(Basket basket) {
		_basket = basket;
		apples = new ArrayList<Apple>();

		itemTimer = 0;
		itemSpawnTime = 5000;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
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
			if (!apples.get(i).isVisible) {
				apples.remove(i);
			}
		}
	}

	void checkCollision() {
		for (Apple a : apples) {
			if (_basket.collisionBox.intersects(a.collisionBox)) {
				// apple has been caught
				a.isVisible = false;
				applesCaught++;
				System.out.println("apple caught: " + applesCaught);
			}
		}
	}
}


//Evil Lily's password is ChristJesus321?
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Basket _basket;
	Random r = new Random();

	ArrayList<Apple> apples;
	ArrayList<Flour> flour;
	ArrayList<Sugar> sugar;

	long itemTimer;
	int itemSpawnTime;

	int score = 0;
	int applesCaught = 0;
	int flourCaught = 0;
	int sugarCaught = 0;

	ObjectManager(Basket basket) {
		_basket = basket;
		apples = new ArrayList<Apple>();
		flour = new ArrayList<Flour>();
		sugar = new ArrayList<Sugar>();

		itemTimer = 0;
		itemSpawnTime = 4000;
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

		for (Flour f : flour) {
			f.update();
		}

		for (Sugar s : sugar) {
			s.update();
		}
	}

	void draw(Graphics g) {
		_basket.draw(g);

		for (Apple a : apples) {
			a.draw(g);
		}

		for (Flour f : flour) {
			f.draw(g);
		}

		for (Sugar s : sugar) {
			s.draw(g);
		}
	}

	void addApple(Apple apple) {
		apples.add(apple);
	}

	void addFlour(Flour _flour) {
		flour.add(_flour);
	}

	void addSugar(Sugar _sugar) {
		sugar.add(_sugar);
	}

	public void manageIngredients() {

		if (System.currentTimeMillis() - itemTimer >= itemSpawnTime) {

			int what = r.nextInt(3);

			if (what == 0) {
				addApple(new Apple(r.nextInt(TheAppleToMyPie.width), 0, 50, 50));
			}

			else if (what == 1) {
				addFlour(new Flour(r.nextInt(TheAppleToMyPie.width), 0, 50, 50));
			}

			else {
				addSugar(new Sugar(r.nextInt(TheAppleToMyPie.width), 0, 50, 50));
			}

			itemTimer = System.currentTimeMillis();
		}
	}

	void purgeObjects() {
		for (int i = apples.size() - 1; i >= 0; i--) {
			if (!apples.get(i).isVisible) {
				apples.remove(i);
			}
		}

		for (int i = flour.size() - 1; i >= 0; i--) {
			if (!flour.get(i).isVisible) {
				flour.remove(i);
			}
		}

		for (int i = sugar.size() - 1; i >= 0; i--) {
			if (!sugar.get(i).isVisible) {
				sugar.remove(i);
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
				if (applesCaught >= 4) {
					// Play sound effect
					applesCaught = 0;
					flourCaught = 0;
					sugarCaught = 0;
				}
			}
		}

		for (Flour f : flour) {
			if (_basket.collisionBox.intersects(f.collisionBox)) {
				// flour has been caught
				f.isVisible = false;
				flourCaught++;
				System.out.println("flour caught: " + flourCaught);
				if (flourCaught >= 2) {
					// Play sound effect
					applesCaught = 0;
					flourCaught = 0;
					sugarCaught = 0;
				}
			}
		}

		for (Sugar s : sugar) {
			if (_basket.collisionBox.intersects(s.collisionBox)) {
				// flour has been caught
				s.isVisible = false;
				sugarCaught++;
				System.out.println("sugar caught: " + sugarCaught);
				if (sugarCaught >= 3) {
					// Play sound effect
					applesCaught = 0;
					flourCaught = 0;
					sugarCaught = 0;
				}
			}
		}
	}
}

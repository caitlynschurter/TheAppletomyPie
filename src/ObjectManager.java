
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

	int maxApples = 3;
	int maxFlour = 1;
	int maxSugar = 2;

	Pie _pie;

	ObjectManager(Basket basket) {
		_basket = basket;
		apples = new ArrayList<Apple>();
		flour = new ArrayList<Flour>();
		sugar = new ArrayList<Sugar>();

		itemTimer = 0;
		itemSpawnTime = 2000;
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

		if (_pie != null) {
			_pie.update();
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

		if (_pie != null) {
			_pie.draw(g);
		}
	}

	void addApple(Apple apple) {
		apples.add(apple);
	}

	void addFlour(Flour flour) {
		this.flour.add(flour);
	}

	void addSugar(Sugar sugar) {
		this.sugar.add(sugar);
	}

	void addPie(Pie pie) {
		this._pie = pie;
	}

	public void manageIngredients() {

		if (System.currentTimeMillis() - itemTimer >= itemSpawnTime) {

			int what = r.nextInt(4);

			if (what == 0 || what == 1) {
				addApple(new Apple(r.nextInt(TheAppleToMyPie.width-75), 0, 70, 70));
			}

			else if (what == 2) {
				addFlour(new Flour(r.nextInt(TheAppleToMyPie.width-75), 0, 75, 75));
			}

			else {
				addSugar(new Sugar(r.nextInt(TheAppleToMyPie.width-75), 0, 75, 75));
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

		if (_pie != null && !_pie.isVisible) {
			_pie = null;
		}

	}

	void checkCollision() {
		for (Apple a : apples) {
			if (_basket.collisionBox.intersects(a.collisionBox)) {
				// apple has been caught
				a.isVisible = false;
				applesCaught++;
				System.out.println("apple caught: " + applesCaught);
				if (applesCaught > maxApples) {
					// Play sound effect
					GamePanel.counter -= 10 * 60; // 10 secs
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
				if (flourCaught > maxFlour) {
					// Play sound effect
					GamePanel.counter -= 10 * 60; // 10 secs
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
				if (sugarCaught > maxSugar) {
					// Play sound effect
					GamePanel.counter -= 10 * 60; // 10 secs
					applesCaught = 0;
					flourCaught = 0;
					sugarCaught = 0;
				}
			}
		}

		if (applesCaught == maxApples && flourCaught == maxFlour && sugarCaught == maxSugar) {
			setScore(getScore() + 1);
			addPie(new Pie(_basket._x, _basket._y - 50, _basket._width, 80));
			GamePanel.counter += 10 * 60; // 10 secs
			if (GamePanel.counter > 60 * 60) {
				GamePanel.counter = 60 * 60;
			}

			applesCaught = 0;
			flourCaught = 0;
			sugarCaught = 0;
		}

	}
}

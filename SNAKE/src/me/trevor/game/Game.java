package me.trevor.game;

import me.trevor.render.*;

public class Game {
	private Snake snake;
	private Drawer draw;
	private Block apple;

	public boolean start() {
		snake = new Snake();
		draw = new Drawer();
		apple = randomBlock();

		int difficulty = draw.mainMenu(0); // 1 = easy, 2 = medium, 3 = hard
		int frame = 0;
		boolean playing = true;
		while(playing) {	
			if (StdDraw.isKeyPressed(39)) snake.setDirection(1);
			if (StdDraw.isKeyPressed(37)) snake.setDirection(2);
			if (StdDraw.isKeyPressed(38)) snake.setDirection(3);
			if (StdDraw.isKeyPressed(40)) snake.setDirection(4);
			draw.draw(snake.getSnake(), apple);
			if (frame  == 8/difficulty) {
				frame = 0;
				snake.move();
				if (snake.isDead()) playing = false;
				if (snake.isHeadTouching(apple)) {
					snake.makeLonger(3);
					apple = randomBlock();
				}
			}
			frame++;
		}
		return draw.gameOver(snake.getSnake(), apple, difficulty);
	}

	private Block randomBlock() {
		int x = 0;
		int y = 0;
		boolean overlapping = true;
		while((x == 0 && y == 0) || overlapping) {
			x = (int) (Math.random() * 30);
			y = (int) (Math.random() * 30);
			overlapping = false;
			for (Block b : snake.getSnake()) {
				if (b.equals(new Block(x, y))) overlapping = true;
			}
		}
		return new Block(x, y);
	}
}

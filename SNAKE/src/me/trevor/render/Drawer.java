package me.trevor.render;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import me.trevor.game.Block;
import me.trevor.game.ScoreManager;

public class Drawer {
	public int mainMenu(int menuState) { // 0 = title, 1 = difficulty selection, 2 = scores
		StdDraw.setCanvasSize(900, 900);
		StdDraw.enableDoubleBuffering();
		Font font;
		ScoreManager score = new ScoreManager();
		String[] scores = score.getScores();
		

		while (true) {
			StdDraw.clear(new Color(30, 30, 30));
			renderGrid();

			StdDraw.picture(0.5, 0.9, "resources/Title.png");
			if (menuState == 0) {
				if (mouseHover(0.5, 0.7, 0.18, 0.07)) {
					StdDraw.picture(0.5, 0.7, "resources/PlayButtonHover.png");
					if (StdDraw.mousePressed()) menuState = 1;
					while(StdDraw.mousePressed());
				}
				else StdDraw.picture(0.5, 0.7, "resources/PlayButton.png");
				
				if (mouseHover(0.5, 0.5, 0.3, 0.07)) {
					StdDraw.picture(0.5, 0.5, "resources/ScoresButtonHover.png");
					if (StdDraw.mousePressed()) menuState = 2;
					while(StdDraw.mousePressed());
				}
				else StdDraw.picture(0.5, 0.5, "resources/ScoresButton.png");
				
				if (mouseHover(0.5, 0.3, 0.18, 0.07)) {
					StdDraw.picture(0.5, 0.3, "resources/QuitButtonHover.png");
					if (StdDraw.mousePressed()) System.exit(0);
				}
				else StdDraw.picture(0.5, 0.3, "resources/QuitButton.png");

			} else if (menuState == 1) {
				font = new Font("Arial", Font.PLAIN, 30);
				StdDraw.setFont(font);
				StdDraw.setPenColor(255, 255, 255);
				if (mouseHover(0.5, 0.7, 0.18, 0.07)) {
					StdDraw.picture(0.5, 0.7, "resources/EasyButtonHover.png");
					StdDraw.text(0.5, 0.2, "Easy: 1x Speed, 1x Score");
					if (StdDraw.mousePressed()) return 1;
				} else StdDraw.picture(0.5, 0.7, "resources/EasyButton.png");

				if (mouseHover(0.5, 0.5, 0.28, 0.07)) {
					StdDraw.picture(0.5, 0.5, "resources/MediumButtonHover.png");
					StdDraw.text(0.5, 0.2, "Medium: 2x Speed, 2x Score");
					if (StdDraw.mousePressed()) return 2;
				} else StdDraw.picture(0.5, 0.5, "resources/MediumButton.png");

				if (mouseHover(0.5, 0.3, 0.18, 0.07)) {
					StdDraw.picture(0.5, 0.3, "resources/HardButtonHover.png");
					StdDraw.text(0.5, 0.2, "Hard: 4x Speed, 3x Score");
					if (StdDraw.mousePressed()) return 3;
				} else StdDraw.picture(0.5, 0.3, "resources/HardButton.png");

			} else if (menuState == 2) {
				font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
				StdDraw.setFont(font);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0.5, 0.8, "Difficulty   Length    Score");
				int i = 0;
				double y = 0.75;
				for (int line = 0; line < 10; line++) {
					String scoreLine = scores[i++];
					String length = scores[i++];
					String scoreVal = scores[i++];

					int spaces = 10 - length.length();
					int moreSpaces = 5 - scoreVal.length();
					scoreLine += "         " + length;
					for (int j = 0; j < spaces; j++) scoreLine += " ";
					scoreLine += scoreVal;
					for (int j = 0; j < moreSpaces; j++) scoreLine += " ";
					StdDraw.text(0.5, y, scoreLine);
					y -= 0.05;
				}
				
				if (mouseHover(0.5, 0.2, 0.18, 0.07)) {
					StdDraw.picture(0.5, 0.2, "resources/MenuButtonHover.png");
					if (StdDraw.mousePressed()) {
						while(StdDraw.mousePressed());
						menuState = 0;
					}
					
				} else StdDraw.picture(0.5, 0.2, "resources/MenuButton.png");
			}
			StdDraw.show();
		}
	}

	private boolean mouseHover(double midX, double midY, double rangeX, double rangeY) {
		double mouseX = StdDraw.mouseX();
		double mouseY = StdDraw.mouseY();
		return (mouseX < midX + rangeX && mouseX > midX - rangeX) && (mouseY < midY + rangeY && mouseY > midY - rangeY);
	}

	private void renderGrid() {
		StdDraw.setPenColor(55, 55, 55);
		for (double x = 0.5; x < 30; x++) {
			for (double y = 0.5; y < 30; y++) {
				StdDraw.square(x / 30.0, y / 30.0, 1/60.0);
			}
		}
	}

	public void draw(ArrayList<Block> snake, Block apple) {
		StdDraw.clear(new Color(30, 30, 30));
		StdDraw.setPenColor(2, 255, 2);
		for (int i = 0; i < snake.size(); i++) {
			Block b = snake.get(i);
			StdDraw.filledSquare((b.x + 0.5) / 30.0, (b.y + 0.5) / 30.0, 1 / 60.0);
		}
		StdDraw.setPenColor(255, 2, 2);
		StdDraw.filledSquare((apple.x + 0.5) / 30.0, (apple.y + 0.5) / 30.0, 1 / 60.0);
		renderGrid();
		StdDraw.show();
	}

	public boolean gameOver(ArrayList<Block> snake, Block apple, int difficulty) {
		Font font = new Font("Arial", Font.BOLD, 60);
		StdDraw.setFont(font);
		boolean highscore = (new ScoreManager()).addScore(difficulty, difficulty*snake.size());
		
		while(true) {
			StdDraw.clear(new Color(30, 30, 30));
			StdDraw.setPenColor(2, 255, 2);
			for (Block b : snake) {
				StdDraw.filledSquare((b.x + 0.5) / 30.0, (b.y + 0.5) / 30.0, 1 / 60.0);
			}
			StdDraw.setPenColor(255, 2, 2);
			StdDraw.filledSquare((apple.x + 0.5) / 30.0, (apple.y + 0.5) / 30.0, 1 / 60.0);

			StdDraw.setPenColor(255, 153, 0);
			StdDraw.filledSquare((snake.get(0).x + 0.5) / 30.0, (snake.get(0).y + 0.5) / 30.0, 1 / 60.0);

			renderGrid();
			StdDraw.picture(0.5, 0.9, "resources/GameOver.png");

			StdDraw.setPenColor(255, 255, 255);
			StdDraw.text(0.5, 0.8, "Length: " + snake.size());
			StdDraw.text(0.5, 0.7, "Score: " + snake.size() * difficulty);
			StdDraw.text(0.5, 0.6, "Difficulty: " + ScoreManager.getDifficultyString(difficulty));
			if (highscore) StdDraw.text(0.5, 0.5, "High Score!");

			if (mouseHover(0.5, 0.5, 0.18, 0.07)) {
				StdDraw.picture(0.5, 0.5, "resources/MenuButtonHover.png");
				if (StdDraw.mousePressed()) {
					while(StdDraw.mousePressed());
					return true;
				}
			}
			else StdDraw.picture(0.5, 0.5, "resources/MenuButton.png");
			
			if (mouseHover(0.5, 0.3, 0.18, 0.07)) {
				StdDraw.picture(0.5, 0.3, "resources/QuitButtonHover.png");
				if (StdDraw.mousePressed()) System.exit(0);
			}
			else StdDraw.picture(0.5, 0.3, "resources/QuitButton.png");

			StdDraw.show();
		}
	}
}

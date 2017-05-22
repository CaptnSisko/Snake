package me.trevor.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ScoreManager {
	private final String FILEPATH = "src/resources/Scores.dat";
	private final File FILE = new File(FILEPATH);

	public String[] getScores() {
		String[] scores = new String[30];
		Scanner data;
		try {
			data = new Scanner(FILE);
			for (int i = 0; i < 30; i++) {
				scores[i] = data.next();
			}
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scores;

	}

	public boolean addScore(int difficulty, int score) { // 1 = easy, 2 = medium, 3 = hard
		try {
			Scanner read = new Scanner(FILE);

			String[]  scoreLines = new String[10];
			for (int i = 0; i < 10; i++) scoreLines[i] = read.nextLine();

			int[] scoreValues = new int[10]; // scoreValues[0] will have the highest value
			for (int i = 0; i < 10; i++) scoreValues[i] = Integer.parseInt(scoreLines[i].split(" ")[2]);

			int position = 0;
			for (int i = 9; i >= 0; i--) 
				if (score > scoreValues[i]) position++;
			if (position == 0) {
				read.close();
				return false;
			}
			position = 10 - position;

			String newLeaderboard = "";
			for (int i = 0; i < position; i++)
				newLeaderboard += scoreLines[i] + "\n";
			newLeaderboard += getDifficultyString(difficulty) + " " + score/difficulty + " " + score + "\n";
			for (int i = position; i < 9; i++) {
				newLeaderboard += scoreLines[i] + "\n";
			}

			FileWriter write = new FileWriter(FILE);
			write.write(newLeaderboard);	
			read.close();
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getDifficultyString(int difficulty) {
		switch (difficulty) {
		case 1: return "Easy";
		case 2: return "Med.";
		case 3: return "Hard";
		}
		return "Difficulty Error";
	}

}

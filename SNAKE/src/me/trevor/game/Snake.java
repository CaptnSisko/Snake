package me.trevor.game;

import java.util.ArrayList;

public class Snake {
	private ArrayList<Block> blocks;
	private int direction; // 0 = no direction, 1 = right, 2 = left, 3 = up, 4 = down
	
	public Snake() {
		blocks = new ArrayList<Block>();
		direction = 0;
		blocks.add(new Block(0, 0));
	}
	
	public void makeLonger(int amount) {
		for (int i = 0; i < amount; i++) {
			blocks.add(blocks.get(blocks.size() - 1));
		}
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move() {
		for (int i = blocks.size() - 1; i > 0; i--) {
			blocks.set(i, blocks.get(i - 1));
		}
		switch(direction) {
		case 1: blocks.set(0, new Block(blocks.get(0).x + 1, blocks.get(0).y)); break;
		case 2: blocks.set(0, new Block(blocks.get(0).x - 1, blocks.get(0).y)); break;
		case 3: blocks.set(0, new Block(blocks.get(0).x, blocks.get(0).y + 1)); break;
		case 4: blocks.set(0, new Block(blocks.get(0).x, blocks.get(0).y - 1)); break;
		}
	}
	
	public ArrayList<Block> getSnake() {
		return blocks;
	}
	
	public boolean isHeadTouching(Block b) {
		return blocks.get(0).equals(b);
	}
	
	public boolean isDead() {
		int headX = blocks.get(0).x;
		int headY = blocks.get(0).y;
		if (headX > 29 || headX < 0 || headY > 29 || headY < 0) {
			if (headX > 29) blocks.set(0, new Block(29, blocks.get(0).y));
			if (headX < 0) blocks.set(0, new Block(0, blocks.get(0).y));
			if (headY > 29) blocks.set(0, new Block(blocks.get(0).x, 29));
			if (headY < 0) blocks.set(0, new Block(blocks.get(0).x, 0));
			return true;
		}
		for (int i = 1; i < blocks.size(); i++) {
			if (isHeadTouching(blocks.get(i))) return true;
		}
		return false;
	}
}

package me.trevor.game;

public class Block {
	public int x;
	public int y;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Block b) {
		return x == b.x && y == b.y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ") ";
	}
}

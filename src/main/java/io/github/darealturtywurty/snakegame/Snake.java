package io.github.darealturtywurty.snakegame;

import java.awt.Color;
import java.util.Arrays;

import io.github.darealturtywurty.snakegame.BasicKeyListener.Direction;

public class Snake {

	protected static final Color HEAD_COLOR = new Color(0, 200, 0);
	protected static final Color BODY_COLOR = Color.GREEN;
	protected int[] xPos, yPos;
	protected int length = 2;
	protected Direction facing = Direction.RIGHT;
	private final MainPanel panel;

	public Snake(final MainPanel panel, final int[] xPos, final int[] yPos) {
		this.panel = panel;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public boolean checkHit() {
		for (int i = this.length - 1; i > 0; i--) {
			if (this.xPos[0] == this.xPos[i] && this.yPos[0] == this.yPos[i])
				return true;
		}

		if (this.xPos[0] < 0)
			return true;

		if (this.xPos[0] > SnakeGame.SCREEN_WIDTH)
			return true;

		if (this.yPos[0] < 0)
			return true;

		if (this.yPos[0] > SnakeGame.SCREEN_HEIGHT)
			return true;
		return false;
	}

	private void extendBody(final int amount) {
		this.length += amount;
		this.xPos = Arrays.copyOf(this.xPos, this.length);
		this.yPos = Arrays.copyOf(this.yPos, this.length);
		switch (this.facing) {
		case LEFT:
			this.xPos[this.length - 1] = this.xPos[this.length - 2] + 1;
			break;
		case UP:
			this.yPos[this.length - 1] = this.yPos[this.length - 2] - 1;
			break;
		case RIGHT:
			this.xPos[this.length - 1] = this.xPos[this.length - 2] - 1;
			break;
		case DOWN:
			this.yPos[this.length - 1] = this.yPos[this.length - 2] + 1;
			break;
		default:
			break;
		}
	}

	public void move() {
		for (var bodyPart = this.length - 1; bodyPart > 0; bodyPart--) {
			this.xPos[bodyPart] = this.xPos[bodyPart - 1];
			this.yPos[bodyPart] = this.yPos[bodyPart - 1];
		}

		switch (this.facing) {
		case RIGHT:
			this.xPos[0] += SnakeGame.UNIT_SIZE;
			break;
		case UP:
			this.yPos[0] -= SnakeGame.UNIT_SIZE;
			break;
		case DOWN:
			this.yPos[0] += SnakeGame.UNIT_SIZE;
			break;
		case LEFT:
			this.xPos[0] -= SnakeGame.UNIT_SIZE;
			break;
		}
	}

	public void tryEatFood() {
		if (this.xPos[0] == this.panel.food.xPos && this.yPos[0] == this.panel.food.yPos) {
			extendBody(this.panel.food.growthAmount);
			this.panel.food = null;
			this.panel.generateFood();
		}
	}
}

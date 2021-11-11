package io.github.darealturtywurty.snakegame;

import java.awt.Color;

public class Food {

	protected static final Color COLOR = Color.RED;

	protected final int xPos;
	protected final int yPos;
	protected final int growthAmount;

	protected Food(final int xPos, final int yPos, final int growthAmount) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.growthAmount = growthAmount;
	}
}

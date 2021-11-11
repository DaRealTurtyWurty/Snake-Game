package io.github.darealturtywurty.snakegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class BasicKeyListener extends KeyAdapter {

	public enum Direction {
		LEFT, UP, RIGHT, DOWN;
	}

	private final MainPanel panel;

	public BasicKeyListener(final MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (this.panel.snake.facing != Direction.LEFT) {
				this.panel.snake.facing = Direction.LEFT;
			}
			break;
		case KeyEvent.VK_UP:
			if (this.panel.snake.facing != Direction.UP) {
				this.panel.snake.facing = Direction.UP;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (this.panel.snake.facing != Direction.RIGHT) {
				this.panel.snake.facing = Direction.RIGHT;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (this.panel.snake.facing != Direction.DOWN) {
				this.panel.snake.facing = Direction.DOWN;
			}
			break;
		case KeyEvent.VK_R:
			if (!this.panel.ready) {
				this.panel.snake.length = 0;
				this.panel.snake.facing = Direction.RIGHT;
				Arrays.fill(this.panel.snake.xPos, 0);
				Arrays.fill(this.panel.snake.yPos, 0);
				this.panel.run();
			}
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		default:
			break;
		}
	}
}

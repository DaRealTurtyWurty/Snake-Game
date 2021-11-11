package io.github.darealturtywurty.snakegame;

import static io.github.darealturtywurty.snakegame.SnakeGame.FPS;
import static io.github.darealturtywurty.snakegame.SnakeGame.RANDOM;
import static io.github.darealturtywurty.snakegame.SnakeGame.SCREEN_HEIGHT;
import static io.github.darealturtywurty.snakegame.SnakeGame.SCREEN_WIDTH;
import static io.github.darealturtywurty.snakegame.SnakeGame.UNIT_SIZE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1910845096352848916L;

	public static int clamp(final float val, final float min, final float max) {
		return (int) Math.max(min, Math.min(max, val));
	}

	protected boolean ready = false;
	protected transient Food food;
	protected Timer mainTimer;

	protected transient Snake snake;

	public MainPanel() {
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.GRAY);
		setFocusable(true);
		addKeyListener(new BasicKeyListener(this));
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (this.ready) {
			this.snake.move();
			this.snake.tryEatFood();
			if (this.snake.checkHit()) {
				this.ready = false;
				this.mainTimer.stop();
			}
		}
		repaint();
	}

	public void draw(final Graphics graphics) {
		if (this.ready) {
			graphics.setColor(Food.COLOR);
			graphics.fillOval(this.food.xPos, this.food.yPos, UNIT_SIZE, UNIT_SIZE);
			for (var bodyPart = 0; bodyPart < this.snake.length; bodyPart++) {
				if (bodyPart == 0) {
					graphics.setColor(Snake.HEAD_COLOR);
					graphics.fillRect(this.snake.xPos[bodyPart], this.snake.yPos[bodyPart], UNIT_SIZE, UNIT_SIZE);
				} else {
					graphics.setColor(Snake.BODY_COLOR);
					graphics.fillRect(this.snake.xPos[bodyPart], this.snake.yPos[bodyPart], UNIT_SIZE, UNIT_SIZE);
				}
			}
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
			final var fontMetrics = getFontMetrics(graphics.getFont());
			graphics.drawString("Body Length: " + this.snake.length,
					(SCREEN_WIDTH - fontMetrics.stringWidth("Body Length: " + this.snake.length)) / 2,
					graphics.getFont().getSize());
		} else {
			gameOver(graphics);
		}
	}

	protected void gameOver(final Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
		final var fontMetrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Body Length: " + this.snake.length,
				(SCREEN_WIDTH - fontMetrics.stringWidth("Body Length: " + this.snake.length)) / 2,
				graphics.getFont().getSize());
		graphics.setColor(Color.RED);
		final FontMetrics fontMetrics2 = getFontMetrics(graphics.getFont());
		graphics.drawString("Game Over", (SCREEN_WIDTH - fontMetrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
		final FontMetrics fontMetrics3 = getFontMetrics(graphics.getFont());
		graphics.drawString("Press R to Replay", (SCREEN_WIDTH - fontMetrics3.stringWidth("Press R to Replay")) / 2,
				SCREEN_HEIGHT / 2 - 150);
	}

	protected void generateFood() {
		final int xPos = clamp(RANDOM.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE, UNIT_SIZE, SCREEN_WIDTH - UNIT_SIZE);
		final int yPos = clamp(RANDOM.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE, UNIT_SIZE, SCREEN_HEIGHT - UNIT_SIZE);
		this.food = new Food(xPos, yPos, 1);
	}

	@Override
	protected void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics);
	}

	protected void run() {
		this.snake = new Snake(this, new int[] { UNIT_SIZE, UNIT_SIZE * 2 }, new int[] { UNIT_SIZE, UNIT_SIZE * 2 });
		generateFood();
		this.mainTimer = new Timer(FPS, this);
		this.mainTimer.start();
		this.ready = true;
	}
}

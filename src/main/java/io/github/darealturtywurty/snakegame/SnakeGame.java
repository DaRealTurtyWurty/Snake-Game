package io.github.darealturtywurty.snakegame;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SnakeGame extends JFrame {

	private static final long serialVersionUID = 8471315470722815005L;
	protected static final Random RANDOM = new Random();
	protected static final int FPS = 10000 / 60;
	protected static final int UNIT_SIZE = 25;
	protected static final int SCREEN_WIDTH = 1200;
	protected static final int SCREEN_HEIGHT = 600;

	public static void main(final String[] args) {
		final var game = new SnakeGame();
		game.start(args);
	}

	private final MainPanel mainPanel = new MainPanel();

	private void initUI() {
		add(this.mainPanel);
		setTitle("Snake Game");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		requestFocus();
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void start(final String[] args) {
		initUI();
		this.mainPanel.run();
	}
}

package uo.cpm.p6.rules;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Cell;
import uo.cpm.p6.model.Dice;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Meteorite;

public class Game {

	public int maxShots;
	int score;
	int shots;
	private Board board;
	private boolean invaderFound;
	private boolean meteoriteFound;
	private Level level = Level.INTERMEDIATE;

	public enum Level {
		EASY, INTERMEDIATE, HARD
	}

	public Board getBoard() {
		return board;
	}

	public Game() {
		initialize();
	}

	public void initialize() {
		board = new Board(level);
		score = 800;
		shots = 0;
	}

	public void shoot(int i) {
		shots--;

		Cell c = board.getCells()[i];

		if (c instanceof Invader) {
			((Invader) c).setErased(true);
			invaderFound = true;
		} else if (c instanceof Meteorite) {
			this.score = 0;
			meteoriteFound = true;
		}

		score = score + board.getCells()[i].discover();
	}

	public boolean isGameOver() {
		return (invaderFound || meteoriteFound || shots == 0);
	}

	public int getScore() {
		return score;
	}

	public void launch() {
		setShots(Dice.launch(maxShots));
	}

	public int getShots() {
		return shots;
	}

	private void setShots(int shots) {
		this.shots = shots;
	}

	public void setLevel(Level level) {
		this.level = level;
		switch (level) {
		case EASY:
			maxShots = 5;
			break;
		case INTERMEDIATE:
			maxShots = 4;
			break;
		case HARD:
			maxShots = 3;
			break;
		}
		initialize();
	}

	public int getBoardSize() {
		return this.board.getDim();
	}

	public String getGameOverMessage() {
		return invaderFound ? "You found the invader!"
				: meteoriteFound ? "A meteorite has destroyed you!" : shots == 0 ? "You ran out of shots!" : "ERROR";
	}
}

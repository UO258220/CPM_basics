package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game.Level;

public class Board {

	private int dim;
	private int numInvaders;
	private int numMeteorites;
	Cell[] cells;

	public Board(Level level) {
		switch (level) {
		case EASY:
			this.dim = 10;
			this.numInvaders = 2;
			this.numMeteorites = 0;
			break;
		case INTERMEDIATE:
			this.dim = 8;
			this.numInvaders = 1;
			this.numMeteorites = 1;
			break;
		case HARD:
			this.dim = 6;
			this.numInvaders = 1;
			this.numMeteorites = 2;
		}
		cells = new Cell[dim];
		for (int i = 0; i < dim; i++) {
			cells[i] = new Space(i);
		}

		int nInvaders = this.numInvaders;
		while (nInvaders > 0) {
			int invaderPosition = (int) (Math.random() * this.dim);
			if (cells[invaderPosition] instanceof Space) {
				cells[invaderPosition] = new Invader();
				nInvaders--;
			}
		}
		
		int nMeteorites = this.numMeteorites;
		while (nMeteorites > 0) {
			int meteoritePosition = (int) (Math.random() * this.dim);
			if (cells[meteoritePosition] instanceof Space) {
				cells[meteoritePosition] = new Meteorite();
				nMeteorites--;
			}
		}
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public String getPicture(Integer position) {
		return this.cells[position].getPicture();
	}
	
	public int getDim() {
		return this.dim;
	}
}

package uo.cpm.p6.rules;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Dice;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Space;

public class Game {
	
	public static final Integer MAX_SHOTS = 4;
	int score;
	int shots;
	private Board board; 
	private boolean invaderFound;
	
	public Board getBoard() {
		return board;
	}

	public Game(){
		initialize();
	}
	
	public void initialize(){
		board = new Board();
		score = 800;
		shots = 0;
	}

	public void shoot(int i){
		shots --;
		
		if (board.getCells()[i] instanceof Invader) {
			((Invader)board.getCells()[i]).setErased(true);
			invaderFound = true;
		}
		score = score + board.getCells()[i].discover();
	}
	
	private void shoot(Invader invader)
	{
		System.out.println("Invader!");
	}
	
	private void shoot(Space space)
	{
		System.out.println("Space!");

	}
	
	public boolean isGameOver() {
		return (invaderFound || shots == 0);
	}

	public int getScore() {
		return score;
	}

	public void launch() {
		setShots(Dice.launch());	
	}
	
	public int getShots() {
		return shots;
	}

	private void setShots(int shots) {
		this.shots = shots;
	}
}

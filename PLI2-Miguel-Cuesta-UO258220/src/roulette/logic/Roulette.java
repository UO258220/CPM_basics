package roulette.logic;

public class Roulette {

	private final static int STEP_SIZE = 100;
	private final static int MULTIPLICITY = 5;

	public Roulette() {
		// New roulette
	}

	public static int spin() {
		return ((int) (Math.random() * MULTIPLICITY) + 1) * STEP_SIZE;
	}

}

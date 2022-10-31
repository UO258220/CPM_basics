package uo.cpm.p6.model;

public class Dice {
	
	public static int launch(int maxShots)
	{ 
		return ((int) (Math.random() * maxShots) + 1);
	}


}

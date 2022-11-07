package roulette.model;

public class OrderPrize {
	
	private Prize price;
	private int units;
	
	public OrderPrize(Prize price) {
		this(price, 0);
	}

	public OrderPrize(Prize price, int units) {
		this.price = price;
		this.units = units;
	}

	public Prize getPrize() {
		return price;
	}
	
	public int getUnits() {
		return units;
	}

	public void increaseUnits() {
		units++;
	}

}

package roulette.service;

import javax.swing.Icon;

import roulette.logic.Catalog;
import roulette.logic.Roulette;
import roulette.model.Prize;

public class PrizeRoulette {
	
	Catalog catalog = new Catalog();
	
	public int spinRoulette() {
		return Roulette.spin();
	}
	
	public Prize[] getPrices() {
		return catalog.getCatalog();
	}

	public void setPoints(int points) {
		catalog.setPoints(points);
	}

	public int getPoints() {
		return catalog.getPoints();
	}

	public void addPrize(Prize p) {
		catalog.addPrizeToOrder(p);
	}

	public Icon getIconFor(Prize selectedItem) {
		// TODO Auto-generated method stub
		return null;
	}

}

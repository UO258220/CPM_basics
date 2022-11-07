package roulette.logic;

import java.util.ArrayList;
import java.util.List;

import roulette.model.OrderPrize;
import roulette.model.Prize;
import roulette.util.FileUtil;

public class Catalog {

	private static final String PRICES_FILENAME = "files/prizes.dat";

	private List<Prize> listCatalog;
	private List<OrderPrize> order;

	private int points = 0;

	public Catalog() {
		this.listCatalog = new ArrayList<Prize>();
		this.order = new ArrayList<OrderPrize>();
		FileUtil.loadFile(PRICES_FILENAME, listCatalog);
	}

	public Prize[] getCatalog() {
		Prize[] prices = listCatalog.toArray(new Prize[listCatalog.size()]);
		return prices;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}

	public void addPrizeToOrder(Prize p) {
		OrderPrize inOrder = null;

		for (OrderPrize x : this.order) {
			if (x.getPrize().equals(p))
				inOrder = x;
		}

		if (inOrder == null) {
			OrderPrize orderPrice = new OrderPrize(p);
			order.add(orderPrice);
		} else {
			inOrder.increaseUnits();
		}
	}

}

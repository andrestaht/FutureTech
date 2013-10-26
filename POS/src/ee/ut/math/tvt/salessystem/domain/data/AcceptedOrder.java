package ee.ut.math.tvt.salessystem.domain.data;

import java.util.ArrayList;


public class AcceptedOrder implements DisplayableItem{

	private static long ID = 1;
	
	private Long id;

	private ArrayList<SoldItem> soldItems;

	private String date;
	
	private String time;

	private double totalSum;
	
	public AcceptedOrder(ArrayList<SoldItem> soldItems, String date, String time, double totalSum) {
		this.soldItems = soldItems;
		this.date = date;
		this.time = time;
		this.totalSum = totalSum;
		this.id = ID;
		ID += 1;
	}

	@Override
	public Long getId() {
		return id;
	}

	public ArrayList<SoldItem> getSoldItems() {
		return soldItems;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public double getTotalSum() {
		return totalSum;
	}
	
	
}

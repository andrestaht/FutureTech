package ee.ut.math.tvt.salessystem.domain.data;

import java.util.ArrayList;
import java.util.Calendar;

public class AcceptedOrder implements DisplayableItem{

	private static long ID = 1;
	
	private Long id;

	private ArrayList<SoldItem> soldItems;

	private Calendar date;
	
	private Calendar time;

	private double totalSum;
	
	public AcceptedOrder(ArrayList<SoldItem> soldItems, Calendar date, Calendar time, double sum) {
		this.soldItems = soldItems;
		this.date = date;
		this.time = time;
		this.totalSum = sum;
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

	public Calendar getDate() {
		return date;
	}

	public Calendar getTime() {
		return time;
	}

	public double getTotalSum() {
		return totalSum;
	}
	
	
}

package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class AcceptedOrder implements DisplayableItem{

	private static long ID = 1;
	
	private Long id;

	private List<SoldItem> soldItems;

	private String date;
	
	private String time;

	private double totalSum;
	
	public AcceptedOrder(List<SoldItem> soldItems, String date, String time, double sum) {
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

	public List<SoldItem> getSoldItems() {
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

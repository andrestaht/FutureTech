package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCEPTEDORDER")
public class AcceptedOrder implements DisplayableItem{

	private static long ID = 1;

	@Id
	@Column (name = "id")
	private final Long id;

	@OneToMany(mappedBy = "acceptedOrder")
	private final List<SoldItem> soldItems;

	@Column(name = "date")
	private final String date;

	@Column(name = "time")
	private final String time;

	public AcceptedOrder(List<SoldItem> soldItems, String date, String time) {
		this.soldItems = soldItems;
		this.date = date;
		this.time = time;
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

	public String getTotalSum() {
		Double purchaseSum = 0.0;
		for (final SoldItem item : soldItems) {
			purchaseSum += item.getSum();
		}
		return purchaseSum.toString();
	}
}

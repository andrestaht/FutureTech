package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASEITEM")
public class PurchaseItem implements DisplayableItem {

	private static long ID = 1;

	@Id
	@Column(name = "id")
	private final Long id;

	@Column(name = "quantity")
	private int quantity;

	@OneToOne
	@JoinColumn(name = "stockitem_id", nullable = false)
	private final StockItem stockItem;

	public PurchaseItem(StockItem stockItem, int quantity) {
		this.stockItem = stockItem;
		this.quantity = quantity;
		this.id = ID;
		ID += 1;
	}

	@Override
	public Long getId() {
		return id;
	}

	public StockItem getStockItem() {
		return stockItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

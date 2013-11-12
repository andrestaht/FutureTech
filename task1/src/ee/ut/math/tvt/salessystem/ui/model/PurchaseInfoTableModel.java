package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);

	public PurchaseInfoTableModel() {
		super(new String[] {"Id", "Name", "Price", "Quantity", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getStockItemId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < headers.length; i++) {
			buffer.append(headers[i] + "\t");
		}
		buffer.append("\n");
		for (final SoldItem item : rows) {
			buffer.append(item.getStockItemId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public String getPurchaseSum() {
		Double purchaseSum = 0.0;
		for (final SoldItem item : rows) {
			purchaseSum += item.getSum();
		}
		return purchaseSum.toString();
	}

	public void addItem(SoldItem item) {
		rows.add(item);
		log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
		fireTableDataChanged();
	}

	public void updateItem(SoldItem item, Integer quantity) {
		item.setQuantity(quantity);
		log.debug("Found existing item " + item.getName() + " and updated the quantity to " + quantity);
		fireTableDataChanged();
	}

	public boolean hasItems() {
		for (SoldItem row : rows) {
			if (row.getQuantity() > 0) {
				return true;
			}
		}
		return false;
	}

	public SoldItem getItemByStockItem(StockItem stockItem) {
		for (final SoldItem item : rows) {
			if (item.getStockItemId() == stockItem.getId()) {
				return item;
			}
		}
		throw new NoSuchElementException();
	}
}

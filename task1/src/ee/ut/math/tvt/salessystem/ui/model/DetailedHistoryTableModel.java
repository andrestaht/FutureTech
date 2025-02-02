package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class DetailedHistoryTableModel extends SalesSystemTableModel<SoldItem>{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(HistoryTableModel.class);

	public DetailedHistoryTableModel() {
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
		for (final SoldItem soldItem : rows) {
			buffer.append(soldItem.getStockItemId() + "\t");
			buffer.append(soldItem.getName() + "\t");
			buffer.append(soldItem.getPrice() + "\t");
			buffer.append(soldItem.getQuantity() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public void addItems(List<SoldItem> soldItems) {
		rows.clear();
		rows.addAll(soldItems);
		log.debug("Added sold items to the order details table");
	}
}

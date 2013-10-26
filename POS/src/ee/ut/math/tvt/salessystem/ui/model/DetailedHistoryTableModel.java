package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;

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
			return item.getId();
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
	
	/**
	 * Add items to the detailed history table. 
	 * 
	 * @param item
	 */
	public void addItems(ArrayList<SoldItem> soldItems) {
		rows.clear();
		rows.addAll(soldItems);
		log.info("Added sold items to the order details table ");
		fireTableDataChanged();
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < headers.length; i++) {
			buffer.append(headers[i] + "\t");
		}
		buffer.append("\n");
		for (final SoldItem soldItem : rows) {
			buffer.append(soldItem.getId() + "\t");
			buffer.append(soldItem.getName() + "\t");
			buffer.append(soldItem.getPrice() + "\t");
			buffer.append(soldItem.getQuantity() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}
}

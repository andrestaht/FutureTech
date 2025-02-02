package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.domain.data.AcceptedOrder;

public class HistoryTableModel extends SalesSystemTableModel<AcceptedOrder>{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(HistoryTableModel.class);

	public HistoryTableModel() {
		super(new String[] { "Id", "Date", "Time", "Total Price"});
	}

	@Override
	protected Object getColumnValue(AcceptedOrder item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getTotalSumInString();
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
		for (final AcceptedOrder order : rows) {
			buffer.append(order.getId() + "\t");
			buffer.append(order.getDate() + "\t");
			buffer.append(order.getTime() + "\t");
			buffer.append(order.getTotalSumInString() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public void addOrder(final AcceptedOrder order) {
		rows.add(order);
		log.debug("Added new sale " + order.getId() + " with total sum of " + order.getTotalSumInString());
		fireTableDataChanged();
	}

	public AcceptedOrder getOrder(int rowNr) {
		return rows.get(rowNr);
	}
}

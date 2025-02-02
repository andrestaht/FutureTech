package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public StockTableModel() {
		super(new String[] {"Id", "Name", "Price", "Quantity"});
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	/**
	 * Add new stock item to table. If there already is a stock item with same id,
	 * then existing item's quantity will be increased.
	 * 
	 * @param stockItem
	 */
	public void addItem(final StockItem stockItem) {
		try {
			StockItem item = getItemById(stockItem.getId());
			item.setQuantity(item.getQuantity() + stockItem.getQuantity());
			log.debug("Found existing item " + stockItem.getName() + " increased quantity by " + stockItem.getQuantity());
		}
		catch (NoSuchElementException e) {
			rows.add(stockItem);
			log.debug("Added " + stockItem.getName() + " quantity of " + stockItem.getQuantity());
		}
		fireTableDataChanged();
	}

	/**
	 * Modify existing stock item from table.
	 * 
	 * @param stockItem
	 */
	public void modifyItem(final StockItem stockItem) {
		StockItem item = getItemById(stockItem.getId());
		item.setName(stockItem.getName());
		item.setDescription(stockItem.getDescription());
		item.setPrice(stockItem.getPrice());
		item.setQuantity(stockItem.getQuantity());
		log.debug("Modified existing item by id " + stockItem.getId());
		fireTableDataChanged();
	}

	public List<StockItem> decreaseItemsQuantity(List<SoldItem> soldItems) {
		List<StockItem> modifiedItems = new ArrayList<StockItem>();

		for(SoldItem soldItem : soldItems) {
			StockItem item = getItemById(soldItem.getStockItemId());
			item.setQuantity(item.getQuantity() - soldItem.getQuantity());
			modifiedItems.add(item);

			log.debug("Decreased quantity of product " + item.getName() + " by " + soldItem.getQuantity());
			fireTableDataChanged();
		}
		return modifiedItems;
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < headers.length; i++) {
			buffer.append(headers[i] + "\t");
		}
		buffer.append("\n");
		for (final StockItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getName() + "\t");
			buffer.append(stockItem.getPrice() + "\t");
			buffer.append(stockItem.getQuantity() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	// search for items with the specified name
	public List<StockItem> getItemByName(String name) {
		List<StockItem> items = new ArrayList<StockItem>();

		for (final StockItem item : rows) {
			if (item.getName() == name) {
				items.add(item);
			}
		}
		return items;
	}
}

package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {

	private StockTableModel model;

	private StockItem item;

	@Before
	public void setUp() {
		model = new StockTableModel();
		item = new StockItem("Chocolate fondant", "Desert", 4.59, 12);
		model.addItem(item);
	}

	@Test
	public void testValidateNameUniqueness() {
		StockItem itemWithSameName = new StockItem("Chocolate fondant", "AnotherDesert", 1.11, 10);
		model.addItem(itemWithSameName);

		assertEquals(2, model.getItemByName(item.getName()).size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHasEnoughInStock() {
		new SoldItem(item, 14);
	}

	@Test
	public void testGetItemByIdWhenItemExists() {
		assertNotNull(model.getItemById(item.getId()));
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		model.getItemById(-1);
	}
}

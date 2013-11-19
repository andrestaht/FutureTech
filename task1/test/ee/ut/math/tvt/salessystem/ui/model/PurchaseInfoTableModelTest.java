package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;


public class PurchaseInfoTableModelTest {

	private PurchaseInfoTableModel model;

	@Before
	public void setUp() {
		model = new PurchaseInfoTableModel();
	}

	@Test
	public void testAddItem() {
		SoldItem item = new SoldItem(new StockItem("Chocolate fondant", "Desert", 4.59, 12), 4);
		model.addItem(item);

		assertEquals(model.rows.contains(item), true);
	}

	@Test
	public void testGetPurchaseSum() {
		SoldItem item1 = new SoldItem(new StockItem("Chocolate fondant", "Desert", 4.59, 12), 4);
		SoldItem item2 = new SoldItem(new StockItem("Meeter", "Õlut", 10.41, 12), 4);
		Double purhcaseSum = item1.getPrice() * item1.getQuantity() + item2.getPrice() * item2.getQuantity();

		model.addItem(item1);
		model.addItem(item2);

		assertEquals(Double.parseDouble(model.getPurchaseSum()), purhcaseSum, 0.0001);
	}

	@Test
	public void testHasItems() {
		SoldItem item = new SoldItem(new StockItem("Chocolate fondant", "Desert", 4.59, 12), 4);
		model.addItem(item);

		assertEquals(model.hasItems(), true);
	}
}
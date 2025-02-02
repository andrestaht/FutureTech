package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {

	private StockItem item;
	
	@Before
	public void setUp() {
		item = new StockItem("Chocolate fondant", "Desert", 4.59, 12);
	}
	
	@Test
	public void testClone() {
		StockItem clone = (StockItem) item.clone();

		assertEquals(item.getId(), clone.getId());
		assertEquals(item.getName(), clone.getName());
		assertEquals(item.getDescription(), clone.getDescription());
		assertEquals(item.getPrice(), clone.getPrice(), 0.0001);
		assertEquals(item.getQuantity(), clone.getQuantity());
	}
	
	@Test
	public void testGetColumn() {
		assertEquals(item.getId(), item.getColumn(0));
		assertEquals(item.getName(), item.getColumn(1));
		assertEquals(item.getPrice(), item.getColumn(2));
		assertEquals(item.getQuantity(), item.getColumn(3));
	}
}

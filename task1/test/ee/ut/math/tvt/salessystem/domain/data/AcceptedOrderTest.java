package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class AcceptedOrderTest {

	private AcceptedOrder order;

	@Before
	public void setUp() {
		order = new AcceptedOrder(new ArrayList<SoldItem>(), "26.09.2013", "01:02:03");
	}

	@Test
	public void testAddSoldItem() {
		SoldItem soldItem = new SoldItem(new StockItem("Praad", "Mahlane", 6.9, 5), 3);
		order.addSoldItem(soldItem);
		assertEquals(order.getSoldItems().get(order.getSoldItems().size() - 1), soldItem);
	}

	@Test
	public void testGetSumWithNoItems() {
		assertEquals(order.getTotalSum(), 0.0, 0.0001);
	}

	@Test
	public void testGetSumWithOneItem() {
		SoldItem soldItem = new SoldItem(new StockItem("Praad", "Mahlane", 6.9, 5), 1);

		order.addSoldItem(soldItem);
		assertEquals(order.getTotalSum(), soldItem.getPrice(), 0.0001);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		SoldItem soldItem1 = new SoldItem(new StockItem("Praad", "Mahlane", 6.9, 5), 1);
		SoldItem soldItem2 = new SoldItem(new StockItem("Shampanja", "Prantsuse", 20.05, 10), 1);
		SoldItem soldItem3 = new SoldItem(new StockItem("Meeter", "Õlut", 35.69, 20), 1);

		order.addSoldItem(soldItem1);
		order.addSoldItem(soldItem2);
		order.addSoldItem(soldItem3);

		assertEquals(order.getTotalSum(), soldItem1.getPrice() + soldItem2.getPrice() + soldItem3.getPrice(), 0.0001);
	}
}
package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.AcceptedOrder;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class HistoryTableModelTest {

	private HistoryTableModel model;

	private AcceptedOrder order;

	@Before
	public void setUp() {
		model = new HistoryTableModel();
		order = new AcceptedOrder(new ArrayList<SoldItem>(), "01-01-13", "00:00:00");
	}

	@Test
	public void testAddOrder() {
		model.addOrder(order);
		assertEquals(model.rows.contains(order), true);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOrderWithInvalidRowNr() {
		model.getOrder(-1);
	}
}
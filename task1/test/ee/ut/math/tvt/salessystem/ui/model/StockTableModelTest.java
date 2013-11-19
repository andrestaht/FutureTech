package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	
	StockTableModel model;
	StockItem item1;
	StockItem item2;
	
	
	
	@Before
	public void setUp() {
		model = new StockTableModel();
		item1 = new StockItem("Chocolate fondant", "Desert", 4.59, 12);
		item2 = new StockItem("Chocolate fondant", "AnotherDesert", 1.11, 10);
		model.addItem(item1);
		model.addItem(item2);	
	}
	
	@Test
    public void testValidateNameUniqueness() {
		assertEquals(2, model.getItemByName(item1.getName()).size());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testHasEnoughInStock() {
    	new SoldItem(item1, 14);    	
    }
    
    @Test
    public void testGetItemByIdWhenItemExists() { 	    	
    	assertNotNull(model.getItemById(item1.getId())); 	
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testGetItemByIdWhenThrowsException() {
    	model.getItemById(13213);  	
    }
	
	
	
	
	
	
	

}


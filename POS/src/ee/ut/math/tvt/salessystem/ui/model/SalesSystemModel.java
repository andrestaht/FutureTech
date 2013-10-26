package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

	private static final Logger log = Logger.getLogger(SalesSystemModel.class);

	// Warehouse model
	private final StockTableModel warehouseTableModel;

	// Current shopping cart model
	private final PurchaseInfoTableModel currentPurchaseTableModel;
	
	//History model
	private final HistoryTableModel historyTableModel;
	private final DetailedHistoryTableModel detailedHistoryTable;

	private final SalesDomainController domainController;

	/**
	 * Construct application model.
	 * 
	 * @param domainController
	 *          Sales domain controller.
	 */
	public SalesSystemModel(SalesDomainController domainController) {
		this.domainController = domainController;
		warehouseTableModel = new StockTableModel();
		currentPurchaseTableModel = new PurchaseInfoTableModel();
		historyTableModel = new HistoryTableModel();
		detailedHistoryTable = new DetailedHistoryTableModel();
		// populate stock model with data from the warehouse
		warehouseTableModel.populateWithData(domainController.loadWarehouseState());
	}

	public HistoryTableModel getHistoryTableModel() {
		return historyTableModel;
	}
	
	public DetailedHistoryTableModel getDetailedHistoryTable() {
		return detailedHistoryTable;
	}

	public StockTableModel getWarehouseTableModel() {
		return warehouseTableModel;
	}

	public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
		return currentPurchaseTableModel;
	}
}

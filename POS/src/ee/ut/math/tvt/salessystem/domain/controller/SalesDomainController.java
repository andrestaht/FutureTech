package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

	public List<StockItem> loadWarehouseState();

	public List<SoldItem> loadHistory();

	public List<StockItem> loadCurrentPurchase();

	public void startNewPurchase() throws VerificationFailedException;

	public void addNewPurchaseItem(StockItem good);

	public void modifyPurchaseItem(StockItem good);

	public void cancelCurrentPurchase() throws VerificationFailedException;

	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException;

	public void addNewStockItem(StockItem good);

	public void modifyStockItem(StockItem good);

	public void endSession();
}

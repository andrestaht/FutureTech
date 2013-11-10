package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;
import ee.ut.math.tvt.salessystem.domain.data.AcceptedOrder;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

	public List<StockItem> loadWarehouseState();

	public List<AcceptedOrder> loadHistory();

	public void startNewPurchase() throws VerificationFailedException;

	public void cancelCurrentPurchase() throws VerificationFailedException;

	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException;

	public void addNewStockItem(StockItem good) throws VerificationFailedException;

	public void modifyStockItem(StockItem good) throws VerificationFailedException;

	public void modifyStockItems(List<StockItem> goods) throws VerificationFailedException;

	public void addNewOrder(AcceptedOrder order) throws VerificationFailedException;

	public void endSession();

}

package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.AcceptedOrder;
import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

public class SalesDomainControllerImpl implements SalesDomainController {

	private final Session session = HibernateUtil.currentSession();

	private static final Logger log = Logger.getLogger(SalesDomainControllerImpl.class);

	@Override
	public List<StockItem> loadWarehouseState() {
		return (List<StockItem>) session.createQuery("from StockItem").list();
	}

	@Override
	public List<AcceptedOrder> loadHistory() {
		// TODO
		return new ArrayList<AcceptedOrder>();
	}

	@Override
	public List<SoldItem> loadSoldItems() {
		// TODO
		return new ArrayList<SoldItem>();
	}

	@Override
	public void startNewPurchase() throws VerificationFailedException {
		// TODO
	}

	@Override
	public void addNewPurchaseItem(StockItem good) {
		// TODO
	}

	@Override
	public void modifyPurchaseItem(StockItem good) {
		// TODO
	}

	@Override
	public void cancelCurrentPurchase() throws VerificationFailedException {
		// TODO
	}

	@Override
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		saveEntities(goods);
	}

	@Override
	public void addNewStockItem(StockItem good) throws VerificationFailedException {
		List<StockItem> goods = new ArrayList<StockItem>();
		goods.add(good);
		saveEntities(goods);
	}

	@Override
	public void modifyStockItem(StockItem good) throws VerificationFailedException {
		List<StockItem> goods = new ArrayList<StockItem>();
		goods.add(good);
		updateEntities(goods);
	}

	@Override
	public void modifyStockItems(List<StockItem> goods) throws VerificationFailedException {
		updateEntities(goods);
	}

	@Override
	public void addNewOrder(AcceptedOrder order) {
		// TODO
	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();
	}

	private void saveEntities(List<? extends DisplayableItem> items) throws VerificationFailedException {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			for (DisplayableItem item : items) {
				session.persist(item);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
			throw new VerificationFailedException(e);
		}
	}

	private void updateEntities(List<? extends DisplayableItem> items) throws VerificationFailedException {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			for (DisplayableItem item : items) {
				session.merge(item);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
			throw new VerificationFailedException(e);
		}
	}
}

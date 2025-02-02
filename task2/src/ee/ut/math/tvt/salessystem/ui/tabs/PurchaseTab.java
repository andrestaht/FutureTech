package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;
import ee.ut.math.tvt.salessystem.ui.windows.PayingWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

	private static final Logger log = Logger.getLogger(PurchaseTab.class);

	private final SalesDomainController domainController;

	private JButton newPurchase;

	private JButton submitPurchase;

	private JButton cancelPurchase;

	private PurchaseItemPanel purchasePane;

	private SalesSystemModel model;

	private Sale sale;

	private JFrame parent;

	public PurchaseTab(SalesDomainController controller, SalesSystemModel model, JFrame parent) {
		this.domainController = controller;
		this.model = model;
		this.parent = parent;
	}

	/**
	 * The purchase tab. Consists of the purchase menu, current purchase dialog
	 * and shopping cart table.
	 */
	public Component draw() {
		JPanel panel = new JPanel();

		// Layout
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(new GridBagLayout());

		// Add the purchase menu
		panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

		// Add the main purchase-panel
		purchasePane = new PurchaseItemPanel(model);
		panel.add(purchasePane, getConstraintsForPurchasePanel());

		return panel;
	}

	// The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
	private Component getPurchaseMenuPane() {
		JPanel panel = new JPanel();

		// Initialize layout
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraintsForMenuButtons();

		// Initialize the buttons
		newPurchase = createNewPurchaseButton();
		submitPurchase = createConfirmButton();
		cancelPurchase = createCancelButton();

		// Add the buttons to the panel, using GridBagConstraints we defined above
		panel.add(newPurchase, gc);
		panel.add(submitPurchase, gc);
		panel.add(cancelPurchase, gc);

		return panel;
	}

	// Creates the button "New purchase"
	private JButton createNewPurchaseButton() {
		JButton b = new JButton("New purchase");

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPurchaseButtonClicked();
			}
		});
		return b;
	}

	// Creates the "Confirm" button
	private JButton createConfirmButton() {
		JButton confirmButton = new JButton("Confirm");

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPayingPurchase();
			}
		});
		confirmButton.setEnabled(false);

		return confirmButton;
	}

	// Creates the "Cancel" button
	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelPurchaseButtonClicked();
			}
		});
		b.setEnabled(false);

		return b;
	}

	/*
	 * === Event handlers for the menu buttons (get executed when the buttons
	 * are clicked)
	 */

	/** Event handler for the <code>new purchase</code> event. */
	private void newPurchaseButtonClicked() {
		log.info("New sale process started");
		domainController.startNewPurchase();

		startNewSale();
	}

	/** Event handler for the <code>cancel purchase</code> event. */
	private void cancelPurchaseButtonClicked() {
		log.info("Sale cancelled");
		domainController.cancelCurrentPurchase();
		endSale();
		model.getCurrentPurchaseTableModel().clear();
	}

	/** Event handler for the <code>submit purchase</code> event. */
	private void startPayingPurchase() {
		double price = model.getCurrentPurchaseTableModel().getTotalPrice();
		PayingWindow.show(price, this);
	}

	public void endPurchaseAfterPaying() {
		log.info("Sale complete");
		try {
			log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
			domainController.registerSale(sale);
			endSale();
			model.getCurrentPurchaseTableModel().clear();
		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}
	}

	public void cancelPaying() {
		submitPurchase.setEnabled(true);
		cancelPurchase.setEnabled(true);
		purchasePane.setEnabled(true);
	}

	/*
	 * === Helper methods that bring the whole purchase-tab to a certain state
	 * when called.
	 */

	// switch UI to the state that allows to proceed with the purchase
	private void startNewSale() {
		purchasePane.reset();

		showSelectClientDialog();
		sale = new Sale(model.getSelectedClient());
		model.getCurrentPurchaseTableModel().setSale(sale);

		purchasePane.setEnabled(true);
		submitPurchase.setEnabled(true);
		cancelPurchase.setEnabled(true);
		newPurchase.setEnabled(false);
	}

	private void showSelectClientDialog() {
		List<Client> clients = domainController.getAllClients();

		Client currentClient = (Client)JOptionPane.showInputDialog(
			parent,
			"Choose client",
			"Choose client",
			JOptionPane.OK_CANCEL_OPTION,
			(Icon) null,
			clients.toArray(),
			null
		);

		if (currentClient != null) {
			log.info("Client " + currentClient.getFirstName() + " with ID=" + currentClient.getId() + " got selected.");
		} else {
			log.info("No client selected");
		}
		// update selected client
		model.setSelectedClient(currentClient);
	}

	// switch UI to the state that allows to initiate new purchase
	private void endSale() {
		purchasePane.reset();

		cancelPurchase.setEnabled(false);
		submitPurchase.setEnabled(false);
		newPurchase.setEnabled(true);
		purchasePane.setEnabled(false);
	}

	/*
	 * === Next methods just create the layout constraints objects that control
	 * the the layout of different elements in the purchase tab. These
	 * definitions are brought out here to separate contents from layout, and
	 * keep the methods that actually create the components shorter and cleaner.
	 */

	private GridBagConstraints getConstraintsForPurchaseMenu() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		return gc;
	}

	private GridBagConstraints getConstraintsForPurchasePanel() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 1.0;

		return gc;
	}

	// The constraints that control the layout of the buttons in the purchase
	// menu
	private GridBagConstraints getConstraintsForMenuButtons() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.RELATIVE;

		return gc;
	}
}
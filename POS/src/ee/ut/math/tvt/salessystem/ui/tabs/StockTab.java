package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public class StockTab {

	private JButton addItem;
	
	private JButton confirmItemAdd;

	private JButton cancelAdd;

	private JFrame addItemFrame;

	private JPanel addItemPanel;

	private JTextField nameField;

	private JTextField descField;

	private JTextField priceField;

	private JTextField quantityField;

	private final SalesSystemModel model;

	public StockTab(SalesSystemModel model) {
		this.model = model;
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;
		panel.add(drawStockMenuPane(), gc);
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawStockMainPane(), gc);
		return panel;
	}

	// warehouse menu
	private Component drawStockMenuPane() {
		JPanel panel = new JPanel();
		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		panel.setLayout(gb);
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;
		addItem = createAddItemButton();
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.weightx = 1.0;
		panel.add(addItem, gc);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}

	// table of the wareshouse stock
	private Component drawStockMainPane() {
		JPanel panel = new JPanel();
		JTable table = new JTable(model.getWarehouseTableModel());
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		panel.setLayout(gb);
		panel.add(scrollPane, gc);
		panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
		return panel;
	}

	// Creates the button "Add"
	private JButton createAddItemButton() {
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItemButtonClicked();
			}
		});
		return b;
	}

	// Creates the button "Confirm"
	private JButton createConfirmButton() {
		JButton b = new JButton("Confirm");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmItemAddButtonClicked();
			}
		});
		return b;
	}

	// Creates the button "Cancel"
	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItemFrame.dispose();
			}
		});
		return b;
	}
	
	/** Event handler for the <code>add item</code> event. */
	protected void addItemButtonClicked() {
		try {
			popAddItemBox();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Incorrect input, try again", "Warning", JOptionPane.WARNING_MESSAGE);
			addItemFrame.dispose();
		}
	}

	/** Event handler for the <code>confirm item add</code> event. */
	protected void confirmItemAddButtonClicked() {
		try {
			String itemName = nameField.getText();
			String itemDesc = nameField.getText();
			Double itemPrice = Double.parseDouble(priceField.getText());
			int itemQuantity = Integer.parseInt(quantityField.getText());

			if (itemName.isEmpty() || itemDesc.isEmpty() || itemPrice < 0 || itemQuantity <= 0) {
				throw new Exception();
			}
			model.getWarehouseTableModel().addItem(new StockItem(itemName, itemDesc, itemPrice, itemQuantity));
			addItemFrame.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Incorrect input, try again", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Add item popup screen
	private void popAddItemBox() {
		addItemPanel = new JPanel(new GridLayout(5, 2));
	
		nameField = new JTextField();
		descField = new JTextField();
		priceField = new JTextField();
		quantityField = new JTextField();
	
		addItemFrame = new JFrame("Add item");
		addItemFrame.setSize(new Dimension(320, 140));
		addItemFrame.setLocationRelativeTo(null);
		addItemFrame.setResizable(false);
		addItemFrame.add(addItemPanel);
		addItemFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		// add item name textlabel and textfield to panel
		addItemPanel.add(new JLabel("Name: "));
		addItemPanel.add(nameField);
		// add item description textlabel and textfield to panel
		addItemPanel.add(new JLabel("Description: "));
		addItemPanel.add(descField);
		// add item price textlabel and textfield to panel
		addItemPanel.add(new JLabel("Price: "));
		addItemPanel.add(priceField);
		// add item amount textlabel and textfield to panel
		addItemPanel.add(new JLabel("Quantity: "));
		addItemPanel.add(quantityField);
	
		// Initializing confirm and cancel buttons
		confirmItemAdd = createConfirmButton();
		cancelAdd = createCancelButton();
	
		// Adding the buttons
		addItemPanel.add(confirmItemAdd);
		addItemPanel.add(cancelAdd);

		addItemFrame.setVisible(true);
	}
}

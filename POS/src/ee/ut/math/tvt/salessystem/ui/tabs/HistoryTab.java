package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.AcceptedOrder;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	
	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);

	private final SalesSystemModel model;

	public HistoryTab(SalesSystemModel model) {
		this.model = model;
	}

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
		//gc.weighty = 0d;
		gc.weighty = 1.0d;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawHistoryMainPane(), gc);
		return panel;
	}
	
	// table of the history
	private Component drawHistoryMainPane() {
		final JPanel panel = new JPanel();
		final JTable table = new JTable(model.getHistoryTableModel()); 
		table.addMouseListener(new MouseAdapter() {
			//defining a mouse listener for table
			public void mouseReleased(MouseEvent e) {
				int rowNr = table.getSelectedRow();
				AcceptedOrder order = model.getHistoryTableModel().getOrder(rowNr);
				JFrame detailedInfo = new JFrame();
				detailedInfo.setTitle("Details of sale nr " + order.getId());
				detailedInfo.setVisible(true);
				int width = 600;
				int height = 400;
				detailedInfo.setSize(width, height);
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				detailedInfo.setLocation((screen.width - width) / 2 + 10, (screen.height - height) / 2 + 50);
				log.info("Opened info about the sale number " + order.getId());
				detailedInfo.add(drawDetailedHistoryPane(order.getSoldItems()));
			}
		});
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
		panel.setBorder(BorderFactory.createTitledBorder("Purchase History"));
		return panel;
	}
	
	// table of purchase detailed contents
		private Component drawDetailedHistoryPane(List<SoldItem> items) {
			final JPanel panel = new JPanel();
			model.getDetailedHistoryTable().addItems(items);
			final JTable table = new JTable(model.getDetailedHistoryTable()); 
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
			return panel;
		}
	
}
package ee.ut.math.tvt.FutureTech;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class IntroUI extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroUI frame = new IntroUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IntroUI() {

		ArrayList<String> data = getData();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel nameLabel = new JLabel(data.get(0));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(nameLabel);

		JLabel leaderLabel = new JLabel("Team leader:");
		leaderLabel.setText(leaderLabel.getText() + " " + data.get(1));
		contentPane.add(leaderLabel);

		JLabel mailLabel = new JLabel("Team leader e-mail:");
		mailLabel.setText(mailLabel.getText() + " " + data.get(2));
		contentPane.add(mailLabel);

		JLabel membersLabel = new JLabel("Team members:");
		membersLabel.setText(membersLabel.getText() + " " + data.get(3));
		contentPane.add(membersLabel);

		JLabel logoLabel = new JLabel(data.get(4));
		contentPane.add(logoLabel);

		JLabel versionLabel = new JLabel();
		versionLabel.setText(versionLabel.getText() + " ");
		contentPane.add(versionLabel);
	}

	private String getSoftwareVersion() {
		return "";
	}

	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("application.properties"));

			data.add(prop.getProperty("teamName"));
			data.add(prop.getProperty("teamLeader"));
			data.add(prop.getProperty("teamLeaderMail"));
			data.add(prop.getProperty("teamMembers"));
			data.add(prop.getProperty("logoPath"));
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}

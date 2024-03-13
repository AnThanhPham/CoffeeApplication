package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelStatistical extends JPanel {

	private JLabel lblStatistical;

	public PanelStatistical() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);

		lblStatistical = new JLabel("Statistical");
		lblStatistical.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblStatistical.setBounds(279, 160, 91, 71);
		add(lblStatistical);
	}

}

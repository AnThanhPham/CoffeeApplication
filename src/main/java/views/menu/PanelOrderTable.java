package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelOrderTable extends JPanel {

	private JLabel lblOrdertable;

	public PanelOrderTable() {
		setLayout(null);
		setBackground(Color.WHITE);

		lblOrdertable = new JLabel("OrderTable");
		lblOrdertable.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblOrdertable.setBounds(279, 160, 91, 71);
		add(lblOrdertable);
	}

}

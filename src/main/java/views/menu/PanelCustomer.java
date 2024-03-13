package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelCustomer extends JPanel {

	private JLabel lblEmployee;

	public PanelCustomer() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);

		lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEmployee.setBounds(279, 160, 91, 71);
		add(lblEmployee);
	}

}

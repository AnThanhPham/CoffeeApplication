package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelEmployee extends JPanel {

	private JLabel lblEmployee;

	public PanelEmployee() {
		setLayout(null);
		setBackground(Color.WHITE);

		lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEmployee.setBounds(279, 160, 91, 71);
		add(lblEmployee);
	}

}

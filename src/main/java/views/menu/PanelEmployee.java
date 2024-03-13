package views.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEmployee extends JPanel {

	private JLabel lblEmployee;

	public PanelEmployee() {
		setLayout(null);
		setBackground(Color.GRAY);
		setBounds(287, 0, 1063, 826);

		lblEmployee = new JLabel("Employee");
		lblEmployee.setBackground(Color.GRAY);
		lblEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEmployee.setBounds(972, 771, 91, 71);
		add(lblEmployee);
	}

}

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
		setBackground(Color.WHITE);
		setBounds(287,0,1634,1061);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEmployee.setBounds(686, 432, 91, 71);
		add(lblEmployee);
	}

}

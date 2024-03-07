package views.menu;


import java.awt.Color;
import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelBill extends JPanel {

	private JLabel lblBill;

	public PanelBill() {
		setLayout(null);
		setBackground(Color.WHITE);

		lblBill = new JLabel("Bill");
		lblBill.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblBill.setBounds(279, 160, 91, 71);
		add(lblBill);
	}

}

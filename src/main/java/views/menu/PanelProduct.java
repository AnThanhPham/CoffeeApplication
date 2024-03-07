package views.menu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProduct extends JPanel {

	private JLabel lblProduct;

	public PanelProduct() {
		setLayout(null);
		setBackground(Color.WHITE);

		lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblProduct.setBounds(279, 160, 91, 71);
		add(lblProduct);
	}

}

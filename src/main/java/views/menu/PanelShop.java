package views.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PanelShopController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelShop extends JPanel {

	private PanelShopController panelShopController;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	public PanelShop() {
		init();	
		panelShopController =  new PanelShopController(this);
	}
	
	public void init() {
		setBackground(Color.GRAY);
		setLayout(null);

		lblNewLabel = new JLabel("shop");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel.setBounds(279, 160, 91, 71);
		add(lblNewLabel);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(436, 186, 89, 23);
		add(btnNewButton);
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	
	
}

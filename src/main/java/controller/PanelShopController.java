package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import views.menu.PanelShop;

public class PanelShopController {
	private PanelShop panelShop;

	public PanelShopController(PanelShop panelShop) {
		this.panelShop = panelShop;
		addEvent();
	}

	private void addEvent() {
		panelShop.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelShop, "Đã ấn");
			}
		});
	}
}

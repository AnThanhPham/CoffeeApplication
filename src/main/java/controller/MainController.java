package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.Main;

public class MainController {
	private Main main;

	public MainController(Main main) {
		this.main = main;
		addEvent();
	}

	private void addEvent() {
		main.getBtnShop().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelShop");
			}
		});

		main.getBtnBill().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelBill");
			}
		});
		
		main.getBtnCategories().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelCategory");
			}
		});
		
		main.getBtnManageCustomer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelCustomer");
			}
		});
		
		main.getBtnManageProduct().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelProduct");
			}
		});
		
		main.getBtnStatistical().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelStatistical");
			}
		});
		
		main.getBtnOrderTable().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelOrderTable");
			}
		});
		
		main.getBtnManageEmployee().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCardLayout().show(main.getPanelCard(), "panelEmployee");
			}
		});
		
		main.getBtnMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}

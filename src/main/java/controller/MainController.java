package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.UserModel;
import views.ChangePasswordView;
import views.Login;
import views.Main;

public class MainController {
	private Main main;

	public MainController(Main main,UserModel user) {
		this.main = main;
		addEvent();
		authorization(user);
	}
	
	void authorization(UserModel user) {
		if(user.getRole().getRoleName().equals("Admin")) {
			// admin
		}else if(user.getRole().getRoleName().equals("Employee")) {
			//employee		
			main.getBtnManageCustomer().setVisible(false);
			main.getBtnManageEmployee().setVisible(false);
			main.getBtnStatistical().setVisible(false);
			main.getBtnManageProduct().setVisible(false);
			main.getBtnCategories().setVisible(false);
			main.getBtnBill().setBounds(0, 158, 286, 66);
			main.getBtnOrderTable().setBounds(0, 248, 286, 66);
		}
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
			
		main.getLogout().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.dispose();
				new Login().setVisible(true);
			}
		});
			
		main.getChangePass().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView();
				changePasswordView.setLocationRelativeTo(null);
				changePasswordView.setVisible(true);
			}
		});

	}
}

package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelCategory extends JPanel {

	private JLabel lblCategory;

	public PanelCategory() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);

		lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCategory.setBounds(279, 160, 91, 71);
		add(lblCategory);
	}

}

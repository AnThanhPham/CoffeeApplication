package views.menu.Product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import views.menu.PanelProduct;

public class PagePanel extends JLabel {	
	private int pageNumber;

	public PagePanel(int pageNumber) {
		this.setHorizontalAlignment(CENTER);
		this.setForeground(Color.WHITE);
		this.pageNumber = pageNumber;
		this.setText(pageNumber + "");
		// TODO Auto-generated constructor stub
		setOpaque(false);// TRUE THI HIEN BACHGROUND MAC DINH
		this.setPreferredSize(new Dimension(30, 30));

		// add su kien khi an pagenumber

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				PanelProduct.getIns().showPage(pageNumber);

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		super.paintComponent(g);

	}
}


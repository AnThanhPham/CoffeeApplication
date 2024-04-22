package views.menu.Product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

import model.ProductModel;


public class ItemProduct extends JPanel {
	JLabel centerItem;
	JLabel southItem;

	public ItemProduct(ProductModel model){
		if(model==null) {
			this.setVisible(false);
			return ;
		}
		
		centerItem = new TImage(new ImageIcon(model.getImage()));
		southItem = new JLabel();
		
//		ImageIcon image=new ImageIcon(model.getImage());
//		centerItem.setIcon(image);
		centerItem.setOpaque(true);
		centerItem.setBackground(Color.lightGray);
//		if(model.getName().length()>=10 ) {
//			String trimmedName = model.getName().substring(0, 17) + "...";
//			southItem.setText("<html>"+"<html><div style=' overflow: hidden; text-overflow: ellipsis;'>" +
//					"Tên: "+ trimmedName+ "<br>"+"Giá: "+ model.getPrice()+"</html>");
//
//		}else
		southItem.setText("<html>"+"Tên: "+model.getName()+ "<br>"+"Giá: "+ model.getPrice()+"</html>");
		
		southItem.setForeground(Color.darkGray);
		southItem.setFont(new Font("Times New roman",Font.PLAIN,20));
		
		this.setLayout(new BorderLayout());
		this.add(centerItem);
		this.add(southItem,BorderLayout.SOUTH);
		
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
				new insertUpdateDelete(model,false);
				
		}
		});
		
	
	}

}

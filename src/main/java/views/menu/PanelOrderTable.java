package views.menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;

import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import org.apache.pivot.wtk.Insets;
import org.jfree.chart.ui.Size2D;
import java.util.*;
import dao.TableDAO;
import model.ProductModel;
import model.TableModel;

public class PanelOrderTable extends JPanel {
	JLabel title= new JLabel("Quản Lý Đặt Bàn");
	Font fontTitle= new Font("Consolas", Font.BOLD,22);
	TableModel model= new TableModel();
	JPanel centerPanel= new JPanel();
		
	java.util.List<TableModel> listTable= TableDAO.selectAll();
	 
		
	public PanelOrderTable() {
		
		
		this.setLayout(new BorderLayout());
		title.setFont(fontTitle);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		this.add(title,BorderLayout.NORTH);

		
		
		centerPanel.setLayout(new GridLayout(4, 4, 30, 30));
		ItemTable[] ItemTable = new ItemTable[17];
		for (int i = 0; i < 16; i++) {
			ItemTable[i]= new ItemTable( listTable.get(i));
			centerPanel.add(ItemTable[i]);
		}
		this.add(centerPanel,BorderLayout.CENTER);
		this.setVisible(false);
		this.setVisible(true);
		
		// set this
		centerPanel.setBorder(new EmptyBorder(30,30,30,30));
		this.setBorder(new EmptyBorder(40,10,20,10));
	
	
		
	}
	
	public static void main(String[] args) {
		JFrame orderTable= new JFrame();
		orderTable.add(new PanelOrderTable());
		orderTable.setSize(1000,800);
		orderTable.setVisible(true);
	}




}

 class tableButton extends JButton {
	 protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			super.paintComponent(g);

		}
}
 class ItemTable extends JButton {
	 
	 	TableModel tm;
	 	
		JLabel tableItem;

		ItemTable( TableModel model){
			tm= model;
			
			tableItem = new JLabel();
			
			
			tableItem.setText("Bàn "+ model.getID());
			tableItem.setForeground(new Color(0xFFE0E6));
			tableItem.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
			tableItem.setVerticalAlignment(JLabel.CENTER);
			tableItem.setHorizontalAlignment(JLabel.CENTER);
			
			
			
			
			this.add(tableItem);
			this.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int result= JOptionPane.showConfirmDialog(null, "Bạn có muốn chuyển trạng thái không","Thông báo", JOptionPane.YES_NO_OPTION);
					if (result==JOptionPane.YES_OPTION) {
						if(model.getStatus().equals("Full")) {
							model.setStatus("Available");
							ItemTable.this.setBackground(Color.LIGHT_GRAY);
							System.out.println(model.getStatus());
						}else {
							model.setStatus("Full");
							ItemTable.this.setBackground(new Color(0x438BF7));
						}
					}
					 try {
						TableDAO.update(model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			if( model.getStatus().equals("Full")) {
				this.setBackground(new Color(0x438BF7));
				
				
			}else {
				this.setBackground(Color.lightGray);
			}
			
		
		}
	}


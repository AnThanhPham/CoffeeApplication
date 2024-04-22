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

import controller.PanelTableController;

import java.util.*;
import dao.TableDAO;
import model.ProductModel;
import model.TableModel;
import views.menu.OrderTable.ItemTable;

public class PanelOrderTable extends JPanel {
	private static PanelOrderTable ins;
	public static PanelOrderTable getIns() {
		return ins;
	}
	
	PanelTableController controller= new PanelTableController(this);
	public PanelTableController getController() {
		return controller;
	}
	public TableDAO tableDao= new TableDAO();
	JLabel title= new JLabel("Quản Lý Bàn");
	Font fontTitle= new Font("Consolas", Font.BOLD,22);
	TableModel model= new TableModel();
	JPanel centerPanel= new JPanel();
		public JPanel getCenterPanel() {
			return centerPanel;
		}
	
	 
		
	public PanelOrderTable() {
		ins= this;
		
		this.setLayout(new BorderLayout());
		title.setFont(fontTitle);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		this.add(title,BorderLayout.NORTH);

		
		
		centerPanel.setLayout(new GridLayout(4, 4, 30, 30));
		// gọi reload
		controller.reload();
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

 



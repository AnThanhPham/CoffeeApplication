package views.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CategoryModel;
import model.ProductModel;

public class PanelProduct extends JPanel {
	JPanel centerCenterPanel = new JPanel();
	JPanel northCenterPanel= new JPanel();
	JPanel southCenterPanel= new JPanel();
	JPanel centerPanel= new JPanel();
	JPanel eastPanel= new JPanel();
	JPanel westPanel= new JPanel();
	JTextField searchTextField= new JTextField();// Thanh tìm kiếm
	JButton  submit= new JButton("Tìm"); // nuts submit
	JButton insertProduct= new JButton("Thêm sản phẩm"); // Them san pham
	JLabel searchLabel= new JLabel("Tìm kiếm");
	JLabel menu= new JLabel("Loại: ");
	
	
	// CenterPanel
	JLabel product1= new JLabel();	
	JLabel nameProduct1= new JLabel("Ten: Trà dâu");
	JLabel priceProduct1= new JLabel("Gia: 55.000");
	JLabel descriptionPr1= new JLabel("Sản phẩm là sự kết hợp từ dâu tây, trà nhài,..");
	JPanel leftCenterpanel = new JPanel();
	
	//JComboBox<String> typeProduct= new JComboBox<>(new String []{"cafe","banh ngot","nước ép"});
	JMenuBar outMenu= new JMenuBar();
	JMenu menu1= new JMenu("Menu");
	String []typeProduct = {"cafe","nuoc ngot","banh ngot","Nuoc ep"};
	

	public PanelProduct() {
		
		
	// set layout cho panel chinh
		this.setLayout(new BorderLayout());
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(eastPanel,BorderLayout.EAST);
		this.add(westPanel,BorderLayout.WEST);
	
	// Set centerPanel
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(northCenterPanel,BorderLayout.NORTH);
		centerPanel.add(southCenterPanel,BorderLayout.SOUTH);
		centerPanel.add(centerCenterPanel);
		
		
	// SEt kich thuoc cho tung phan	
		northCenterPanel.setPreferredSize(new Dimension(50,50));// setsize cho boder layout
		southCenterPanel.setPreferredSize(new Dimension(50,50));// setsize cho boder layout
		eastPanel.setPreferredSize(new Dimension(30,30));// setsize cho boder layout
		westPanel.setPreferredSize(new Dimension(30,30));// setsize cho boder layout
		//centerPanel.setBackground(Color.red);
		
	// set cac phan trong norrthPanel
		northCenterPanel.setLayout(new BorderLayout());
		JPanel topNorthPanel= new JPanel();
		JPanel centerNorthPanel= new JPanel();
		northCenterPanel.add(topNorthPanel, BorderLayout.NORTH);
		northCenterPanel.add(centerNorthPanel);
		searchTextField.setPreferredSize(new Dimension(300,30));
		
		// tao left right center cho topcenterpanel
		JPanel leftTop= new JPanel();
		JPanel centerTop= new JPanel();
		JPanel rightTop= new JPanel();
		topNorthPanel.setLayout(new BorderLayout());
		topNorthPanel.add(centerTop);
		topNorthPanel.add(rightTop,BorderLayout.EAST);
		topNorthPanel.add(leftTop,BorderLayout.WEST);
		//centerTop.setBackground(Color.gray);
		centerTop.add(searchLabel);
		centerTop.add(searchTextField);
		centerTop.add(submit);
		rightTop.add(insertProduct);
		leftTop.add(menu);
		//leftTop.add(typeProduct);
		for(int i=0; i<typeProduct.length    ; i++) {
			menu1.add(typeProduct[i]);
		}
		outMenu.add(menu1);
		leftTop.add(outMenu);
		
		
		
		
		// responsive topNorthPane
		topNorthPanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			
			public void componentResized(ComponentEvent e) {
				int width= centerTop.getWidth();
				System.out.println("width"+ width);
				// TODO Auto-generated method stub
				searchTextField.setPreferredSize(new Dimension(width/2,30));
				searchTextField.setVisible(false);
				searchTextField.setVisible(true);
				
			
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		// set centerCenterpanel
		centerCenterPanel.setLayout(new GridLayout(2,4));
		ProductModel testProductModel = new ProductModel(1,  10000f,"cafe", "cafe cho son", "src/main/java/img/product/img1.png", new CategoryModel(1,"cafe","cafe"));
		ItemProduct product1= new ItemProduct(testProductModel);
		ItemProduct product2= new ItemProduct(testProductModel);
		ItemProduct product3= new ItemProduct(testProductModel);
		ItemProduct product4= new ItemProduct(testProductModel);
		ItemProduct product5= new ItemProduct(testProductModel);
		ItemProduct product6= new ItemProduct(testProductModel);
		ItemProduct product7= new ItemProduct(testProductModel);
		ItemProduct product8= new ItemProduct(testProductModel);
		centerCenterPanel.add(product1);
		centerCenterPanel.add(product2);
		centerCenterPanel.add(product3);
		centerCenterPanel.add(product4);
		centerCenterPanel.add(product5);
		centerCenterPanel.add(product6);
		centerCenterPanel.add(product7);
		centerCenterPanel.add(product8);
		
		southCenterPanel.add(new CirclePanel());
		southCenterPanel.setBackground(Color.magenta);
		
		
		
			
		
		
				
	}
	public static void main(String[] args) {
		JFrame jf= new JFrame();
		jf.setVisible(true);
		jf.add(new PanelProduct() );
		jf.setSize(600,400);
		
	}

}



// tao them class cho itemProduct
class ItemProduct extends JLabel{
	ItemProduct(ProductModel model){
		this.setText("<html>"+"Tên: "+model.getName()+ "<br>"+"Giá: "+ model.getPrice()+"</html>");
		
		ImageIcon image=new ImageIcon(model.getImage());
		this.setIcon(image);
		this.setHorizontalTextPosition(this.CENTER);
		this.setVerticalTextPosition(this.BOTTOM);
		this.setForeground(Color.darkGray);
		this.setFont(new Font("Times New roman",Font.PLAIN,20));
		
		
		
	}
	
}
class CirclePanel extends JPanel{
	public CirclePanel() {
		// TODO Auto-generated constructor stub
		setOpaque(false);
		this.setPreferredSize(new Dimension(30,30));
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		
	}
}

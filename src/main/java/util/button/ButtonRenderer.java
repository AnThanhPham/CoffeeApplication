package util.button;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{
	public ButtonRenderer() {
		// set button properties
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean Selected, boolean Focus,int row, int column) {
		
		// set passed object as button text
		setText((obj==null) ? "": obj.toString());
		
		return this;
	}
	
}

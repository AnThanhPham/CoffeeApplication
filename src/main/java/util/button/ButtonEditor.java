package util.button;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.PanelBillController;

public class ButtonEditor extends DefaultCellEditor{

	protected JButton btn;
	private String lb1;
	private boolean clicked;
	public ButtonEditor(JTextField txt) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				if (action.equals("+")) {
                   
                } else if (action.equals("-")) {
               
                } else if (action.equals("delete")) {
                	
                }
				
				fireEditingStopped();
			}
		});
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean Selected, int row, int column) {
		lb1 = (obj==null) ? "": obj.toString();
		btn.setText(lb1);
		clicked=true;
		return btn;
	}
	
	@Override
	public Object getCellEditorValue() {
		if(clicked) {
			
		}
		clicked=false;
		return new String(lb1);
		}
	
	@Override
	public boolean stopCellEditing() {
		clicked= false;
		return super.stopCellEditing();
	}
	
	@Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
	}
}

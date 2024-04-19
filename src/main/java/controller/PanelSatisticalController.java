package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.data.category.DefaultCategoryDataset;

import dao.BillDAO;
import views.menu.PanelStatistical;

public class PanelSatisticalController {
	private BillDAO billDao = new BillDAO();
	private PanelStatistical statistical;
	
	public PanelSatisticalController (PanelStatistical statistical) {
		this.statistical = statistical;
		graph();
	}
	
	public void graph() {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// dataset.setValue(0, null, null);
		 statistical.getYear().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = (int) statistical.getYear().getSelectedIndex();
				 if(idx !=0) {
					TreeMap<Integer,Double> map =  billDao.FitterDataByYear((String) statistical.getYear().getSelectedItem());
					for(int i=1;i<=12;i++) {
						if(map.containsKey(i))
							continue;
						else map.put(i, 0.0);
					}
					for (Map.Entry<Integer, Double> x : map.entrySet()) {
						//System.out.println(entry.getKey()+" "+entry.getValue());
						 dataset.setValue(x.getValue(), "Tháng "+String.valueOf(x.getKey()), "");
					}
					//System.out.println(map.size());
					statistical.getChart().getCategoryPlot().setDataset(dataset);
				 }
			}
		});
		 
		
	}
}
package views.menu;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.PanelBillController;
import controller.PanelSatisticalController;

public class PanelStatistical extends JPanel {
	private JComboBox<String> Year;
	private JFreeChart chart;
	private PanelSatisticalController controller;
	
    public PanelStatistical() {
    	graphColumn();
    	controller = new PanelSatisticalController(this);

    }
    
    public void graphColumn() {
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	 /*
         dataset.setValue(10, "Tháng 1", "");
         dataset.setValue(20, "Tháng 2", "");
         dataset.setValue(30, "Tháng 3", "");
         dataset.setValue(40, "Tháng 4", "");
         dataset.setValue(10, "Tháng 5", "");
         dataset.setValue(20, "Tháng 6", "");
         dataset.setValue(30, "Tháng 7", "");
         dataset.setValue(40, "Tháng 8", "");
         dataset.setValue(50, "Tháng 9", ""); 
         dataset.setValue(40, "Tháng 10", "");
         dataset.setValue(10, "Tháng 11", "");
         dataset.setValue(20	, "Tháng 12", "");
         */
         // Create a bar chart
        chart = ChartFactory.createBarChart(
        		  "Sample Statistical Chart",
                  "Thống kê doanh thu",
                  "Value",
                  dataset,
                  PlotOrientation.VERTICAL,
                  true, // Include legend
                  true, // Include tooltips
                  false  // No URL generation
         );
         
        /*
         chart.getCategoryPlot().setDataset(newDataset);
    chart.fireChartRefresh(); // Refresh the chart
         */
         ChartPanel chartPanel = new ChartPanel(chart);
         chartPanel.setPreferredSize(new java.awt.Dimension(1000, 700));
         // Add the chart to the panel
         add(chartPanel);  
         
         Year = new JComboBox<String>();
         for(int i=2020;i<=2024;i++) {
        	 Year.addItem(i+"");
         }
         add(Year);
    }

	public JComboBox<String> getYear() {
		return Year;
	}

	public void setYear(JComboBox<String> year) {
		Year = year;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public PanelSatisticalController getController() {
		return controller;
	}

	public void setController(PanelSatisticalController controller) {
		this.controller = controller;
	}
}

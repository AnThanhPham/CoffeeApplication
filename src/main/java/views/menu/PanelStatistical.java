package views.menu;

import javax.swing.JButton;
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
	private JButton Reresh;
	
    public PanelStatistical() {
    	graphColumn();
    	controller = new PanelSatisticalController(this);

    }
    
    public void graphColumn() {
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	 /*
         dataset.setValue(10, "Tháng 1", "");
         */
         // Create a bar chart
        chart = ChartFactory.createBarChart(
        		  "Thống kê doanh thu",
                  "",
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
         add(Year);
         
         Reresh = new JButton("làm mới");
         add(Reresh);
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

	public JButton getReresh() {
		return Reresh;
	}

	public void setReresh(JButton reresh) {
		Reresh = reresh;
	}
}

package views.menu;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelStatistical extends JPanel {
	private JComboBox<String> Year;
    public PanelStatistical() {
    	graphColumn();
    }
    
    public void graphColumn() {
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         dataset.setValue(25, "Series 1", "Category A");
         dataset.setValue(30, "Series 1", "Category B");
         dataset.setValue(45, "Series 2", "Category A");
         dataset.setValue(10, "Series 2", "Category B");

         // Create a bar chart
         JFreeChart chart = ChartFactory.createBarChart(
                 "Sample Statistical Chart",
                 "Category",
                 "Value",
                 dataset,
                 PlotOrientation.VERTICAL,
                 true, // Include legend
                 true, // Include tooltips
                 false  // No URL generation
         );
         
         ChartPanel chartPanel = new ChartPanel(chart);
         chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
         // Add the chart to the panel
         add(chartPanel);  
         
         Year = new JComboBox<String>();
         for(int i=2000;i<=2024;i++) {
        	 Year.addItem(i+"");
         }
         add(Year);
    }
}

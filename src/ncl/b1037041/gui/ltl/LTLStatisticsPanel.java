package ncl.b1037041.gui.ltl;

import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import ncl.b1037041.LTL.entites.StatisticsLTLUsage;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class LTLStatisticsPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	public LTLStatisticsPanel() {
		setLayout(null);
		
		List<StatisticsLTLUsage> usages = ltlManager.getLTLUsageStatistics();
		Iterator<StatisticsLTLUsage> it = usages.iterator();	
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		StatisticsLTLUsage usage = null;
		while(it.hasNext()) {
			usage = it.next();
			dataSet.addValue(usage.getNum(), "number", usage.getNickname());
		}

        JFreeChart chart = ChartFactory.createBarChart3D("Usage of LTLs",
                "LTL", "Usage", dataSet, PlotOrientation.VERTICAL,
                false, false, false);
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        // Set the unit to be integer
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel panel = new ChartPanel(chart);
        panel.setLocation(10, 10);
        panel.setSize(770, 520);
        this.add(panel);
	}
}

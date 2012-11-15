package be.boeboe.scapsync.android.stats;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import be.boeboe.scapsync.android.R;

public class StatsActivity extends Activity {
  private LinearLayout fLayout;
  private GraphicalView fBarChartView;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stats);

    fLayout = (LinearLayout) findViewById(R.id.stats_graph);
    if (fBarChartView == null) {
      XYMultipleSeriesRenderer renderer = getBarRenderer();
      setChartSettings(renderer);
      fBarChartView  = ChartFactory.getBarChartView(this, getBarDataset(),renderer, Type.DEFAULT);
      fLayout.addView(fBarChartView);
    } else {
      fBarChartView.repaint();
      fBarChartView.setVisibility(View.GONE);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }
  
  private void setChartSettings(XYMultipleSeriesRenderer renderer) {
    renderer.setChartTitle("SCAP Chart");
    renderer.setXTitle("Type");
    renderer.setYTitle("Hits");
    renderer.setXAxisMin(0);
    renderer.setXAxisMax(5);
    renderer.setYAxisMin(0);
  }
  
  public XYMultipleSeriesRenderer getBarRenderer() {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    renderer.setAxisTitleTextSize(16);
    renderer.setChartTitleTextSize(20);
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setBarSpacing(1);
    renderer.setLabelsColor(Color.WHITE);
    renderer.setMarginsColor(Color.BLACK);
    renderer.addXTextLabel(1, "Configurations");
    renderer.addXTextLabel(2, "Products");
    renderer.addXTextLabel(3, "Vulnerabilities");
    renderer.addXTextLabel(4, "Weaknesses");
    renderer.setMargins(new int[] {30, 30, 30, 30});
    SimpleSeriesRenderer r = new SimpleSeriesRenderer();
    r.setColor(Color.RED);
    renderer.addSeriesRenderer(r);
    return renderer;
  }
  
  private XYMultipleSeriesDataset getBarDataset() {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    Random r = new Random();
    CategorySeries series = new CategorySeries("WeekDay series");
    for(int i=0;i<4;i++) {
      series.add( 100 + r.nextInt() % 100);
    }
    dataset.addSeries(series.toXYSeries());
    return dataset;
  }
}

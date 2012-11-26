package be.boeboe.scapsync.android.stats;

import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncHandle;
import be.boeboe.scapsync.rest.interfaces.IScapSyncStats;

public class StatsActivity extends Activity {
  private LinearLayout fLayout;
  private GraphicalView fBarChartView;
  private IScapSyncStats fScapSyncStats;
  private ProgressDialog fProgressDialog; 

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stats);

    fLayout = (LinearLayout) findViewById(R.id.stats_graph);
    new StatsTask().execute();
  }

  private void showBarChart() {
    if (fBarChartView == null) {
      XYMultipleSeriesRenderer renderer = getBarRenderer(fScapSyncStats);
      setChartSettings(renderer, fScapSyncStats);
      fBarChartView  = ChartFactory.getBarChartView(this, getBarDataset(fScapSyncStats),renderer, Type.DEFAULT);
      fLayout.addView(fBarChartView);
      fBarChartView.setOnClickListener(onChartClickListener);
    } else {
      fBarChartView.repaint();
      fBarChartView.setVisibility(View.GONE);
    }
  }

  private OnClickListener onChartClickListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      SeriesSelection seriesSelection = fBarChartView.getCurrentSeriesAndPoint();
      
      if (seriesSelection == null) {
      } else {
        Toast.makeText(getApplicationContext(), 
          (int) seriesSelection.getValue() + " records found.",
          Toast.LENGTH_SHORT)
          .show();
      }
    }
  };

  private void setChartSettings(XYMultipleSeriesRenderer renderer, IScapSyncStats stats) {
    renderer.setChartTitle(stats.getTitle());
    renderer.setXTitle(stats.getLabels()[0]);
    renderer.setYTitle(stats.getLabels()[1]);
    renderer.setXAxisMin(0);
    renderer.setXAxisMax(stats.getStatistics().size() + 1);
    renderer.setYAxisMin(0);
  }
  
  public XYMultipleSeriesRenderer getBarRenderer(IScapSyncStats stats) {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    renderer.setAxisTitleTextSize(16);
    renderer.setChartTitleTextSize(20);
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setBarSpacing(1);
    renderer.setLabelsColor(Color.WHITE);
    renderer.setMarginsColor(Color.BLACK);
    renderer.setShowLegend(false);

    int count = 0;
    renderer.clearXTextLabels();
    int[] margins = new int[stats.getStatistics().size()];
    for (Map.Entry<String, Integer> entry :  stats.getStatistics().entrySet()) {
      margins[count] = 30;
      count++;
      renderer.addXTextLabel(count, entry.getKey());
    }

    renderer.setMargins(margins);
    renderer.setPanEnabled(false);
    renderer.setZoomEnabled(false);
    renderer.setClickEnabled(true);
    SimpleSeriesRenderer r = new SimpleSeriesRenderer();
    r.setColor(Color.RED);
    renderer.addSeriesRenderer(r);
    return renderer;
  }

  private XYMultipleSeriesDataset getBarDataset(IScapSyncStats stats) {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    //CategorySeries series = new CategorySeries("WeekDay series");
    CategorySeries series = new CategorySeries("");

    for (Map.Entry<String, Integer> entry :  stats.getStatistics().entrySet()) {
      series.add(entry.getValue());
    }

    dataset.addSeries(series.toXYSeries());
    return dataset;
  }

  private class StatsTask extends AsyncTask<Void, IScapSyncStats, IScapSyncStats> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      fProgressDialog = new ProgressDialog(StatsActivity.this);
      fProgressDialog.setMessage("Fetching SCAP statistics...");
      fProgressDialog.setIndeterminate(false);
      fProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      fProgressDialog.setCancelable(false);
      fProgressDialog.show();
    }

    @Override
    protected IScapSyncStats doInBackground(Void... params) {
      IScapSyncStats stats = doStatsLookup();
      return stats;
    }

    private IScapSyncStats doStatsLookup() {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      return scapSyncHandle.getStatistics();
    }

    @Override
    protected void onPostExecute(IScapSyncStats stats) {
      super.onPostExecute(stats);
      fProgressDialog.dismiss();
      fScapSyncStats = stats;
      showBarChart();
    }
  }
}

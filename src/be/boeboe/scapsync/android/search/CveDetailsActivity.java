package be.boeboe.scapsync.android.search;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncHandle;
import be.boeboe.scapsync.rest.interfaces.IScapSyncCveDetails;

public class CveDetailsActivity extends Activity {
  public static String CVE_ID = "search_filter";
  private ActionBar fActionBar;
  private ProgressDialog fProgressDialog;
  private RatingBar fVulnerabilityScore;
  private TextView fCvePublishedDate;
  private TextView fCveModifiedDate;
  private TextView fCveSummary;
  private TextView fVulnerabilityScoreDetails;
  
  private String fCveId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cve_details);
    fActionBar = getActionBar();

    fCvePublishedDate = (TextView) findViewById(R.id.cve_published_date);
    fCveModifiedDate = (TextView) findViewById(R.id.cve_modified_date);
    fCveSummary = (TextView) findViewById(R.id.cve_summary);
    fVulnerabilityScore = (RatingBar) findViewById(R.id.vulnerability_score);
    fVulnerabilityScoreDetails = (TextView) findViewById(R.id.vulnerability_score_details);
    
    fCveId = getIntent().getStringExtra(CVE_ID);
    
    //fActionBar.setDisplayHomeAsUpEnabled(true);
    fActionBar.removeAllTabs();
    fActionBar.setTitle(fCveId);
    fActionBar.setSubtitle(R.string.cve_full);
    fActionBar.setHomeButtonEnabled(true);
    new CveDetailsTask().execute(fCveId);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case android.R.id.home:
      NavUtils.navigateUpFromSameTask(this);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  
  private class CveDetailsTask extends AsyncTask<String, IScapSyncCveDetails, IScapSyncCveDetails> {
    
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      fProgressDialog = new ProgressDialog(CveDetailsActivity.this);
      fProgressDialog.setMessage("Getting details for '" + fCveId + "'");
      fProgressDialog.setIndeterminate(false);
      fProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      fProgressDialog.setCancelable(false);
      fProgressDialog.show();
    }
    
    @Override
    protected IScapSyncCveDetails doInBackground(String... params) {
      if (params != null && params.length > 0 && params[0] != null) {
        String cveId = params[0];
        return getCveDetails(cveId);
      }
      return null;
    }

    protected void onPostExecute(IScapSyncCveDetails cveDetails) {
      super.onPostExecute(cveDetails);
      fProgressDialog.dismiss();
      showCveDetails(cveDetails);
    }

    /**
     * @param cveDetails
     */
    private void showCveDetails(final IScapSyncCveDetails cveDetails) {
      fCvePublishedDate.setText(cveDetails.getUpstreamPublishedDate().toString());
      fCveModifiedDate.setText(cveDetails.getUpstreamModifiedDate().toString());
      fCveSummary.setText(cveDetails.getSummary());
      fVulnerabilityScore.setRating(cveDetails.getCvssBaseScore() / 2);
      fVulnerabilityScoreDetails.setText(cveDetails.getCvss().toString());

      fVulnerabilityScore.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          Toast.makeText(getApplicationContext(), "Score: " + cveDetails.getCvssBaseScore(),
              Toast.LENGTH_SHORT).show();
          return true;
        }
      });
    }

    private IScapSyncCveDetails getCveDetails(String cveId) {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      return scapSyncHandle.getCveDetails(cveId);
    }
  }
}

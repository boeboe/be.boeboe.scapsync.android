package be.boeboe.scapsync.android.feeds;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncHandle;
import be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed;


public class FeedsActivity extends Activity {
  private Tab fNewTab;
  private Tab fChangedTab;
  private ProgressDialog fProgressDialog;
  private IScapSyncDailyFeed fScapDailyFeeds;
  private TabListener<NewListFragment> fNewTabListener;
  private TabListener<ChangedListFragment> fChangedTabListener;

  @Override
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_feeds);
    
    new FeedsTask().execute();
  }
  
  public IScapSyncDailyFeed getDailyFeed() { 
    System.out.println("FeedsActivity.getDailyFeed(): " + fScapDailyFeeds.getChangedItems().length);
    return fScapDailyFeeds;
  }

  /**
   * 
   */
  private void createMyActionBar() {
    ActionBar actionBar = getActionBar();
    actionBar.setTitle("Scap Tool");
    actionBar.setSubtitle("Daily Feeds");
    actionBar.setHomeButtonEnabled(true);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    // actionBar.setDisplayShowTitleEnabled(false);
    
    fNewTab = actionBar.newTab();
    fChangedTab = actionBar.newTab();
   
    fNewTabListener = new TabListener<NewListFragment>(this, R.id.fragmentContainer, NewListFragment.class, fScapDailyFeeds);
    fChangedTabListener = new TabListener<ChangedListFragment>(this, R.id.fragmentContainer,ChangedListFragment.class, fScapDailyFeeds);

    fNewTab.setText("New")
           .setContentDescription("New tab")
           .setTabListener(fNewTabListener);
    
    fChangedTab.setText("Changed")
               .setContentDescription("Changed tab")
               .setTabListener(fChangedTabListener);

    actionBar.addTab(fNewTab);
    actionBar.addTab(fChangedTab);
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

  public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment fFragment;
    private Activity fActivity;
    private Class<T> fFragmentClass;
    private int fFragmentContainer;
    private IScapSyncDailyFeed fDailyFeeds;

    public TabListener(Activity activity, int fragmentContainer,
        Class<T> fragmentClass, IScapSyncDailyFeed dailyFeeds) {
      fActivity = activity;
      fFragmentContainer = fragmentContainer;
      fFragmentClass = fragmentClass;
      fDailyFeeds = dailyFeeds;
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
      if (fFragment == null) {
        String fragmentName = fFragmentClass.getName();
        fFragment = Fragment.instantiate(fActivity, fragmentName);
        
        ft.add(fFragmentContainer, fFragment, fragmentName);
      } else {
        ft.attach(fFragment);
      }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
      if (fFragment != null) {
        ft.detach(fFragment);
      }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
      if (fFragment != null) {
        ft.attach(fFragment);
      }
    }
  }
  
  private class FeedsTask extends AsyncTask<Void, IScapSyncDailyFeed, IScapSyncDailyFeed> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      fProgressDialog = new ProgressDialog(FeedsActivity.this);
      fProgressDialog.setMessage("Fetching SCAP feeds...");
      fProgressDialog.setIndeterminate(false);
      fProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      fProgressDialog.setCancelable(false);
      fProgressDialog.show();
    }

    @Override
    protected IScapSyncDailyFeed doInBackground(Void... params) {
      IScapSyncDailyFeed feeds = doDailyFeedLookup();
      return feeds;
    }

    private IScapSyncDailyFeed doDailyFeedLookup() {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      return scapSyncHandle.getDailyFeed();
    }

    @Override
    protected void onPostExecute(IScapSyncDailyFeed feeds) {
      super.onPostExecute(feeds);
      fProgressDialog.dismiss();
      fScapDailyFeeds = feeds;
      System.out.println("FeedsActivity.FeedsTask.onPostExecute(): " + fScapDailyFeeds.getChangedItems().length);
      createMyActionBar();
    }
  }
}

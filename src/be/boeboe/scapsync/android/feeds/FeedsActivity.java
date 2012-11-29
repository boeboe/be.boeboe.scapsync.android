package be.boeboe.scapsync.android.feeds;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncHandle;
import be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed;

@SuppressLint("SimpleDateFormat")
public class FeedsActivity extends Activity {
  private Tab fNewTab;
  private Tab fChangedTab;
  private ProgressDialog fProgressDialog;
  private IScapSyncDailyFeed fScapDailyFeeds;
  private TabListener<NewListFragment> fNewTabListener;
  private TabListener<ChangedListFragment> fChangedTabListener;
  private ActionBar fActionBar;
  private Calendar fCalender;
  private Date fTodayDate;
  private Date fMinDate;
  private MenuItem fDateMenuItem;
  private static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_feeds);
    fCalender = Calendar.getInstance();
    fTodayDate = fCalender.getTime();
    try {
      fMinDate = DATE_FORMATTER.parse("2012-07-14");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    new FeedsTask().execute();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_feeds, menu);
    fDateMenuItem = menu.findItem(R.id.menu_date);
    return true;
  }

  public IScapSyncDailyFeed getDailyFeed() { 
    return fScapDailyFeeds;
  }

  /**
   * 
   */
  private void createMyActionBar() {
    fActionBar = getActionBar();
    fActionBar.removeAllTabs();
    fActionBar.setTitle("Feeds");
    fActionBar.setHomeButtonEnabled(true);
    fActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    //actionBar.setDisplayShowTitleEnabled(false);

    fNewTab = fActionBar.newTab();
    fChangedTab = fActionBar.newTab();

    fNewTabListener = new TabListener<NewListFragment>(this, R.id.fragmentContainer, NewListFragment.class);
    fChangedTabListener = new TabListener<ChangedListFragment>(this, R.id.fragmentContainer,ChangedListFragment.class);

    fNewTab.setText("New (" + fScapDailyFeeds.getNewItems().length + ")")
           .setContentDescription("New tab")
           .setTabListener(fNewTabListener);

    fChangedTab.setText("Changed (" + fScapDailyFeeds.getChangedItems().length + ")")
               .setContentDescription("Changed tab")
               .setTabListener(fChangedTabListener);

    fActionBar.addTab(fNewTab);
    fActionBar.addTab(fChangedTab);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
      case R.id.menu_previous:
        if (fCalender.getTimeInMillis() > fMinDate.getTime()) {
          fCalender.add(Calendar.DATE, -1); 
          Date yesterdayDate = new Date(fCalender.getTimeInMillis());
          new FeedsTask().execute(yesterdayDate);
          String formattedYesterdayDate = DATE_FORMATTER.format(yesterdayDate);
          fDateMenuItem.setTitle(formattedYesterdayDate.toString());
          return true;
        }
      case R.id.menu_calender:
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, fDateSetListener, 
            fCalender.get(Calendar.YEAR), fCalender.get(Calendar.MONTH), fCalender.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(fMinDate.getTime());
        datePickerDialog.getDatePicker().setMaxDate(fTodayDate.getTime());
        datePickerDialog.show();
        return true;
      case R.id.menu_next:
        if (fCalender.getTimeInMillis() < fTodayDate.getTime()) {
          fCalender.add(Calendar.DATE, 1); 
          Date tomorrowDate = new Date(fCalender.getTimeInMillis());
          new FeedsTask().execute(tomorrowDate);
          String formattedTomorrowDate = DATE_FORMATTER.format(tomorrowDate);
          fDateMenuItem.setTitle(formattedTomorrowDate.toString());
          return true;
        }
    }
    return super.onOptionsItemSelected(item);
  }

  private DatePickerDialog.OnDateSetListener fDateSetListener = new OnDateSetListener() {
    @Override      
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      Date date;
      try {
        date = DATE_FORMATTER.parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        new FeedsTask().execute(date);
        String formattedDate = DATE_FORMATTER.format(date);
        fDateMenuItem.setTitle(formattedDate.toString());
        fCalender.set(year, monthOfYear, dayOfMonth);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
  };
  
  public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment fFragment;
    private Activity fActivity;
    private Class<T> fFragmentClass;
    private int fFragmentContainer;

    public TabListener(Activity activity, int fragmentContainer,
        Class<T> fragmentClass) {
      fActivity = activity;
      fFragmentContainer = fragmentContainer;
      fFragmentClass = fragmentClass;
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

  private class FeedsTask extends AsyncTask<Date, IScapSyncDailyFeed, IScapSyncDailyFeed> {
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
    protected IScapSyncDailyFeed doInBackground(Date... dates) {
      IScapSyncDailyFeed feeds;
      if (dates.length == 0) {
        feeds = doDailyFeedLookup();
      } else {
        feeds = doFeedLookup(dates[0]);
      }
      return feeds;
    }

    private IScapSyncDailyFeed doDailyFeedLookup() {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      return scapSyncHandle.getDailyFeed();
    }

    private IScapSyncDailyFeed doFeedLookup(Date date) {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      return scapSyncHandle.getFeed(date);
    }

    @Override
    protected void onPostExecute(IScapSyncDailyFeed feeds) {
      super.onPostExecute(feeds);
      fProgressDialog.dismiss();
      fScapDailyFeeds = feeds;
      Toast.makeText(getApplicationContext(), fScapDailyFeeds.getChangedItems().length
          + fScapDailyFeeds.getNewItems().length + " feeds fetched",
          Toast.LENGTH_LONG).show();
      createMyActionBar();
    }
  }
}

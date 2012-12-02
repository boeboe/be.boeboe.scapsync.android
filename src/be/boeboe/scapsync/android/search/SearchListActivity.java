package be.boeboe.scapsync.android.search;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncHandle;
import be.boeboe.scapsync.rest.ScapSyncSearch;
import be.boeboe.scapsync.rest.ScapSyncSearchListener;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;

public class SearchListActivity extends ListActivity {
  public final static String SEARCH_TERM = "search_term";
  public final static String SEARCH_FILTER = "search_filter";
  public final static String SEARCH_FILTER_ALL = "search_filter_all";
  public final static String SEARCH_FILTER_CPE = "search_filter_cpe";
  public final static String SEARCH_FILTER_CVE = "search_filter_cve";
  public final static String SEARCH_FILTER_CWE = "search_filter_cwe";

  private ProgressDialog fProgressDialog;
  private ActionBar fActionBar;
  private ArrayList<IScapSyncSearchResult> fResults = new ArrayList<IScapSyncSearchResult>();
  private String fSearchTerm;
  private String fSearchFilter;
  private Tab fCceTab;
  private Tab fCpeTab;
  private Tab fCveTab;
  private Tab fCweTab;
  private TabListener<SearchListFragment> fCceSearchResultTabListener;
  private TabListener<SearchListFragment> fCpeSearchResultTabListener;
  private TabListener<SearchListFragment> fCveSearchResultTabListener;
  private TabListener<SearchListFragment> fCweSearchResultTabListener;

  @SuppressLint("NewApi")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_list);
 
    fSearchTerm = getIntent().getStringExtra(SEARCH_TERM);
    fSearchFilter = getIntent().getStringExtra(SEARCH_FILTER);
    new SearchTask().execute(fSearchTerm);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(SEARCH_TERM, fSearchTerm);
    outState.putString(SEARCH_FILTER, fSearchFilter);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    fSearchTerm = savedInstanceState.getString(SEARCH_TERM);
    fSearchFilter = savedInstanceState.getString(SEARCH_FILTER);
  }

  public ArrayList<IScapSyncSearchResult> getResults() { 
    return fResults;
  }

  private void createMyActionBar() {
    fActionBar = getActionBar();
    fActionBar.removeAllTabs();
    fActionBar.setTitle(R.string.title_activity_search_list);
    fActionBar.setHomeButtonEnabled(true);
    fActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    
    fCceTab = fActionBar.newTab();
    fCpeTab = fActionBar.newTab();
    fCveTab = fActionBar.newTab();
    fCweTab = fActionBar.newTab();
    
    fCceSearchResultTabListener = new TabListener<SearchListFragment>(this, R.id.fragmentContainer, SearchListFragment.class,"cce");
    fCpeSearchResultTabListener = new TabListener<SearchListFragment>(this, R.id.fragmentContainer, SearchListFragment.class,"cpe");
    fCveSearchResultTabListener = new TabListener<SearchListFragment>(this, R.id.fragmentContainer, SearchListFragment.class,"cve");
    fCweSearchResultTabListener = new TabListener<SearchListFragment>(this, R.id.fragmentContainer, SearchListFragment.class,"cwe");
    
    fCceTab.setText("CCE (" + fResults.size() + ")")
    .setContentDescription("CCE Tab")
    .setTabListener(fCceSearchResultTabListener);

    fCpeTab.setText("CPE (" + fResults.size() + ")")
    .setContentDescription("CPE Tab")
    .setTabListener(fCpeSearchResultTabListener);

    fCveTab.setText("CVE (" + fResults.size() + ")")
    .setContentDescription("CVE Tab")
    .setTabListener(fCveSearchResultTabListener);

    fCweTab.setText("CWE (" + fResults.size() + ")")
    .setContentDescription("CWE Tab")
    .setTabListener(fCweSearchResultTabListener);

    fActionBar.addTab(fCceTab);
    fActionBar.addTab(fCpeTab);
    fActionBar.addTab(fCveTab);
    fActionBar.addTab(fCweTab);
  }

  private void updateMyActionBar() {
    int countCce = 0;
    int countCpe = 0;
    int countCve = 0;
    int countCwe = 0;
    for (IScapSyncSearchResult res: fResults) {
      if ( res.getType().equals(IScapSyncSearchResultType.TYPE_CCE) ) { countCce++ ; }
      else if ( res.getType().equals(IScapSyncSearchResultType.TYPE_CPE) ) { countCpe++ ; }
      else if ( res.getType().equals(IScapSyncSearchResultType.TYPE_CVE) ) { countCve++ ; }
      else if ( res.getType().equals(IScapSyncSearchResultType.TYPE_CWE) ) { countCwe++ ; }
    }
    fCceTab.setText("CCE (" + countCce + ")");
    fCpeTab.setText("CPE (" + countCpe + ")");
    fCveTab.setText("CVE (" + countCve + ")");
    fCweTab.setText("CWE (" + countCwe + ")");
  }

  public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment fFragment;
    private Activity fActivity;
    private Class<T> fFragmentClass;
    private int fFragmentContainer;
    private Bundle fFilter = new Bundle();

    public TabListener(Activity activity, int fragmentContainer,
        Class<T> fragmentClass, String filter) {
      fActivity = activity;
      fFragmentContainer = fragmentContainer;
      fFragmentClass = fragmentClass;
      fFilter.putString(SearchListFragment.FILTER, filter);;
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
      if (fFragment == null) {
        String fragmentName = fFragmentClass.getName();
        fFragment = Fragment.instantiate(fActivity, fragmentName);
        fFragment.setArguments(fFilter);
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

  private class SearchTask extends AsyncTask<String, IScapSyncSearchResult[], IScapSyncSearchResult[]> {
    private boolean fFirstArrived = false;
    
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      fProgressDialog = new ProgressDialog(SearchListActivity.this);
      fProgressDialog.setMessage("Searching for '" + fSearchTerm + "'");
      fProgressDialog.setIndeterminate(false);
      fProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      fProgressDialog.setCancelable(false);
      fProgressDialog.show();
    }
    
    @Override
    protected IScapSyncSearchResult[] doInBackground(String... params) {
      if (params != null && params.length > 0 && params[0] != null) {
        String searchItem = params[0];
        doSearch(searchItem, fSearchFilter);
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(IScapSyncSearchResult[]... results) {
      super.onProgressUpdate(results);
      if (!fFirstArrived) {
        fProgressDialog.dismiss();
        fFirstArrived = true;
        createMyActionBar();
      }
      fResults.addAll(Arrays.asList(results[0]));
      updateMyActionBar();
    }

    protected void onPostExecute(IScapSyncSearchResult[] res) {
      super.onPostExecute(res);
      Toast.makeText(getApplicationContext(), "Search finished with " + fResults.size() + " results",
          Toast.LENGTH_LONG).show();
    }

    private void doSearch(String searchItem, String filter) {
      ScapSyncHandle scapSyncHandle = new ScapSyncHandle();
      ScapSyncSearch search;
      ScapSyncSearchListener searchListener = new ScapSyncSearchListener() {
        public void resultReceived(IScapSyncSearchResult[] searchResults) {
          publishProgress(searchResults);
        }
      };

      if (filter.equals(SEARCH_FILTER_ALL)) {
        search = scapSyncHandle.search(searchItem, null, null);
      } else if (filter.equals(SEARCH_FILTER_CPE)) {
        search = scapSyncHandle.search(searchItem, IScapSyncSearchResultType.TYPE_CPE, null);
      } else if (filter.equals(SEARCH_FILTER_CVE)) {
        search = scapSyncHandle.search(searchItem, IScapSyncSearchResultType.TYPE_CVE, null);
      } else if (filter.equals(SEARCH_FILTER_CWE)) {
        search = scapSyncHandle.search(searchItem, IScapSyncSearchResultType.TYPE_CWE, null);
      } else {
        search = scapSyncHandle.search(searchItem, null, null);
      }

      search.addSearchListener(searchListener);
      search.run();
    }
  }
}

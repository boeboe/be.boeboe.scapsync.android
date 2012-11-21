package be.boeboe.scapsync.android.search;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncSearch;
import be.boeboe.scapsync.rest.ScapSyncSearchListener;
import be.boeboe.scapsync.rest.ScapSyncSearcher;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;

public class SearchListActivity extends ListActivity {
  public static String SEARCH_TERM = "search_term";
  public static String SEARCH_FILTER = "search_filter";
  public static String SEARCH_FILTER_ALL = "search_filter_all";
  public static String SEARCH_FILTER_CPE = "search_filter_cpe";
  public static String SEARCH_FILTER_CVE = "search_filter_cve";
  public static String SEARCH_FILTER_CWE = "search_filter_cwe";

  private SearchAdapter fSearchAdapter;
  private ArrayList<IScapSyncSearchResult> fResults = new ArrayList<IScapSyncSearchResult>();
  private ListView fListView;
  private String fSearchTerm;
  private String fSearchFilter;

  @SuppressLint("NewApi")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
 
    fSearchTerm = getIntent().getStringExtra(SEARCH_TERM);
    fSearchFilter = getIntent().getStringExtra(SEARCH_FILTER);
    new SearchTask().execute(fSearchTerm);
    fSearchAdapter = new SearchAdapter(this, R.layout.activity_search_list, fResults);
    setListAdapter(fSearchAdapter);
    fListView = getListView();
    
    fListView.setDivider(getResources().getDrawable(R.drawable.gradient));
    fListView.setDividerHeight(1);
  }


  private class SearchTask extends AsyncTask<String, IScapSyncSearchResult[], IScapSyncSearchResult[]> {
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
      fSearchAdapter.addAll(new ArrayList<IScapSyncSearchResult>(Arrays.asList(results[0])));
      fSearchAdapter.notifyDataSetChanged();
    }

    protected void onPostExecute(IScapSyncSearchResult[] res) {
      super.onPostExecute(res);
      Toast.makeText(getApplicationContext(), "Search finished with " + fSearchAdapter.getCount() + " results",
          Toast.LENGTH_LONG).show();
    }

    private void doSearch(String searchItem, String filter) {
      ScapSyncSearcher searcher = new ScapSyncSearcher();
      ScapSyncSearch search;
      ScapSyncSearchListener searchListener = new ScapSyncSearchListener() {
        public void resultReceived(IScapSyncSearchResult[] searchResults) {
          publishProgress(searchResults);
        }
      };

      if (filter.equals(SEARCH_FILTER_ALL)) {
        search = searcher.searchAll(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CPE)) {
        search = searcher.searchCpe(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CVE)) {
        search = searcher.searchCve(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CWE)) {
        search = searcher.searchCwe(searchItem);
      } else {
        search = searcher.searchAll(searchItem);
      }
      
      search.addSearchListener(searchListener);
      search.run();
    }
  }
}

package be.boeboe.scapsync.android.search;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import be.boeboe.scapsync.android.R;
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
  private String fSearchTerm;
  private String fSearchFilter;

  @SuppressLint("NewApi")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
 
    fSearchTerm = getIntent().getStringExtra(SEARCH_TERM);
    fSearchFilter = getIntent().getStringExtra(SEARCH_FILTER);
    new SearchTask().execute(fSearchTerm);
    fSearchAdapter = new SearchAdapter(this, R.layout.activity_search_list, new ArrayList<IScapSyncSearchResult>());
    setListAdapter(fSearchAdapter);
    
    getListView().setDivider(getResources().getDrawable(R.drawable.gradient));
    getListView().setDividerHeight(1);
  }

  private class SearchTask extends AsyncTask<String, Integer, IScapSyncSearchResult[]> {

    @Override
    protected IScapSyncSearchResult[] doInBackground(String... params) {
      if (params != null && params.length > 0 && params[0] != null) {
        String searchItem = params[0];
        return doSearch(searchItem, fSearchFilter);
      }
      return null;
    }

    protected void onPostExecute(IScapSyncSearchResult[] result) {
      fSearchAdapter.addAll(new ArrayList<IScapSyncSearchResult>(Arrays.asList(result)));
      fSearchAdapter.notifyDataSetChanged();
    }
    
    private IScapSyncSearchResult[] doSearch(String searchItem, String filter) {
      ScapSyncSearcher searcher = new ScapSyncSearcher();
      IScapSyncSearchResult[] results;
      if (filter.equals(SEARCH_FILTER_ALL)) {
        results = searcher.searchAll(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CPE)) {
        results = searcher.searchCpe(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CVE)) {
        results = searcher.searchCve(searchItem);
      } else if (filter.equals(SEARCH_FILTER_CWE)) {
        results = searcher.searchCwe(searchItem);
      } else {
        results = searcher.searchAll(searchItem);
      }
      
      searcher.close();
      return results;
    }
  }
}

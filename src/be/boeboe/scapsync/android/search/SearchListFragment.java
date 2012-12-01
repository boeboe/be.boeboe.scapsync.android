package be.boeboe.scapsync.android.search;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;

/**
 * @author boeboe
 *
 */
public class SearchListFragment  extends ListFragment {
  public final static String FILTER = "filter";
  private SearchListAdapter fSearchListAdapter;
  private SearchListActivity fSearchListActivity;
  private ListView fListView;
  private IScapSyncSearchResultType fFilterType;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    fSearchListActivity = (SearchListActivity) activity;
    ArrayList<IScapSyncSearchResult> searchResults = new ArrayList<IScapSyncSearchResult>();
    fSearchListAdapter = new SearchListAdapter(getActivity(), android.R.id.list, searchResults);
    fFilterType = IScapSyncSearchResultType.fromString(getArguments().getString(FILTER));
    fSearchListAdapter.addAll(getFilteredResults(fSearchListActivity.getResults()));
    fSearchListAdapter.notifyDataSetChanged();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_search_list, container, false);
    fListView = (ListView) view.findViewById(android.R.id.list);
    fListView.setDivider(getResources().getDrawable(R.drawable.gradient));
    fListView.setDividerHeight(1);
    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    fListView.setAdapter(fSearchListAdapter);
    fSearchListAdapter.clear();
    fSearchListAdapter.addAll(getFilteredResults(fSearchListActivity.getResults()));
    fSearchListAdapter.notifyDataSetChanged();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  private ArrayList<IScapSyncSearchResult> getFilteredResults(ArrayList<IScapSyncSearchResult> results) {
    ArrayList<IScapSyncSearchResult> filteredResults = new ArrayList<IScapSyncSearchResult>();
    for (IScapSyncSearchResult result: results) {
      if (result.getType() == fFilterType) {
        filteredResults.add(result);
      }
    }
    return filteredResults;
  }
}

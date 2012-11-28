/**
 * 
 */
package be.boeboe.scapsync.android.feeds;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.interfaces.IScapSyncDailyFeed;
import be.boeboe.scapsync.rest.interfaces.IScapSyncFeed;

/**
 * @author boeboe
 *
 */
public class ChangedListFragment extends ListFragment {
  private FeedsAdapter fFeedsAdapter;
  private FeedsActivity fFeedsActivity;
  private ListView fListView;
  private IScapSyncDailyFeed fDailyFeed;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ArrayList<IScapSyncFeed> feeds = new ArrayList<IScapSyncFeed>();
    fFeedsAdapter = new FeedsAdapter(getActivity(), android.R.id.list, feeds);
    setRetainInstance(true);
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    fFeedsActivity = (FeedsActivity) activity;
    fDailyFeed = fFeedsActivity.getDailyFeed();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_feeds, container, false);
    fListView = (ListView) view.findViewById(android.R.id.list);
    fListView.setDivider(getResources().getDrawable(R.drawable.gradient));
    fListView.setDividerHeight(1);
    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    fListView.setAdapter(fFeedsAdapter);
    fFeedsAdapter.clear();
    fFeedsAdapter.addAll(Arrays.asList(fDailyFeed.getChangedItems()));
    fFeedsAdapter.notifyDataSetChanged();
  }
}

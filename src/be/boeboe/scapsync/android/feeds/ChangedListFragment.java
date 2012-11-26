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
import be.boeboe.scapsync.rest.interfaces.IScapSyncFeed;

/**
 * @author boeboe
 *
 */
public class ChangedListFragment extends ListFragment {
  private FeedsAdapter fFeedsAdapter;
  private FeedsActivity fFeedsActivity;
  private ListView fListView;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ArrayList<IScapSyncFeed> feeds = new ArrayList<IScapSyncFeed>();
    fFeedsAdapter = new FeedsAdapter(getActivity(), android.R.id.list, feeds);
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    fFeedsActivity = (FeedsActivity) activity;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //View view = inflater.inflate(R.layout.fragment_feeds, container, false);
    //ListView list = (ListView)view.findViewById(R.id.feeds_list);

    View view = inflater.inflate(R.layout.activity_feeds, container, false);
    fListView = (ListView) view.findViewById(android.R.id.list);
    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) 
  {
      super.onActivityCreated(savedInstanceState);
      fListView.setAdapter(fFeedsAdapter);
      
      System.out.println("ChangedListFragment.onActivityCreated(): " + fFeedsActivity.getDailyFeed().getChangedItems().length);
      fFeedsAdapter.addAll(Arrays.asList(fFeedsActivity.getDailyFeed().getChangedItems()));
      fFeedsAdapter.notifyDataSetChanged();
  }
}

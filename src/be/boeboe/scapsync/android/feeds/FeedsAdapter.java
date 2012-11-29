/**
 * 
 */
package be.boeboe.scapsync.android.feeds;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.interfaces.IScapSyncFeed;

/**
 * @author boeboe
 *
 */
public class FeedsAdapter extends ArrayAdapter<IScapSyncFeed> {
  private Context fContext;

  public FeedsAdapter(Context context, int textViewResourceId,
      ArrayList<IScapSyncFeed> feeds) {
    super(context, textViewResourceId, feeds);
    fContext = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View root;
    final IScapSyncFeed feed = getItem(position);

    if (null == convertView) {
      LayoutInflater inflater = ((Activity)fContext).getLayoutInflater();
      root = inflater.inflate(R.layout.row_feeds_list, parent, false);
    } else {
      root = convertView;
    }

    TextView feedType = (TextView) root.findViewById(R.id.feed_type);
    TextView feedTitle = (TextView) root.findViewById(R.id.feed_title);

    feedType.setText(feed.getDocType());
    feedTitle.setText(feed.getTitle());
    return root;
  }

}

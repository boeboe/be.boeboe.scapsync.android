/**
 * 
 */
package be.boeboe.scapsync.android.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;

/**
 * @author boeboe
 *
 */
public class SearchListAdapter extends ArrayAdapter<IScapSyncSearchResult> {
  private Context fContext;

  public SearchListAdapter(Context context, int textViewResourceId,
      ArrayList<IScapSyncSearchResult> results) {
    super(context, textViewResourceId, results);
    fContext = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View root;
    final IScapSyncSearchResult item = getItem(position);

    if (null == convertView) {
      LayoutInflater inflater = ((Activity)fContext).getLayoutInflater();
      root = inflater.inflate(R.layout.row_search_list, parent, false);
    } else {
      root = convertView;
    }

    TextView id = (TextView) root.findViewById(R.id.result_id);
    TextView titleText = (TextView) root.findViewById(R.id.result_title_text);
    
    ImageView navigateNext = (ImageView) root.findViewById(R.id.navigation_next);
    navigateNext.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        System.out.println("Clicked item '" + item.getId() + "' with URL " +  item.getUrl());
      }
    }); 

    id.setText(item.getId());
    titleText.setText(item.getSummaryText());

    return root;
  }
}

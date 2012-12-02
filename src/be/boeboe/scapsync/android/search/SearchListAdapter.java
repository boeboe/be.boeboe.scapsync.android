/**
 * 
 */
package be.boeboe.scapsync.android.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResultType;

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
        if (item.getType().equals(IScapSyncSearchResultType.TYPE_CVE)) {
          Intent cveDetailsIntent = new Intent(fContext.getApplicationContext(), CveDetailsActivity.class);
          cveDetailsIntent.putExtra(CveDetailsActivity.CVE_ID, item.getId());
          fContext.startActivity(cveDetailsIntent);
        }
      }
    }); 

    id.setText(item.getId());
    Spanned markedUpTitle = Html.fromHtml(item.getTitleText());
    titleText.setText(markedUpTitle);
    return root;
  }
}

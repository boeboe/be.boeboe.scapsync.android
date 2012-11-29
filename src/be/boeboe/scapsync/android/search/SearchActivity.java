package be.boeboe.scapsync.android.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import be.boeboe.scapsync.android.R;

public class SearchActivity extends Activity {
  private ImageView fSearchButton;
  private ImageView fResetButton;
  private TextView fSearchTerm;

  private RadioGroup fFilterGroup;
  private int fFilterSelected;
  

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    fSearchButton = (ImageView) findViewById(R.id.search_button);
    fResetButton = (ImageView) findViewById(R.id.reset_button);
    fSearchTerm = (TextView) findViewById(R.id.search_term);

    fFilterGroup = (RadioGroup) findViewById(R.id.filter_select);
    fFilterSelected = fFilterGroup.getCheckedRadioButtonId();
    
    fFilterGroup.setOnCheckedChangeListener(filterSelectedListener);
    
    fSearchButton.setOnClickListener(searchButtonListener);
    fResetButton.setOnClickListener(resetButtonListener);
    fSearchTerm.addTextChangedListener(textWatcher);
  }

  @Override
  protected void onResume() {
    super.onResume();
    updateUI();
  }

  private void updateUI() {
    String searchTermText = fSearchTerm.getText().toString();
    if (TextUtils.isEmpty(searchTermText)) {
      fSearchButton.setEnabled(false);
    } else {
      fSearchButton.setEnabled(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_search, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private OnCheckedChangeListener filterSelectedListener = new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
      fFilterSelected = checkedId;
    }
  };

  private OnClickListener searchButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent searchListIntent = new Intent(getApplicationContext(), SearchListActivity.class);
      searchListIntent.putExtra(SearchListActivity.SEARCH_TERM, fSearchTerm.getText().toString());
      searchListIntent.putExtra(SearchListActivity.SEARCH_FILTER, getFilterName(fFilterSelected));
      startActivity(searchListIntent);
    }
  };

  private OnClickListener resetButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      fSearchTerm.setText("");
      fFilterGroup.check(R.id.filter_all);
    }
  };
  
  private final TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
      updateUI();
    }
  };
  
  private String getFilterName(int resourceId) {
    switch (resourceId) {
    case R.id.filter_all:
      return SearchListActivity.SEARCH_FILTER_ALL;
    case R.id.filter_cpe:
      return SearchListActivity.SEARCH_FILTER_CPE;
    case R.id.filter_cve:
      return SearchListActivity.SEARCH_FILTER_CVE;
    case R.id.filter_cwe:
      return SearchListActivity.SEARCH_FILTER_CWE;
    default:
      return SearchListActivity.SEARCH_FILTER_ALL;
    }
  }
}

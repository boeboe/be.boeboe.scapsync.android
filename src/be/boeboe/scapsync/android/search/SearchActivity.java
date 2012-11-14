package be.boeboe.scapsync.android.search;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import be.boeboe.scapsync.android.R;
import be.boeboe.scapsync.rest.ScapSyncSearcher;
import be.boeboe.scapsync.rest.interfaces.IScapSyncSearchResult;

public class SearchActivity extends Activity {
  private Button fSearchButton;
  private Button fResetButton;
  private TextView fSearchTerm;
  private TextView fSearchResult;
  
  private OnClickListener searchButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      System.out.println("Going to search for: " + fSearchTerm.getText());
      new SearchTask().execute(fSearchTerm.getText().toString());
    }
  };

  private OnClickListener resetButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      fSearchTerm.setText("");
      fSearchResult.setText("<no results yet>");
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

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    fSearchButton = (Button) findViewById(R.id.search_button);
    fResetButton = (Button) findViewById(R.id.reset_button);
    fSearchTerm = (TextView) findViewById(R.id.search_term);
    fSearchResult = (TextView) findViewById(R.id.search_result);
    
    fSearchButton.setOnClickListener(searchButtonListener);
    fResetButton.setOnClickListener(resetButtonListener);
    fSearchTerm.addTextChangedListener(textWatcher);
    
    fSearchResult.setText("<no results yet>");
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

  private class SearchTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {
      String resultStr = "";
      if (params != null && params.length > 0 && params[0] != null) {
        String searchItem = params[0];
        System.out.println("Going to REST Search for " + searchItem);
        ScapSyncSearcher searcher = new ScapSyncSearcher();
        IScapSyncSearchResult[] result = searcher.searchAll(searchItem);
        System.out.println("REST Search result: " + result[0].getSummaryText());
        resultStr = result[0].toString();
      }
      return resultStr;
    }
    
    protected void onPostExecute(String result) {
      fSearchResult.setText(result);
    }
  }
}

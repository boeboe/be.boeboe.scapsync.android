package be.boeboe.scapsync.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasicSearchActivity extends Activity {
  private ImageButton fSearchButton;
  private TextView fSearchTerm;
  
  private OnClickListener searchButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      System.out.println("Going to search for: " + fSearchTerm.getText());
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
    setContentView(R.layout.activity_basic_search);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    
    fSearchButton = (ImageButton) findViewById(R.id.do_basic_search);
    fSearchTerm = (TextView) findViewById(R.id.search_term);
    
    fSearchButton.setOnClickListener(searchButtonListener);
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
    getMenuInflater().inflate(R.menu.activity_basic_search, menu);
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

}

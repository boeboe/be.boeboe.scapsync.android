package be.boeboe.scapsync.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
  
  private Button fSearchButton;
  private Button fStatsButton;
  private Button fProfilesButton;
  private Button fFeedsButton;
  
  private OnClickListener searchButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }
  };

  
  private OnClickListener statsButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(getApplicationContext(), StatsActivity.class));
    }
  };
  
  private OnClickListener profilesButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(getApplicationContext(), ProfilesActivity.class));
    }
  };
  
  private OnClickListener feedsButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(getApplicationContext(), FeedsActivity.class));
    }
  };
  
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    fSearchButton = (Button) findViewById(R.id.search_button);
    fStatsButton = (Button) findViewById(R.id.stats_button);
    fProfilesButton = (Button) findViewById(R.id.profiles_button);
    fFeedsButton = (Button) findViewById(R.id.feeds_button);
    
    fSearchButton.setOnClickListener(searchButtonListener);
    fStatsButton.setOnClickListener(statsButtonListener);
    fProfilesButton.setOnClickListener(profilesButtonListener);
    fFeedsButton.setOnClickListener(feedsButtonListener);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }
}

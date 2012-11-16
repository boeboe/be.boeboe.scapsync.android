package be.boeboe.scapsync.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import be.boeboe.scapsync.android.feeds.FeedsActivity;
import be.boeboe.scapsync.android.profiles.ProfilesActivity;
import be.boeboe.scapsync.android.search.SearchActivity;
import be.boeboe.scapsync.android.stats.StatsActivity;

public class MainActivity extends Activity {
  
  private LinearLayout fSearchButton;
  private LinearLayout fStatsButton;
  private LinearLayout fProfilesButton;
  private LinearLayout fFeedsButton;
  
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
    
    fSearchButton = (LinearLayout) findViewById(R.id.search_button);
    fStatsButton = (LinearLayout) findViewById(R.id.stats_button);
    fProfilesButton = (LinearLayout) findViewById(R.id.profiles_button);
    fFeedsButton = (LinearLayout) findViewById(R.id.feeds_button);
    
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

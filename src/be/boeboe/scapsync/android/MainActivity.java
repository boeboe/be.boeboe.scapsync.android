package be.boeboe.scapsync.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
  
  private Button fBasicSearchButton;
  private Button fAdvancedSearchButton;

  private OnClickListener basicSearchButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      startActivity(new Intent(getApplicationContext(), BasicSearchActivity.class));
    }
  };
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    fBasicSearchButton = (Button) findViewById(R.id.jump_cvs_basic_search);
    fAdvancedSearchButton = (Button) findViewById(R.id.jump_cvs_advanced_search);
    
    fBasicSearchButton.setOnClickListener(basicSearchButtonListener);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }
}

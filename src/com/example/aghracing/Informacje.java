package com.example.aghracing;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Informacje extends Activity {
private TextView mTextView;
private ImageSpan mImageSpan;
private Drawable mImageView;
	protected void onCreate(Bundle savedInstanceState){
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.informacje2);
	 getActionBar().setDisplayHomeAsUpEnabled(true);
	 
 }
	public boolean onCreateOptionsMenu(Menu menu) {
		   
	    return true;
	  }

	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    if (item.getItemId() == android.R.id.home) {
	      finish();
	      return true;
	    }
	    return super.onOptionsItemSelected(item);
	  }
}

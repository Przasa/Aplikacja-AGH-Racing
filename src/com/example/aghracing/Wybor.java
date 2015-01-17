package com.example.aghracing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.transition.Visibility;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Wybor extends Activity {
	Button b1, b2,b3,b4;
	TextView tv;

	public boolean koniec = false;
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wybor);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		
		tv = (TextView) findViewById(R.id.textView1);
		Typeface tp = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
		tv.setTypeface(tp);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencja = new Intent(getApplicationContext(), Lista.class);
				startActivity(intencja);			
			}
		});
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencja = new Intent(getApplicationContext(), Wizytowka.class);
				startActivity(intencja);
			}});
		b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencja = new Intent(getApplicationContext(),Informacje.class);
				startActivity(intencja);
			}});
		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencja = new Intent(getApplicationContext(),RacingGlowa.class);
				startActivity(intencja);
			}});
		
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

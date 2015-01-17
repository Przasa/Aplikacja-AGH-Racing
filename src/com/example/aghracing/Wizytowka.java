package com.example.aghracing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.connection.BeaconConnection;

public class Wizytowka extends Activity {
	private boolean mContentLoaded;
    private ImageButton mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;
    private TextView mTekstA1,mTekstA2,mTekstB1,mTekstB2;
    private Button b1;
    private int brama =1;
    
    private BeaconManager beaconManager;
    private BeaconConnection connection;
    private static final int REQUEST_ENABLE_BT = 1234;
    private static final String TAG = Wizytowka.class.getSimpleName();
	private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
	private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId",
			ESTIMOTE_PROXIMITY_UUID, null, null);

	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wizytowka_1_6);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mTekstA1 = (TextView) findViewById(R.id.textViewA1);
		mTekstA2 = (TextView) findViewById(R.id.textViewA2);
		mTekstB1 = (TextView) findViewById(R.id.textViewB1);
		mTekstB2 = (TextView) findViewById(R.id.textViewB2);
		mContentView = (ImageButton) findViewById(R.id.imageButton1);
        mLoadingView = findViewById(R.id.loading_spinner);
        b1 = (Button) findViewById(R.id.button1);
        b1.setVisibility(View.GONE);

        // Initially hide the content view.
        mContentView.setVisibility(View.GONE);
        mTekstB1.setVisibility(View.GONE);
        mTekstB2.setVisibility(View.GONE);
        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        mContentView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencja = new Intent(getApplicationContext(),Szczegoly1.class);
				startActivity(intencja);
				
			}});
        /*b1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 mContentLoaded = !mContentLoaded;
	             showContentOrLoadingIndicator(mContentLoaded);
	             
			}}); */
        
        beaconManager = new BeaconManager(this);
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);        
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
			
			@Override
			public void onBeaconsDiscovered(Region region, final List<Beacon> beacon) {
				// TODO Auto-generated method stub
				
				
			runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Beacon mBeacon1 = beacon.get(0);
						//String mMaj1 = String.valueOf(mBeacon1.getMajor());
						//kleszcze=Integer.valueOf(mBeacon1.getMajor());
						
						switch(Integer.valueOf(mBeacon1.getMajor())) {
						case 29647:
							if(brama==1) {
								showContentOrLoadingIndicator(true);
								getActionBar().setSubtitle("");
								}
								brama=2;
							break;
						default:
						if (brama==2) {
								showContentOrLoadingIndicator(false);
								
								getActionBar().setSubtitle("Scanning...");
								getActionBar().show();
							}
								brama=1;
							break;	
						}			
					}
				}); 
			}
		});
 }
	
	
    private void showContentOrLoadingIndicator(boolean contentLoaded) {
        // Decide which view to hide and which to show.
    	
    	
        final View showView = contentLoaded ? mContentView : mLoadingView;
        final View hideView = contentLoaded ? mLoadingView : mContentView;
        final TextView showText1 = contentLoaded ? mTekstB1 : mTekstA1;
        final TextView hidetext1 = contentLoaded ? mTekstA1 : mTekstB1;
        final TextView showText2 = contentLoaded ? mTekstB2 : mTekstA2;
        final TextView hidetext2 = contentLoaded ? mTekstA2 : mTekstB2;
        
        // Set the "show" view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        // Animate the "show" view to 100% opacity, and clear any animation listener set on
        // the view. Remember that listeners are not limited to the specific animation
        // describes in the chained method calls. Listeners are set on the
        // ViewPropertyAnimator object for the view, which persists across several
        // animations.
        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
        // to GONE as an optimization step (it won't participate in layout passes, etc.)
        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
        //-------------------------------------//

        showText1.setAlpha(0f);
        showText1.setVisibility(View.VISIBLE);
        showText1.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        hidetext1.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
        showText2.setAlpha(0f);
        showText2.setVisibility(View.VISIBLE);
        showText2.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        hidetext2.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
        
    }
    
    @Override
	  protected void onDestroy() {
	    beaconManager.disconnect();
	    super.onDestroy();
	  }

	  @Override
	  protected void onStart() {
	    super.onStart();
	    // Check if device supports Bluetooth Low Energy.
	    if (!beaconManager.hasBluetooth()) {
	      Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
	      return;
	    }
	    // If Bluetooth is not enabled, let user enable it.
	    if (!beaconManager.isBluetoothEnabled()) {
	      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	      startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
	    } else {
	      connectToService();
	    }
	  }

	  @Override
	  protected void onStop() {
	  /*  try {
	      beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
	    } catch (RemoteException e) {
	      Log.d(TAG, "Error while stopping ranging", e);
	    } */
	    super.onStop();
	  }

	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_ENABLE_BT) {
	      if (resultCode == Activity.RESULT_OK) {
	        connectToService();
	      } else {
	        Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
	        getActionBar().setSubtitle("Bluetooth not enabled");
	      }
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	  }
	  
	  private void connectToService() {
	    getActionBar().setSubtitle("Scanning...");
	    //adapter.replaceWith(Collections.<Beacon>emptyList());
	    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
	      @Override
	      public void onServiceReady() {
	        try {
	          beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
	        } catch (RemoteException e) {
	          Toast.makeText(Wizytowka.this, "Cannot start ranging, something terrible happened",Toast.LENGTH_LONG).show();
	          Log.e(TAG, "Cannot start ranging", e);
	        }
	      }
	    });

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

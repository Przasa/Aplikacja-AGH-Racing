package com.example.aghracing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.connection.BeaconConnection;

public class Szczegoly1 extends Activity{
	
private String[] lista;
private ArrayList<Elementy> lista1 = new ArrayList();
private ArrayList<Elementy> lista2 = new ArrayList();
private ListView mContentView1, mContentView2;
private View mLoadingView;
private int mShortAnimationDuration;
private Animator mCurrentAnimator;
private boolean bramka = true, contentLoaded = false, mShowingBack=true;
//private int contentLoaded;
//private int contentHided;
private int mJunior = 99, mSenior=29647;
private BeaconManager beaconManager;
private BeaconConnection connection;
private static final int REQUEST_ENABLE_BT = 1234;
private static final String TAG = Wizytowka.class.getSimpleName();
//private static final String TAG = Wizytowka.class.getSimpleName();
private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId",
		ESTIMOTE_PROXIMITY_UUID, null, 52278);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detale_1_3);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	
		int mImA1 = R.drawable.just_photo1;
		int mImA2 = R.drawable.just_photo2;
		int mImA3 = R.drawable.just_photo3;
		int mImB1 = R.drawable.just_photo4;
		int mImB2 = R.drawable.just_photo5;
		int mImB3 = R.drawable.just_photo6;
		String mTekst = getResources().getString(R.string.lorem_ipsum);
		
		lista1.add(new Elementy(mImA1, mTekst));
		lista1.add(new Elementy(mImA2, mTekst));
		lista1.add(new Elementy(mImA3, mTekst));
		lista2.add(new Elementy(mImB1, mTekst));
		lista2.add(new Elementy(mImB2, mTekst));
		lista2.add(new Elementy(mImB3, mTekst));
		
		mContentView1 = (ListView) findViewById(R.id.listView1);
		mContentView2 = (ListView) findViewById(R.id.listView2);
        mLoadingView = findViewById(R.id.loading_spinner);
        mContentView1.setVisibility(View.GONE);
        mContentView2.setVisibility(View.GONE);
        
        
		MojAdapter adapter1 = new MojAdapter(this, R.layout.detale_rekord_1_1, lista1);
		MojAdapter adapter2 = new MojAdapter(this, R.layout.detale_rekord_1_1, lista2);
		mContentView1.setAdapter(adapter1);
		mContentView2.setAdapter(adapter2);
		
		mContentView1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
					ImageView ikona = (ImageView) view.findViewById(R.id.imageView1);
					int sciezka = lista1.get(position).mImageView;
					zoomImageFromThumb(ikona, sciezka);
			
			}});
		mContentView2.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ImageView ikona = (ImageView) view.findViewById(R.id.imageView1);
				int sciezka = lista2.get(position).mImageView;
				zoomImageFromThumb(ikona, sciezka);
			}});
		// Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        beaconManager = new BeaconManager(this);
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0); 
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {		
			@Override
			public void onBeaconsDiscovered(Region arg0, final List<Beacon> beacons) {
				// TODO Auto-generated method stub

				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Beacon mBeacon = beacons.get(0);
						mJunior = Integer.valueOf(mBeacon.getMajor());
						
						if (mShowingBack==true) {
						if (mJunior==mSenior) {
							mSenior=mJunior;
						}
						else {
							if (bramka==true){
								mLoadingView.setAlpha(0f);
								mLoadingView.setVisibility(View.GONE);
								bramka=false;
							}
							switch (mJunior) {
							case 65436:
								showContentOrLoadingIndicator(true);
								break;
							case 794:
								showContentOrLoadingIndicator(false);
								break;
							}
							mSenior=mJunior;
							
						}
						}
						
						switch (Integer.valueOf((beacons.get(0)).getMajor())) {
							case 65436:
								getActionBar().setSubtitle("Silnik - bolid 2013");
								break;
							case 794:
								getActionBar().setSubtitle("Uk³ad kierowniczy - bolid 2013");
								break;
							default:
								getActionBar().setSubtitle("Searching...");
							break;
						}

					}
				});
			}
		});	
	}
	
	 private void showContentOrLoadingIndicator(boolean contentLoaded) {
	        // Decide which view to hide and which to show.
	    	
	    	
	        final ListView showView = contentLoaded ? mContentView1 : mContentView2;
	        final ListView hideView = contentLoaded ? mContentView2 : mContentView1;

	        
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
	       
	        
	    }
	private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);
        
        Animator se2 = new AnimatorSet();
        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel, back to their
                // original values.
                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
	
	public class MojAdapter extends ArrayAdapter<Elementy>{
		Context context;
		int layoutId;
		private ArrayList<Elementy> objects = null;
		public MojAdapter(Context context, int layoutId, ArrayList<Elementy> objects) {
			super(context, layoutId, objects);
			this.context = context;
			this.layoutId = layoutId;
			this.objects = objects;
			// TODO Auto-generated constructor stub
		}
		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			View mRowView;
			LayoutInflater mInflater = ((Activity)context).getLayoutInflater();
			mRowView = mInflater.inflate(layoutId, parent, false);
			ImageView im1 = (ImageView) mRowView.findViewById(R.id.imageView1);
			TextView t1 = (TextView) mRowView.findViewById(R.id.textView1);
			im1.setImageResource(objects.get(position).mImageView);
			t1.setText(objects.get(position).mTekst);
			return mRowView;
			
		}
		
	}
	
	private class Elementy{

		private int mImageView;
		private String mTekst;
		
		public Elementy(int mImageView, String mTekst) {
			super();
			this.mImageView = mImageView;
			this.mTekst = mTekst;
		}
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
	//    try {
	//      beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
	//    } catch (RemoteException e) {
	//      Log.d(TAG, "Error while stopping ranging", e);
	//    }
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
	    //getActionBar().setSubtitle("Scanning...");
	    //adapter.replaceWith(Collections.<Beacon>emptyList());
	    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
	      @Override
	      public void onServiceReady() {
	        try {
	          beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
	        } catch (RemoteException e) {
	          Toast.makeText(Szczegoly1.this, "Cannot start ranging, something terrible happened",Toast.LENGTH_LONG).show();
	          Log.e(TAG, "Cannot start ranging", e);
	        }
	      }
	    });

	  }
	  public boolean onCreateOptionsMenu(Menu menu) {

	        
		    //return true;
		    getMenuInflater().inflate(R.menu.scan_menu, menu);
		    
		    MenuItem refreshItem = menu.findItem(R.id.refresh);
	    	refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
		    refreshItem.setVisible(
		    		mShowingBack
		    		  ? true
		    		  : false);
		    MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
		                mShowingBack
		                        ? R.string.skan_stop
		                        : R.string.skan_wzon);
		        item.setIcon(mShowingBack
		                ? R.drawable.ikona_pause2_ok
		                : R.drawable.ikona_next_g3);
		        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		        
		    
		    return true;
		  }

		  @Override
		  public boolean onOptionsItemSelected(MenuItem item) {
			  switch (item.getItemId()) {
	            case android.R.id.home:
	                // Navigate "up" the demo structure to the launchpad activity.
	                // See http://developer.android.com/design/patterns/navigation.html for more.
	                NavUtils.navigateUpTo(this, new Intent(this, RacingGlowa.class));
	                return true;

	            case R.id.action_flip:
	            	mShowingBack = !mShowingBack;
	            	invalidateOptionsMenu();
	                return true;
		    }
		    return super.onOptionsItemSelected(item);
		  }

}

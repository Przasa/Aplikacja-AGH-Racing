package com.example.aghracing;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class RacingGlowa extends Activity 
	implements FragmentManager.OnBackStackChangedListener {
	/**
     * A handler object, used for deferring UI operations.
     */
    private Handler mHandler = new Handler();
    private int i=1;

    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        startService(new Intent(this, EstimoteService.class));
        
        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info").
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Add either a "photo" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item0 = menu.add(Menu.NONE, R.id.action_skip, Menu.NONE,R.string.action_previous_card);
        item0.setIcon(R.drawable.ikona_skip2ok);
        item0.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        MenuItem item = menu.add(Menu.NONE, R.id.action_backflip, Menu.NONE,R.string.action_previous_card);
        item.setIcon(R.drawable.ikona_previous_g3);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        MenuItem item2 = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,R.string.action_next_card);
        item2.setIcon(R.drawable.ikona_next_g3);
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        
          /*  case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                Intent intencja2 = new Intent(getApplicationContext(),Wybor.class);
                startActivity(intencja2);
            	//NavUtils.navigateUpTo(this, new Intent(this, Wybor.class));
                return true; */
	        case R.id.action_skip:
	        	Intent intencja0 = new Intent(getBaseContext(),Wybor.class);
	    		startActivity(intencja0);
	    		return true;

            case R.id.action_flip:
            	if (i>4) {i=i-4;}
            	i++;
            	if(i==5) {
            		Intent intencja = new Intent(getBaseContext(),Wybor.class);
            		startActivity(intencja);
            		}
            	else flipCard();
                return true;
            case R.id.action_backflip:
            	if(i<5) {i=i+4;}
            	i--;
            	if(i==4) {i=8;}
                flipCard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
    	
    	switch(i) {
    	case 1:
    		getFragmentManager()
            .beginTransaction()
            
            .setCustomAnimations(
            //		 R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                     R.animator.card_flip_left_in, R.animator.card_flip_left_out)
            .replace(R.id.container, new CardFrontFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 2:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	 R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                     R.animator.card_flip_left_in, R.animator.card_flip_left_out)
            .replace(R.id.container, new CardBackFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 3:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	 R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                     R.animator.card_flip_left_in, R.animator.card_flip_left_out)
            .replace(R.id.container, new CardThirdFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 4:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            		R.animator.card_flip_left_in, R.animator.card_flip_left_out)
            	//	R.animator.card_flip_right_in, R.animator.card_flip_right_out)
            .replace(R.id.container, new CardFourthFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 5:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	R.animator.card_flip_left_in, R.animator.card_flip_left_out, 
            		R.animator.card_flip_right_in, R.animator.card_flip_right_out)
            .replace(R.id.container, new CardFrontFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 6:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	R.animator.card_flip_left_in, R.animator.card_flip_left_out, 
            		R.animator.card_flip_right_in, R.animator.card_flip_right_out)
            .replace(R.id.container, new CardBackFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 7:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	R.animator.card_flip_left_in, R.animator.card_flip_left_out, 
            		R.animator.card_flip_right_in, R.animator.card_flip_right_out)
            .replace(R.id.container, new CardThirdFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	case 8:
    		getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(
            	//	R.animator.card_flip_left_in, R.animator.card_flip_left_out, 
            		R.animator.card_flip_right_in, R.animator.card_flip_right_out)
            .replace(R.id.container, new CardFourthFragment())
            //.addToBackStack(null)
            .commit();
    		break;
    	}
        // Flip to the back.
    	
    	/*
        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit(); 
                */

        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
     /*   mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });*/
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu(); 
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dycha2, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.jopek, container, false);
        }
    }
    public static class CardThirdFragment extends Fragment {
        public CardThirdFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dama, container, false);
        }
    }
    public static class CardFourthFragment extends Fragment {
        public CardFourthFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.krol, container, false);
        }
    }
    @Override
	protected void onStart() {
		super.onStart();
		   //Intent startServiceIntent = new Intent(this, EstimoteReceiver.class);
		   //this.startService(startServiceIntent);
		   //Intent startServiceIntent = new Intent(this, EstimoteService.class);
	       //this.startService(startServiceIntent);
	}

	
	  @Override
	  protected void onResume() {
	    super.onResume();
		IntentFilter filter = new IntentFilter();
	    filter.addAction("android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED");
	    this.registerReceiver(new EstimoteReceiver(), filter);
	  }
}

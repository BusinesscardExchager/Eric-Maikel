package com.example.businesscardexchager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
<<<<<<< HEAD
=======
import android.app.SearchManager;
>>>>>>> FETCH_HEAD
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.widget.Toast;
=======
import android.widget.SearchView;
>>>>>>> FETCH_HEAD

public class MainActivity extends FragmentActivity implements TabListener {

	ViewPager viewPager;
	ActionBar actionBar;
<<<<<<< HEAD
	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
=======
	SearchView searchView;
>>>>>>> FETCH_HEAD

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

		if (sharedprefs.contains("achtergrondkleur")) {
			int color = sharedprefs.getInt("achtergrondkleur", 0);
			Log.d("EDR", "test");
			View layout = findViewById(R.id.fragmentA);
			Log.d("EDR", "" + color);
			if (color != 2131034125) {
				layout.getRootView().setBackgroundColor(color);
			}
			Log.d("EDR", "test");
		}

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
				// Log.d("EDR", "onPageSelected at " + " position " + arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// Log.d("EDR", "onPageScrolled at " + " position " + arg0 +
				// " from " + arg1+ " with number of pixels " + arg2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				/*
				 * if(arg0==ViewPager.SCROLL_STATE_IDLE) { Log.d("EDR",
				 * "onPageScrollStateChanged Idle"); }
				 * if(arg0==ViewPager.SCROLL_STATE_DRAGGING) { Log.d("EDR",
				 * "onPageScrollStateChanged Dragging"); }
				 * if(arg0==ViewPager.SCROLL_STATE_SETTLING) { Log.d("EDR",
				 * "onPageScrollStateChanged Settling"); }
				 */

			}
		});
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Collection");
		tab1.setTabListener(this);

		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("My card");
		tab2.setTabListener(this);

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		SearchManager searchManager =
		           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		    searchView =
		            (SearchView) menu.findItem(R.id.action_search).getActionView();
		    searchView.setSearchableInfo(
		            searchManager.getSearchableInfo(getComponentName()));
		    
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void onBackPressed() {
		
	    if (!searchView.isIconified()) {
	        searchView.setIconified(true);
	    } else {
	        super.onBackPressed();
	    }
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_send:
			openSend();
			return true;
		case R.id.action_search:
			openSearch();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openSearch() {
		// TODO Auto-generated method stub

	}

	private void openSend() {
		// TODO Auto-generated method stub

	}

	private void openSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void editMyCard(View view) {
		Intent intent = new Intent(this, EditCardActivity.class);
		startActivity(intent);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// Log.d("EDR", "onTabReselected at " + " position " + tab.getPosition()
		// + " name " + tab.getText());

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// Log.d("EDR", "onTabSelected at " + " position " + tab.getPosition()
		// + " name " + tab.getText());
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// Log.d("EDR", "onTabUnselected at " + " position " + tab.getPosition()
		// + " name " + tab.getText());
	}
}

class MyAdapter extends FragmentPagerAdapter {
	public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		if (arg0 == 0) {
			fragment = new FragmentA();
		}
		if (arg0 == 1) {
			fragment = new FragmentB();

		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
}
	


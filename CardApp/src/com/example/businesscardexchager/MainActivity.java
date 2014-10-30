package com.example.businesscardexchager;

import java.util.List;

import com.example.businesscardexchager.R.color;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.ColorRes;
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
import android.webkit.WebView.FindListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.SearchView;

public class MainActivity extends FragmentActivity implements TabListener {

	ViewPager viewPager;
	ActionBar actionBar;
	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	SearchView searchView;
	boolean selectedB;
	ActionBar.Tab tab1;
	ActionBar.Tab tab2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
				if (arg0 == 1) {
					selectedB = true;
				} else {
					selectedB = false;
				}
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

		tab1 = actionBar.newTab();
		tab1.setText("Collection");
		tab1.setTabListener(this);

		tab2 = actionBar.newTab();
		tab2.setText("My card");
		tab2.setTabListener(this);

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onBackPressed() {

		if (!searchView.isIconified()) {
			searchView.setIconified(true);
		} else {
			if (selectedB) {
				viewPager.setCurrentItem(tab1.getPosition());
			} else {
				super.onBackPressed();
			}
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
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	public void setAchtergrond(View layout) {
		if (sharedprefs.contains("achtergrondkleur")) {
			String color = sharedprefs.getString("achtergrondkleur", "");

			if (color.equals("Rood")) {
				layout.setBackgroundColor(getResources().getColor(R.color.red));
			} else if (color.equals("Blauw")) {
				layout.setBackgroundColor(getResources().getColor(R.color.blue));
			} else if (color.equals("Groen")) {
				layout.setBackgroundColor(getResources()
						.getColor(R.color.green));
			}
		}
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

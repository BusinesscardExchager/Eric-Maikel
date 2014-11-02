package com.example.businesscardexchager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements TabListener {

	/** Globale variabelen */
	ViewPager viewPager;
	ActionBar actionBar;
	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	SearchView searchView;
	boolean selectedB;
	ActionBar.Tab tab1;
	ActionBar.Tab tab2;
	private CardProvider cp;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		cp = new CardProvider(this);

		/** De ViewPager wordt gebruikt om de tabs en de overgang daar tussen */
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			/** Als er op een tab geklikt wordt */
			@Override
			public void onPageSelected(int arg0) {
				// Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
				if (arg0 == 1) {
					selectedB = true;
				} else {
					selectedB = false;
				}
			}

			/**
			 * Als er horizontaal gescrolt wordt, standaard methode geen
			 * invulling
			 */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// Auto-generated method stub
			}

			/** Als er gescrolt is, standaard methode, geen invulling */
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// Auto-generated method stub

			}
		});

		/**
		 * Wordt gebruikt om een toast weer te geven, moet op de UI thread
		 * gebeuren, wordt aangeroepen vanuit een apparte thread
		 */
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					String text = msg.getData().getString("text");
					Toast.makeText(getApplicationContext(), text,
							Toast.LENGTH_SHORT).show();

				}
			}
		};

		/** tabs en actionbar */
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

	/**
	 * Menu bar wordt gemaakt m.b.v. inflater, searchview(zoek scherm) wordt
	 * toegevoegd aan de bar
	 */
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

	/**
	 * Als er op de hardware backknop wordt kunnen er 2 acties uitgevoerd
	 * worden. Als Search actief is: 1. (Heeft voorrang als beide actief zijn)
	 * Als Fragment B actief is: 2. Anders wordt de app afgesloten. 1. Search
	 * scherm wordt niet meer gefocust en wordt teruggegaan naar het originele
	 * scherm 2. Fragment B (Tab 2) wordt teruggebracht naar fragment A (Tab 1)
	 */
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

	/**
	 * Event handler als er op een knop in de menubar gklikt wordt uitgezonderd
	 * van search, deze wordt appart geopend.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_send:
			openSend();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Wordt aangeroepen als er op send gedrukt wordt (Haalt in dit geval het
	 * json bestand van de server, in een nieuwe thread (niet ui thread)
	 */
	private void openSend() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				InputStream is = null;
				BufferedReader reader = null;
				StringBuilder stringBuilder = null;
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet hGet = new HttpGet(
							"http://athena.fhict.nl/users/i291685/jsonCard2.json");
					HttpResponse response = client.execute(hGet);

					StatusLine statusLine = response.getStatusLine();
					int statusCode = statusLine.getStatusCode();
					if (statusCode == 200) {
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						reader = new BufferedReader(new InputStreamReader(is));

						stringBuilder = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							stringBuilder.append(line);
						}
						/**
						 * InputStream en BufferedReader zijn niet meer nodig,
						 * dus close
						 */
						is.close();
						reader.close();

						/**
						 * De opgehaalde informatie wordt in een JSONObject
						 * gezet
						 */
						JSONObject jObject;
						String jsonText;
						String afbeeldingString;
						String afbeeldingString2;
						try {
							jObject = new JSONObject(stringBuilder.toString());
							String naam = jObject.getString("naam");
							jsonText = stringBuilder.toString();
							int sub = jsonText.indexOf("/9j");
							afbeeldingString = jsonText.substring(sub);
							int sub2 = afbeeldingString.indexOf("\"");
							afbeeldingString2 = afbeeldingString.substring(0,
									sub2);

							/** Card wordt gecreëerd */
							Card ontvangenCard = new Card(jObject
									.getString("bedrijf"), jObject
									.getString("naam"), jObject
									.getString("adres"), jObject
									.getString("telefoonnummer"), jObject
									.getString("functie"), jObject
									.getString("email"), afbeeldingString2,
									jObject.getString("locatie"), jObject
											.getString("reden"));

							/**
							 * Als de card nog niet in de lijst met kaarten
							 * voorkomt wordt deze toegevoegd
							 */
							if (cp.getCardByName(naam) == null) {
								cp.addCard(ontvangenCard);
							} else {
								Message message = handler.obtainMessage(0);
								Bundle bundle = new Bundle();
								bundle.putString("text",
										"Kaart is reeds toegevoegd");
								message.setData(bundle);
								handler.sendMessage(message);
							}
						} catch (JSONException ex) {
							Message message = handler.obtainMessage();
							Bundle bundle = new Bundle();
							bundle.putString("text", "Geen geldige kaart");
							message.setData(bundle);
							handler.sendMessage(message);

						} finally {
							/** Grote tekst (encoded afbeelding) leegmaken */
							jObject = null;
							jsonText = "";
							afbeeldingString = "";
							afbeeldingString2 = "";
						}

					}
				} catch (IOException ex) {
					Message message = handler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putString("text", "Geen internet verbinding");
					message.setData(bundle);
					handler.sendMessage(message);
				} finally {
					stringBuilder = null;
				}
			}
		}).start();
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
		/** Niet gebruikte methode, wel geïmplementeerd mocht het nodig zijn */
	}

	/** De geslecteerde tab wordt getoond */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		/** Niet gebruikte methode, wel geïmplementeerd mocht het nodi zijn */
	}
}

/** De my adapter is een custom adapter om de fragments te beheren */
class MyAdapter extends FragmentPagerAdapter {
	public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int arg0) {
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
		return 2;
	}
}

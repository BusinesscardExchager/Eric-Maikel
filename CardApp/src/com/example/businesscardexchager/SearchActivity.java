package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Deze activity kijkt of het een zoekactivity is, volgens de android.developer
 * site Deze activity geeft alle kaarten weer die te maken hebben met de
 * zoekopdracht
 */
public class SearchActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	MyGridAdapter adapter;
	GridView grid;
	CardProvider cardProvider;
	List<Card> cards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Search Result");

		cards = new ArrayList<Card>();
		sharedprefs = getApplication().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);
		setAchtergrond();
		handleIntent(getIntent());

		if (cards.size() > 0) {
			grid = (GridView) findViewById(R.id.searchCollection);
			adapter = new MyGridAdapter(this, cards);
			grid.setAdapter(adapter);
			grid.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int position, long id) {
					Intent intent = new Intent(getApplicationContext(),
							DetailActivity.class);
					Card card = cards.get(position);
					intent.putExtra("card", card);
					//intent.putExtra("position", position);
					startActivity(intent);
				}
			});
		} else {
			TextView tvNoCards = (TextView) findViewById(R.id.noCards);
			tvNoCards.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	}

	/** activity kijkt of het een search activity is */
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			TextView tv = (TextView) findViewById(R.id.querySearch);
			tv.setText("Searched on: " + query);
			cards = findCards(query);
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	private void setAchtergrond() {
		View view = findViewById(R.id.activity_search);
		if (sharedprefs != null) {
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				if (color.equals("Rood")) {
					view.setBackgroundResource(R.drawable.bg_one);
				} else if (color.equals("Blauw")) {
					view.setBackgroundResource(R.drawable.bg_two);
				} else if (color.equals("Groen")) {
					view.setBackgroundResource(R.drawable.bg_three);
				}
			}
		}
	}

	private List<Card> findCards(String query) {
		List<Card> foundCards = new ArrayList<Card>();
		cardProvider = new CardProvider(this);
		for (Card card : cardProvider.getCards()) {
			if (card.getNaam().toLowerCase(Locale.US)
					.contains(query.toLowerCase(Locale.US))
					|| card.getBedrijf().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getFunctie().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getAdres().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getTelnummer().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getEmail().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getLocatie().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))
					|| card.getReden().toLowerCase(Locale.US)
							.contains(query.toLowerCase(Locale.US))) {
				foundCards.add(card);
			}
		}
		TextView tvNumberCards = (TextView) findViewById(R.id.numberCards);
		tvNumberCards.setText("Number of Cards found: " + foundCards.size());

		return foundCards;
	}
}

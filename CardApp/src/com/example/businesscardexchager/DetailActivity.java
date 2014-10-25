package com.example.businesscardexchager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		sharedprefs = getApplication().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);

		View view = findViewById(R.id.activity_detail);
		if (sharedprefs != null) {
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				if (color.equals("Rood")) {
					view.setBackgroundColor(getResources()
							.getColor(R.color.red));
				} else if (color.equals("Blauw")) {
					view.setBackgroundColor(getResources().getColor(
							R.color.blue));
				} else if (color.equals("Groen")) {
					view.setBackgroundColor(getResources().getColor(
							R.color.green));
				}
			}
		}

		Card card = getIntent().getParcelableExtra("card");
		TextView tvBedrijf = (TextView) findViewById(R.id.tvBedrijf);
		TextView tvNaam = (TextView) findViewById(R.id.tvNaam);
		TextView tvAdres = (TextView) findViewById(R.id.tvAdres);
		TextView tvTelefoonnummer = (TextView) findViewById(R.id.tvTelefoonnummer);
		TextView tvFunctie = (TextView) findViewById(R.id.tvFunctie);

		if (card != null) {
			tvBedrijf.setText(card.getBedrijf());
			tvNaam.setText(card.getNaam());
			tvAdres.setText(card.getAdres());
			tvTelefoonnummer.setText(card.getTelnummer());
			tvFunctie.setText(card.getFunctie());
		}
	}
}

package com.example.businesscardexchager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EditCardActivity extends Activity {

	SharedPreferences sharedprefs;
	public static final String MY_PREFERENCES = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_card);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

		if (sharedprefs.contains("achtergrondkleur")) {
			int color = sharedprefs.getInt("achtergrondkleur", 0);
			View layout = findViewById(R.id.activity_edit_card);
			layout.setBackgroundColor(color);
		}

	}
}

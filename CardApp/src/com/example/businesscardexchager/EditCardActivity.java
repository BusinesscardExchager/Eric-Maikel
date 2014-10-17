package com.example.businesscardexchager;

import com.example.businesscardexchager.R.id;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditCardActivity extends Activity {

	SharedPreferences sharedprefs;
	public static final String MY_PREFERENCES = "MyPrefs";

	@Override
	public void onResume() 
	{
		super.onResume();
		EditText etNaam = (EditText) findViewById(id.naamContact);
		EditText etAdres = (EditText) findViewById(id.adresContact);
		EditText etBedrijf = (EditText) findViewById(id.BedrijfContact);
		EditText etTelefoon = (EditText) findViewById(id.telefoonContact);
		if(sharedprefs.contains("naamCard"))
		{
			etNaam.setText(sharedprefs.getString("naamCard", "Naam"));
		}
		if(sharedprefs.contains("AdresCard"))
		{
			etAdres.setText(sharedprefs.getString("AdresCard", "Adres"));
		}
		if(sharedprefs.contains("bedrijfCard"))
		{
			etBedrijf.setText(sharedprefs.getString("bedrijfCard", "Bedrijf"));
		}
		if(sharedprefs.contains("telefoonCard"))
		{
			etTelefoon.setText(sharedprefs.getString("telefoonCard", "Nummer"));
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_card);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				View layout = findViewById(R.id.activity_edit_card);

				if (color.equals("Rood")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.red));
				} else if (color.equals("Blauw")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.blue));
				} else if (color.equals("Groen")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.green));
				}

			}

	}
	public void SaveMyCard(View view) {
		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedprefs.edit();
		EditText etBedrijf = (EditText) findViewById(id.BedrijfContact);
		editor.putString("bedrijfCard", etBedrijf.getText().toString());
		EditText etNaam = (EditText) findViewById(id.naamContact);
		editor.putString("naamCard", etNaam.getText().toString());
		EditText etAdres = (EditText) findViewById(id.adresContact);
		editor.putString("AdresCard", etAdres.getText().toString());
		EditText etTelefoon = (EditText) findViewById(id.telefoonContact);
		editor.putString("telefoonCard", etTelefoon.getText().toString());
		editor.commit();
		finish();
	}
}

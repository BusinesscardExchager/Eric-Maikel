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
		EditText et = (EditText) findViewById(id.naamContact);
		if(sharedprefs.contains("naamCard"))
		{
			et.setText(sharedprefs.getString("naamCard", "Naam"));
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
					Log.d("EDR", "Edit Rood");
				} else if (color.equals("Blauw")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.blue));
					Log.d("EDR", "Edit Blauw");
				} else if (color.equals("Groen")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.green));
					Log.d("EDR", "Edit Groen");
				}

			}

	}
	public void SaveMyCard(View view) {
		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedprefs.edit();
		EditText et = (EditText) findViewById(id.naamContact);
		editor.putString("naamCard", et.getText().toString());
		editor.commit();
		finish();
	}
}

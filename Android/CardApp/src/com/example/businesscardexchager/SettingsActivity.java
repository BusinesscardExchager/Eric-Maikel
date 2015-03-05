package com.example.businesscardexchager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";

	SharedPreferences sharedprefs;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		sharedprefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

		/** Kijkt of er een achtergrondkleur in de sharedpreferences zit
		 * zo ja, dan wordt de achtergrond kleur veranderd */
		if (sharedprefs.contains("achtergrondkleur")) {
			String color = sharedprefs.getString("achtergrondkleur", "");
			View imageButton = findViewById(R.id.imageView);
			View root = imageButton.getRootView();

			if (color.equals("Rood")) {
				root.setBackgroundResource(R.drawable.bg_one);
				//root.setBackgroundColor(getResources().getColor(R.color.red));
				imageButton.setBackgroundResource(R.drawable.bg_one);
			} else if (color.equals("Blauw")) {
				root.setBackgroundResource(R.drawable.bg_two);
				imageButton.setBackgroundResource(R.drawable.bg_two);
			} else if (color.equals("Groen")) {
				root.setBackgroundResource(R.drawable.bg_three);
				imageButton.setBackgroundResource(R.drawable.bg_three);
			}
		}
	}

	/** toont en dialog met 3 opties om de kleur te veranderen van de achtergrond */
	public void setBackground(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.chooseColor).setItems(R.array.color_array,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						View imageButton = findViewById(R.id.imageView);
						Editor editor = sharedprefs.edit();
						View settings = findViewById(R.id.activity_settings);

						switch (which) {
						case 0:
							settings.setBackgroundResource(R.drawable.bg_one);
							imageButton.setBackgroundResource(R.drawable.bg_one);
							
							editor.putString("achtergrondkleur", "Rood");
							editor.commit();
							break;

						case 1:
							settings.setBackgroundResource(R.drawable.bg_two);
							imageButton.setBackgroundResource(R.drawable.bg_two);

							editor.putString("achtergrondkleur", "Blauw");
							editor.commit();
							break;

						case 2:
							settings.setBackgroundResource(R.drawable.bg_three);
							imageButton.setBackgroundResource(R.drawable.bg_three);

							editor.putString("achtergrondkleur", "Groen");
							editor.commit();
							break;
						}

					}
				});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}

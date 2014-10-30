package com.example.businesscardexchager;

import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification.Style;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SettingsActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";

	SharedPreferences sharedprefs;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		sharedprefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

		if (sharedprefs.contains("achtergrondkleur")) {
			String color = sharedprefs.getString("achtergrondkleur", "");
			View imageButton = findViewById(R.id.imageView);
			View root = imageButton.getRootView();

			if (color.equals("Rood")) {
				root.setBackgroundColor(getResources().getColor(R.color.red));
				imageButton.setBackgroundColor(getResources().getColor(
						R.color.red));
			} else if (color.equals("Blauw")) {
				root.setBackgroundColor(getResources().getColor(R.color.blue));
				imageButton.setBackgroundColor(getResources().getColor(
						R.color.blue));
			} else if (color.equals("Groen")) {
				root.setBackgroundColor(getResources().getColor(R.color.green));
				imageButton.setBackgroundColor(getResources().getColor(
						R.color.green));
			}

		}

	}

	public void setBackground(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.chooseColor).setItems(R.array.color_array,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						View imageButton = findViewById(R.id.imageView);
						View root = imageButton.getRootView();
						Editor editor = sharedprefs.edit();
						View settings = findViewById(R.id.activity_settings);

						switch (which) {
						case 0:
							settings.setBackgroundColor(getResources().getColor(R.color.red));
							imageButton.setBackgroundColor(getResources().getColor(R.color.red));
							
							editor.putString("achtergrondkleur", "Rood");
							editor.commit();
							break;

						case 1:
							settings.setBackgroundColor(getResources().getColor(R.color.blue));
							imageButton.setBackgroundColor(getResources().getColor(R.color.blue));

							editor.putString("achtergrondkleur", "Blauw");
							editor.commit();
							break;

						case 2:
							settings.setBackgroundColor(getResources().getColor(R.color.green));
							imageButton.setBackgroundColor(getResources().getColor(R.color.green));

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

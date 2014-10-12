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
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SettingsActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";
	
	SharedPreferences sharedPrefs;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		sharedPrefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
	}
	
	public void setBackground(View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.chooseColor).setItems(R.array.color_array, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				View imageButton = findViewById(R.id.imageButton);
				View root = imageButton.getRootView();
				Editor editor = sharedPrefs.edit();
				
				switch(which)
				{
				case 0:
					
					View settings = findViewById(R.id.activity_settings);
					settings.setBackgroundColor(getResources().getColor(R.color.red));
					
					/*root.setBackgroundColor(getResources().getColor(R.color.red));
					imageButton.setBackgroundColor(getResources().getColor(R.color.red));
					getApplication().setTheme(R.style.Red);*/
					
					editor.putInt("achtergrondkleur", -65536);
					editor.commit();
					break;
					
				case 1:
					root.setBackgroundColor(getResources().getColor(R.color.blue));
					imageButton.setBackgroundColor(getResources().getColor(R.color.blue));
					getApplication().setTheme(R.style.Blue);
					
					editor.putInt("achtergrondkleur", R.color.blue);
					editor.commit();
					break;
					
				case 2:
					root.setBackgroundColor(getResources().getColor(R.color.green));
					imageButton.setBackgroundColor(getResources().getColor(R.color.green));
					getApplication().setTheme(R.style.Green);
					
					editor.putInt("achtergrondkleur", R.color.green);
					editor.commit();
					break;
				}
				
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}

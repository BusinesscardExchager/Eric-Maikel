package com.example.pokerapp;

import java.util.Locale;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}
	
	public void btnSave_Click(View view)
	{
		this.finish();
		Intent i = getApplicationContext().getPackageManager()
				 .getLaunchIntentForPackage(getApplicationContext().getPackageName() );

				 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
				 startActivity(i);
	}
	
	public void btnLanguage_Click(View view)
	{
		Button b = (Button)findViewById(R.id.btnLanguage);
		 Locale locale = null;  
		 Log.i("EDR", (String) b.getText());
		    if(b.getText().toString().equals("Language")) {
		        locale = new Locale("nl");
		        Toast.makeText(getBaseContext(), "Taal is nederlands",
						Toast.LENGTH_LONG).show();
		    } else if(b.getText().toString().equals("Taal")) {
		        locale = new Locale("en_US");
		        Toast.makeText(getBaseContext(), "Language is english",
						Toast.LENGTH_LONG).show();
		    }
		 Locale.setDefault(locale);
		 Configuration config = new Configuration();
		 config.locale = locale;
		 getApplicationContext().getResources().updateConfiguration(config, null);
		 
		 Intent intent = getIntent();
		    finish();
		    startActivity(intent);
	}
}

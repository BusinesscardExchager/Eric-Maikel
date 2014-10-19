package com.example.businesscardexchager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		TextView tv = (TextView)findViewById(R.id.aantalKaarten);
		
		CardProvider cp = new CardProvider(this);
		Intent intent = getIntent();
		int position = intent.getIntExtra("name", 0);
		Card card = cp.getCard(position);
		
		tv.setText("Kaarthouder: " + card.getNaam());
	}
}

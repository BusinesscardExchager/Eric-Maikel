package com.example.pokerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_detail);
		
		Intent intent = this.getIntent();
		int position = intent.getIntExtra("position", 0);
		HistoryProvider provider = new HistoryProvider();
		HistoryGame game = provider.getGames().get(position);
		
		TextView tvGameName = (TextView) findViewById(R.id.tvGameName);
		tvGameName.setText(game.getName());
		
		TextView tvPlace = (TextView) findViewById(R.id.tvPlace);
		tvPlace.setText(game.getPlace() + "");
		
		TextView tvPrize = (TextView) findViewById(R.id.tvPrize);
		tvPrize.setText(game.getPrize() + "");
		
		TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
		tvStartTime.setText(game.getStartTime());
	}
}

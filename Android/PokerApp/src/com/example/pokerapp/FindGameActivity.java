package com.example.pokerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_game);
	}
	
	public void btnTournament_Click(View view)
	{
		Intent intent = new Intent(this, SelectGameActivity.class);
		intent.putExtra("kindOfGame", "Tournament");
		startActivity(intent);
	}
	
	public void btnSitGo_Click(View view)
	{
		Intent intent = new Intent(this, SelectGameActivity.class);
		intent.putExtra("kindOfGame", "Sit and Go");
		startActivity(intent);
	}
	
	public void btnRingGame_Click(View view)
	{
		Intent intent = new Intent(this, SelectGameActivity.class);
		intent.putExtra("kindOfGame", "Ring Game");
		startActivity(intent);
	}
}

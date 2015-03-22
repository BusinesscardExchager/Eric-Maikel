package com.example.pokerapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SelectGameActivity extends Activity {

	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_game);
		
		Intent intent = this.getIntent();
		final String kindOfGame = intent.getStringExtra("kindOfGame");
		TextView textview = (TextView) findViewById(R.id.tfKindOfGame);
		textview.setText(kindOfGame);
		
		GameProvider provider = new GameProvider(kindOfGame);
		List<Game> games = provider.getGames();
		
		if(games.size() > 0)
		{
			GameListAdapter adapter = new GameListAdapter(this,
					games);
			listview = (ListView) findViewById(R.id.gameList);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					int itemPosition = position;

					Intent intent = new Intent(getBaseContext(), GameDetailActivity.class);
					intent.putExtra("kindOfGame", kindOfGame);
					intent.putExtra("Game", itemPosition);
					startActivity(intent);
				}
			});
		}
		
		
	}
}

package com.example.pokerapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HistoryActivity extends Activity {

	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		HistoryProvider provider = new HistoryProvider();
		List<HistoryGame> games = provider.getGames();
		
		if(games.size() > 0)
		{
			HistoryGameListAdapter adapter = new HistoryGameListAdapter(this,
					games);
			listview = (ListView) findViewById(R.id.historyList);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(view.getContext(), HistoryDetailActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}
			});
		}
	}
}

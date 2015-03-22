package com.example.pokerapp;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryGameListAdapter extends ArrayAdapter<HistoryGame>{

	private Context context;
	private List<HistoryGame> games;

	public HistoryGameListAdapter(Context context, List<HistoryGame> games) {
		super(context, R.layout.gamelistitem, games);

		this.context = context;
		this.games = games;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.gamelistitem, null);
		}
		else{
			view = convertView;
		}
		
		HistoryGame requestedGame = games.get(position);
		TextView startTime = (TextView) view.findViewById(R.id.StartTimeListViewItem);
		startTime.setText(requestedGame.getStartTime());
		
		TextView gameName = (TextView) view.findViewById(R.id.nameListViewItem);
		gameName.setText(requestedGame.getName());
		
		TextView buyin = (TextView) view.findViewById(R.id.BuyinistViewItem);
		buyin.setText("#" + requestedGame.getPlace());

		return view;
	}
}

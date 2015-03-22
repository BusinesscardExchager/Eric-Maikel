package com.example.pokerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameDetailActivity extends Activity {

	Game selectedGame;
	String kindOfGame;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_detail);

		Intent intent = this.getIntent();
		kindOfGame = intent.getStringExtra("kindOfGame");
		int position = intent.getIntExtra("Game", 0);

		GameProvider provider = new GameProvider(kindOfGame);
		selectedGame = provider.getGames().get(position);

		TextView tvName = (TextView) findViewById(R.id.tvSelectedGame);
		tvName.setText(selectedGame.getName());

		TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
		tvStartTime.setText(selectedGame.getStartTime());

		TextView tvBuyin = (TextView) findViewById(R.id.tvBuyin);
		tvBuyin.setText(selectedGame.getBuyin());

		TextView tvTotalPrize = (TextView) findViewById(R.id.tvTotalPrize);
		tvTotalPrize.setText(selectedGame.getPrize() + "");

		TextView tvParticipants = (TextView) findViewById(R.id.tvParticipants);
		tvParticipants.setText(selectedGame.getNumberOfParticipants() + "/"
				+ selectedGame.getMaxParticipants());

		TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvDescription.setText(selectedGame.getGameDescription());
	}

	public void btnRegister_Click(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View view2 = view;
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int id) {
				Toast t = Toast.makeText(view2.getContext(), "Registerd for" + selectedGame.getName(), Toast.LENGTH_LONG);
				t.show();
			}
		});

		builder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						Toast t = Toast.makeText(view2.getContext(), "You did not register for " + selectedGame.getName(), Toast.LENGTH_LONG);
						t.show();
					}
				});

		String alertText = "";
		if(kindOfGame.equals("Tournament"))
		{
			alertText = "Are you sure you want to register for this tournament?";
		}
		else if(kindOfGame.equals("Ring Game"))
		{
			alertText = "Are you sure you want to register for this Ring Game?";
		}
		else if(kindOfGame.equals("Sit and Go"))
		{
			alertText = "Are you sure you want to register for this Sit & Go tournament?";
		}
		builder.setMessage(alertText).setTitle(selectedGame.getName());

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void btnMoreInfo_Click(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
}

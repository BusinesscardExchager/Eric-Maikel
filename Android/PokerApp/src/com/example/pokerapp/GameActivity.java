package com.example.pokerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class GameActivity extends Activity {
	
	boolean checkSet = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				EditText et = (EditText) findViewById(R.id.EtAmountBet);
				et.setText(progress+ "");
				
			}
		});
	}
	
	public void btnCheck_Click(View view)
	{
		if(checkSet == true)
		{
			checkSet = false;
			Button buttonCheck = (Button) findViewById(R.id.btnCheck);
			buttonCheck.setText(R.string.call);
			
			Button buttonBet = (Button) findViewById(R.id.btnBet);
			buttonBet.setText(R.string.raise);
		}
		else if(checkSet == false)
		{
			checkSet = true;
			Button buttonCheck = (Button) findViewById(R.id.btnCheck);
			buttonCheck.setText(R.string.check);
			
			Button buttonBet = (Button) findViewById(R.id.btnBet);
			buttonBet.setText(R.string.bet);
		}
	}
	
	public void btnLeave_Click(View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View view2 = view;
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int id) {
				Activity host = (Activity) view2.getContext();
				host.finish();
			}
		});

		builder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						
					}
				});

		String alertText = "Are you sure you want to leave this table?";
		builder.setMessage(alertText).setTitle("Leave Table");

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}

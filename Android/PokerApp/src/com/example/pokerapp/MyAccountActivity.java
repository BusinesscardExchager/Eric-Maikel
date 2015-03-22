package com.example.pokerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account);
	}
	
	public void btnHistory_Click(View view)
	{
		Intent intent = new Intent(this, HistoryActivity.class);
		startActivity(intent);
	}
}

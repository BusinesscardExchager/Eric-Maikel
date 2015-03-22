package com.example.pokerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    
    public void findGame_Click(View view)
    {
    	Intent findGameIntent = new Intent(this, FindGameActivity.class);
    	startActivity(findGameIntent);
    }
    
    public void myAccount_Click(View view)
    {
    	Intent findGameIntent = new Intent(this, MyAccountActivity.class);
    	startActivity(findGameIntent);
    }
    
    public void settings_Click(View view)
    {
    	Intent findGameIntent = new Intent(this, SettingsActivity.class);
    	startActivity(findGameIntent);
    }
}

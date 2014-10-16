package com.example.businesscardexchager;

import com.example.businesscardexchager.R.id;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class FragmentB extends Fragment {
	public static final String MY_PREFERENCES = "MyPrefs";

	SharedPreferences sharedprefs;
	public FragmentB() {
		// Required empty public constructor
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		// Inflate the layout for this fragment
		
        View V = inflater.inflate(R.layout.fragment_b, container , false);
        TextView tv = (TextView)V.findViewById(id.tvnaamCard);
		if(sharedprefs.contains("naamCard"))
		{
			tv.setText(sharedprefs.getString("naamCard", "Naam"));
		}

		return inflater.inflate(R.layout.fragment_b, container , false);
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		TextView tv = (TextView) getView().findViewById(id.tvnaamCard);
		if(sharedprefs.contains("naamCard"))
		{
			tv.setText(sharedprefs.getString("naamCard", "Naam"));
		}
	}
	
}

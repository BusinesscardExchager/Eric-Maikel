package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;

import com.example.businesscardexchager.R.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class FragmentA extends Fragment {

	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	int count = 0;

	ArrayList<Card> list;
	GridView grid;

	public FragmentA() {
		// Required empty public constructor

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@SuppressLint("NewApi") @Override
	public void onResume() {
		View view = getView().findViewById(R.id.fragmentA);
		super.onResume();
		if (sharedprefs != null) {
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				if (color.equals("Rood")) {
					view.setBackgroundColor(getResources()
							.getColor(R.color.red));
				} else if (color.equals("Blauw")) {
					view.setBackgroundColor(getResources().getColor(
							R.color.blue));
				} else if (color.equals("Groen")) {
					view.setBackgroundColor(getResources().getColor(
							R.color.green));
				}
			}
		} else {
			Log.d("EDR", "hoi");
		}
		
		
		/*--------------------------Cards aanmaken--------------------------------*/
		/*Card card1 = new Card("ME Software Inc.", "Eric de Regter", "Patrijshof 7", "0495-544302", "Managing Software Engineer", color.AliceBlue);
		Card card2 = new Card("ME Software Inc.", "Maikel Hoeks", "Magneestrat 101", "0612950493", "Software Engineer", color.Beige);
		
		list = new ArrayList<Card>();
		
		list.add(card1);
		list.add(card2);
		
		View img = new ImageView(getActivity().getBaseContext());
		img.setBackground(getResources().getDrawable(R.drawable.cardachtergrond));
		
		grid.addView(img);
		
		grid= (GridView) getView().findViewById(R.id.Collection);*/

		
		/*--------------------------Cards aanmaken--------------------------------*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);
		View rootView = inflater.inflate(R.layout.fragment_a, container, false);
		
		grid = (GridView) rootView.findViewById(R.id.Collection);
		grid.setAdapter(new MyGridAdapter(getActivity().getApplicationContext()));
		return rootView;
	}

}

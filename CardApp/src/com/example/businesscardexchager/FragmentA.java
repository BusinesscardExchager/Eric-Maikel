package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;

import com.example.businesscardexchager.R.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

	GridView grid;

	public FragmentA() {
		// Required empty public constructor

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
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
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);
		View rootView = inflater.inflate(R.layout.fragment_a, container, false);

		final CardProvider cardProvider = new CardProvider(getActivity()
				.getApplicationContext());
		List<Card> cards = cardProvider.getCards();

		if (cards.size() == 0) {
			Card card1 = new Card("ME Software Inc.", "Eric de Regter",
					"Patrijshof 7", "0495-544302",
					"Managing Software Engineer", color.Aquamarine);
			Card card2 = new Card("ME Software Inc.", "Maikel Hoeks",
					"Magneestrat 101", "0612950493", "Software Engineer",
					color.android_green);

			Card card3 = new Card("NS", "Gerard de Regter", "Patrijshof 7",
					"0495544302", "Medewerker", color.Bisque);

			Card card4 = new Card("Land van Horne", "Mila de Regter",
					"Patrijhof 7", "0495-544302", "Verpleegster",
					color.CadetBlue);

			cardProvider.addCard(card1);
			cardProvider.addCard(card2);
			cardProvider.addCard(card3);
			cardProvider.addCard(card4);
		}

		grid = (GridView) rootView.findViewById(R.id.Collection);
		grid.setAdapter(new MyGridAdapter(
				getActivity().getApplicationContext(), cards));

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View view, int position,
					long id) {
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), DetailActivity.class);
				int cardNr = position;
				Card card = (Card) cardProvider.getCard(position);
				intent.putExtra("card", card);
				intent.putExtra("name", position);
				startActivity(intent);
			}

		});
		return rootView;
	}

}

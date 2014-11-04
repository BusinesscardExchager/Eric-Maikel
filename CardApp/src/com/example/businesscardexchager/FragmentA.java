package com.example.businesscardexchager;

import java.util.List;

import com.example.businesscardexchager.R.color;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Fragment geeft de ontvangen kaarten weer
 * 
 */
public class FragmentA extends Fragment {

	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	MyGridAdapter adapter;
	CardProvider cardProvider;
	GridView grid;

	public FragmentA() {
		// Required empty public constructor
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * Lifecycle method zet de achtergrond kleur steeds als er een onResume
	 * optreedt
	 */
	@Override
	public void onResume() {
		View view = getView().findViewById(R.id.fragmentA);
		super.onResume();
		if (sharedprefs != null) {
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				if (color.equals("Rood")) {
					view.setBackgroundResource(R.drawable.bg_one);
				} else if (color.equals("Blauw")) {
					view.setBackgroundResource(R.drawable.bg_two);
				} else if (color.equals("Groen")) {
					view.setBackgroundResource(R.drawable.bg_three);
				}
			}
		}

		/**
		 * Laat de adapter weten dat er een verandering is in de data (nog een
		 * goede plaats vinden)
		 */
		((BaseAdapter) grid.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);
		View rootView = inflater.inflate(R.layout.fragment_a, container, false);

		/**
		 * Voegt kaarten toe aan de app als deze voor het eerst opstart. (Nog
		 * veranderen in serialiseren)?
		 */
		cardProvider = new CardProvider(getActivity().getApplicationContext());
		List<Card> cards = cardProvider.getCards();

		if (cards.size() == 0) {
			Card card1 = new Card("ME Software Inc.", "Hans Petersson",
					"St. Eugene straat 83", "0619681049",
					"Managing Software Engineer", "h.petersson@me.com",
					color.newAndroid_blue);
			
			Card card2 = new Card("ME Software Inc.", "Caroline Singer",
					"Mussenberg 201", "0612950493", "Paralegal",
					"c.singer@me.com", color.newAndroid_red);

			Card card3 = new Card("Microsoft", "Bill Gates", "One Microsoft Way",
					"425 8828080", "Big Boss", "b.gates@microsoft.nl",
					color.newAndroid_green);

			Card card4 = new Card("Land van Horne", "Paul Thornson",
					"Biest 43", "0490-391092", "CEO",
					"paul.thornson@lvh.nl", color.newAndroid_blue);

			Card card5 = new Card("NS", "Femke Janssen",
					"Laan van Puntenburg 100", "+31 (0)88 6712000", "Personeelszaken",
					"femke.janssen@ns.nl", color.newAndroid_green);

			card1.setAfbeelding(R.drawable.persoon_one);
			card2.setAfbeelding(R.drawable.woman_one);
			card3.setAfbeelding(R.drawable.person_three);
			card4.setAfbeelding(R.drawable.person_four);
			card5.setAfbeelding(R.drawable.woman_five);

			card1.setLocatie("Utrecht");
			card1.setReden("Stageplek");

			/** Kaarten toevoegen aan de static list cards in CardProvider */
			cardProvider.addCard(card1);
			cardProvider.addCard(card2);
			cardProvider.addCard(card3);
			cardProvider.addCard(card4);
			cardProvider.addCard(card5);
		}

		/**
		 * Adapter wordt toegevoegd aan GridView en onclick listener wordt
		 * gemaakt
		 */
		grid = (GridView) rootView.findViewById(R.id.Collection);
		adapter = new MyGridAdapter(getActivity().getApplicationContext(),
				cards);
		grid.setAdapter(adapter);

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View view, int position,
					long id) {
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), DetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}

		});
		return rootView;
	}

}

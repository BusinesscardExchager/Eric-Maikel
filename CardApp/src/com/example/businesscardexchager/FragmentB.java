package com.example.businesscardexchager;

import com.example.businesscardexchager.R.id;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Fragment B geeft de eigen kaart weer
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
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);

		inflater.inflate(R.layout.fragment_b, container, false);

		return inflater.inflate(R.layout.fragment_b, container, false);
	}

	/**
	 * De kaart in fragment B wordt steeds gemaakt in de onResume hierdoor
	 * blijft de kaart up to date
	 */
	@Override
	public void onResume() {
		super.onResume();
		TextView tv = (TextView) getView().findViewById(id.tvnaamCard);
		if (sharedprefs != null) {
			if (sharedprefs.contains("naamCard")) {
				String naam = sharedprefs.getString("naamCard", "naam");
				tv.setText(naam);
			}
			if (sharedprefs.contains("AdresCard")) {
				TextView tvAdres = (TextView) getView().findViewById(
						R.id.tvAdresCard);
				String adres = sharedprefs.getString("AdresCard", "Adres");
				tvAdres.setText(adres);
			}

			if (sharedprefs.contains("bedrijfCard")) {
				TextView tvBedrijf = (TextView) getView().findViewById(
						R.id.tvBedrijfCard);
				String bedrijf = sharedprefs
						.getString("bedrijfCard", "Bedrijf");
				tvBedrijf.setText(bedrijf);
			}

			if (sharedprefs.contains("telefoonCard")) {
				TextView tvTelefoon = (TextView) getView().findViewById(
						R.id.tvTelefoonCard);
				String nummer = sharedprefs.getString("telefoonCard", "nummer");
				tvTelefoon.setText(nummer);
			}

			if (sharedprefs.contains("functieCard")) {
				TextView tvFunctie = (TextView) getView().findViewById(
						R.id.tvFunctieCard);
				String functie = sharedprefs
						.getString("functieCard", "Functie");
				tvFunctie.setText(functie);
			}

			if (sharedprefs.contains("mailCard")) {
				TextView tvEmail = (TextView) getView().findViewById(
						R.id.tvEmailCard);
				String Email = sharedprefs.getString("mailCard", "Email");
				tvEmail.setText(Email);
			}

			if (sharedprefs.contains("PhotoCard")) {
				ImageView ivPhoto = (ImageView) getView().findViewById(
						R.id.PhotoCard);
				ivPhoto.setImageBitmap(Card.decodeBase64(sharedprefs.getString(
						"PhotoCard", "photo")));
			}

			View view = getView().findViewById(R.id.fragmentB);

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

		/** De achtergrond van het kaartje wordt veranderd indien nodig */
		View layout = getView().findViewById(R.id.fragmentB_linear);
		GradientDrawable gDrawable = (GradientDrawable) layout.getBackground();
		if (sharedprefs.contains("achtergrondkleurCard")) {
			String color = sharedprefs.getString("achtergrondkleurCard", "");

			if (color.equals("Rood")) {
				gDrawable.setColorFilter(
						getResources().getColor(R.color.newAndroid_red),
						PorterDuff.Mode.MULTIPLY);
			} else if (color.equals("Blauw")) {
				gDrawable.setColorFilter(
						getResources().getColor(R.color.newAndroid_blue),
						PorterDuff.Mode.MULTIPLY);
			} else if (color.equals("Groen")) {
				gDrawable.setColorFilter(
						getResources().getColor(R.color.newAndroid_green),
						PorterDuff.Mode.MULTIPLY);
			}
		}
		gDrawable.setStroke(3, getResources().getColor(R.color.black));
	}

}

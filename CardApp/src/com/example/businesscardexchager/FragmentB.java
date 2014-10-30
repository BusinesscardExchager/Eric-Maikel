package com.example.businesscardexchager;

import com.example.businesscardexchager.R.id;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
		sharedprefs = this.getActivity().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);
		// Inflate the layout for this fragment

		View V = inflater.inflate(R.layout.fragment_b, container, false);

		if (sharedprefs.contains("naamCard")) {
			TextView tvNaam = (TextView) V.findViewById(R.id.tvnaamCard);
			if (tvNaam != null) {
				String naam = sharedprefs.getString("naamCard", "Naam");
				tvNaam.setText(naam);
				Log.d("EDR", tvNaam.getText().toString());
			} else {
				Log.d("EDR", "fiets");
			}
		}

		if (sharedprefs.contains("AdresCard")) {
			TextView tvAdres = (TextView) V.findViewById(R.id.tvAdresCard);
			if (tvAdres != null) {
				String adres = sharedprefs.getString("AdresCard", "Adres");
				tvAdres.setText(adres);
				Log.d("EDR", tvAdres.getText().toString());
			} else {
				Log.d("EDR", "fiets");
			}
		}

		if (sharedprefs.contains("bedrijfCard")) {
			TextView tvBedrijf = (TextView) V.findViewById(R.id.tvBedrijfCard);
			if (tvBedrijf != null) {
				String bedrijf = sharedprefs
						.getString("bedrijfCard", "Bedrijf");
				tvBedrijf.setText(bedrijf);
				Log.d("EDR", tvBedrijf.getText().toString());
			} else {
				Log.d("EDR", "fiets");
			}
		}

		return inflater.inflate(R.layout.fragment_b, container, false);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ImageView iv = (ImageView) getView().findViewById(id.PhotoCard);
		// iv.setImageBitmap()
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
				ivPhoto.setImageBitmap(decodeBase64(sharedprefs.getString(
						"PhotoCard", "photo")));
			}

			View view = getView().findViewById(R.id.fragmentB);

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
		View layout = getView().findViewById(R.id.fragmentB_linear);
		GradientDrawable gDrawable = (GradientDrawable) layout.getBackground();
		if (sharedprefs.contains("achtergrondkleurCard")) {
			String color = sharedprefs.getString("achtergrondkleurCard", "");

			if (color.equals("Rood")) {
				gDrawable.setColorFilter(
						getResources().getColor(R.color.LightPink),
						PorterDuff.Mode.MULTIPLY);
			} else if (color.equals("Blauw")) {
				gDrawable.setColorFilter(
						getResources().getColor(R.color.LightBlue),
						PorterDuff.Mode.MULTIPLY);
			} else if (color.equals("Groen")) {
				gDrawable.setColorFilter(getResources().getColor(R.color.LightGreen),
						PorterDuff.Mode.MULTIPLY);
			}
		}
		gDrawable.setStroke(3, getResources().getColor(R.color.black));
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}

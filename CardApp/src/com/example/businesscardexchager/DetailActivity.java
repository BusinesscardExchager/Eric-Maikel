package com.example.businesscardexchager;

import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	public static final String MY_PREFERENCES = "MyPrefs";
	SharedPreferences sharedprefs;
	Card card;
	CardProvider cp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		sharedprefs = getApplication().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);

		View view = findViewById(R.id.activity_detail);
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

		int position = getIntent().getIntExtra("position", -1);
		cp = new CardProvider(getApplicationContext());
		card = cp.getCard(position);
		TextView tvBedrijf = (TextView) findViewById(R.id.tvBedrijf);
		TextView tvNaam = (TextView) findViewById(R.id.tvNaam);
		TextView tvAdres = (TextView) findViewById(R.id.tvAdres);
		TextView tvTelefoonnummer = (TextView) findViewById(R.id.tvTelefoonnummer);
		TextView tvFunctie = (TextView) findViewById(R.id.tvFunctie);
		TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		ImageView img = (ImageView) findViewById(R.id.imageCardDetail);

		if (card.getAfbeelding() == -2) {
			Log.d("EDR", "1" + card.getNaam());
			Bitmap bm = Card.decodeBase64(card.getAfbeeldingString());
			img.setImageBitmap(bm);
			
		} else if (card.getAfbeelding() != -1) {

			img.setImageDrawable(getResources().getDrawable(
					card.getAfbeelding()));
		}

		if (card != null) {
			tvBedrijf.setText(card.getBedrijf());
			tvNaam.setText(card.getNaam());
			tvAdres.setText(card.getAdres());
			tvTelefoonnummer.setText(card.getTelnummer());
			tvFunctie.setText(card.getFunctie());
			tvEmail.setText(card.getEmail());
			etWaar.setText(card.getLocatie());
			etWaarom.setText(card.getReden());

			View rootCardView = findViewById(R.id.topCardDetail);
			GradientDrawable gDrawable = (GradientDrawable) rootCardView
					.getBackground();
			gDrawable.setColorFilter(
					getResources().getColor(card.getAchtergrondKleur()),
					PorterDuff.Mode.MULTIPLY);
			gDrawable.setStroke(3, getResources().getColor(R.color.black));
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		etWaar.setText(card.getLocatie());
		etWaarom.setText(card.getReden());
	}

	public void call(View view) {
		String telefoonnummer = "tel:" + card.getTelnummer();
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse(telefoonnummer));
		startActivity(callIntent);
	}

	public void sendEmail(View view) {
		String email = card.getEmail();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"mailto", email, null));
		startActivity(Intent.createChooser(emailIntent,
				"Choose an Email client: "));
	}

	public void sendText(View view) {

		Intent textIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"smsto", card.getTelnummer(), null));
		textIntent.putExtra("chat", true);
		startActivity(Intent.createChooser(textIntent, "Send with:"));

	}

	public void addContact(View view) {
		String naam = card.getNaam();
		String bedrijf = card.getBedrijf();
		String functie = card.getFunctie();
		String adres = card.getAdres();
		String telefoonnummer = card.getTelnummer();
		String email = card.getEmail();

		Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

		intent.putExtra(ContactsContract.Intents.Insert.NAME, naam);
		intent.putExtra(ContactsContract.Intents.Insert.PHONE, telefoonnummer);
		intent.putExtra(ContactsContract.Intents.Insert.COMPANY, bedrijf);
		intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, functie);
		intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
		intent.putExtra(ContactsContract.Intents.Insert.POSTAL, adres);
		startActivity(intent);
	}

	public void SaveDetail(View view) {
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);

		int position = getIntent().getIntExtra("position", -1);
		CardProvider cp = new CardProvider(this);
		List<Card> cards = cp.getCards();
		Card card2 = cards.get(position);

		card2.setLocatie(etWaar.getText().toString());
		card2.setReden(etWaarom.getText().toString());
		finish();
	}

	@Override
	public void onBackPressed() {
		finish();
	}
}

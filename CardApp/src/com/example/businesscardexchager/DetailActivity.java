package com.example.businesscardexchager;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
		getActionBar().setDisplayHomeAsUpEnabled(true);
		/** Zet de achtergrond van de activity */
		sharedprefs = getApplication().getSharedPreferences(MY_PREFERENCES,
				Context.MODE_PRIVATE);

		View view = findViewById(R.id.activity_detail);
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
		 * Haalt de de kaart op met de doorgegeven positie in de static list
		 * Card in cardProvider
		 */
		int position = getIntent().getIntExtra("position", -1);
		if (position != -1) {
			cp = new CardProvider(getApplicationContext());
			card = cp.getCard(position);
		}
		else if(getIntent().getParcelableExtra("card") != null)
		{
			card = (Card) getIntent().getParcelableExtra("card");
		}
		TextView tvBedrijf = (TextView) findViewById(R.id.tvBedrijf);
		TextView tvNaam = (TextView) findViewById(R.id.tvNaam);
		TextView tvAdres = (TextView) findViewById(R.id.tvAdres);
		TextView tvTelefoonnummer = (TextView) findViewById(R.id.tvTelefoonnummer);
		TextView tvFunctie = (TextView) findViewById(R.id.tvFunctie);
		TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		ImageView img = (ImageView) findViewById(R.id.imageCardDetail);

		/**
		 * De opgehaalde kaart vanuit het jSon bestand heeft maakt gebruik van
		 * een string als afbeelding, vandaar de aparte if
		 */
		if (card.getAfbeelding() == -2) {
			try {
				img.getBackground().setAlpha(0);
				Bitmap bm = Card.decodeBase64(card.getAfbeeldingString());
				img.setImageBitmap(bm);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (card.getAfbeelding() != -1) {
			img.setImageDrawable(getResources().getDrawable(
					card.getAfbeelding()));
		}

		/** de kaart wordt gevuld met gegevens */
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

		getActionBar().setTitle(card.getNaam());
	}

	/** EditTexts waar en waarom worden gevuld */
	@Override
	public void onResume() {
		super.onResume();
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		etWaar.setText(card.getLocatie());
		etWaarom.setText(card.getReden());
	}

	/** Belt het telefoonnummer op de kaart */
	public void call(View view) {
		String telefoonnummer = "tel:" + card.getTelnummer();
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse(telefoonnummer));
		startActivity(callIntent);
	}

	/** Verstuurd een email naar het emailadres op de kaart */
	public void sendEmail(View view) {
		String email = card.getEmail();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"mailto", email, null));
		startActivity(Intent.createChooser(emailIntent,
				"Choose an Email client: "));
	}

	/**
	 * Verstuurd een textbericht naar het opgegeven nummer Whatsapp wordt nog
	 * niet ondersteund
	 */
	public void sendText(View view) {
		Intent textIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"smsto", card.getTelnummer(), null));
		textIntent.putExtra("chat", true);
		startActivity(Intent.createChooser(textIntent, "Send with:"));

	}

	/** Er wordt een nieuw contact gemaakt met de gegevens van de kaart */
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

	/** de getoonde kaart wordt geupdate */
	public void SaveDetail(View view) {
		EditText etWaar = (EditText) findViewById(R.id.waarGekregenET);
		EditText etWaarom = (EditText) findViewById(R.id.waaromGekregenET);

		int position = getIntent().getIntExtra("position", -1);
		CardProvider cp = new CardProvider(this);
		List<Card> cards = cp.getCards();
		Card card2 = cards.get(position);

		card2.setLocatie(etWaar.getText().toString());
		card2.setReden(etWaarom.getText().toString());
		CardProvider.saveCards();
		finish();
	}

	/** Eventhandler van de hardware backknop */
	@Override
	public void onBackPressed() {
		finish();
	}
}

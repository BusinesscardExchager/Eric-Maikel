package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** Deze klasse zorgt voor de invulling van de gridView */
public class MyGridAdapter extends BaseAdapter {

	private List<Card> cards = new ArrayList<Card>();
	private LayoutInflater inflater;

	public MyGridAdapter(Context context, List<Card> cards) {
		inflater = LayoutInflater.from(context);
		this.cards = cards;
	}

	@Override
	public int getCount() {
		return cards.size();
	}

	@Override
	public Object getItem(int arg0) {
		return cards.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	/** suppresslint (newApi) voor de setBackground */
	@SuppressLint("NewApi")
	@Override
	public View getView(int i, View convertView, ViewGroup viewGroup) {
		View v = convertView;
		TextView textBedrijf;
		TextView textNaam;
		TextView textFunctie;
		TextView textAdres;
		TextView textTelefoonnummer;
		TextView textEmail;
		ImageView img;

		/** Als convertview(v) null is dan worden er nieuwe tags toegevoegd */
		if (v == null) {
			v = inflater.inflate(R.layout.row_grid, viewGroup, false);
			v.setTag(R.id.textBedrijf, v.findViewById(R.id.textBedrijf));
			v.setTag(R.id.textNaam, v.findViewById(R.id.textNaam));
			v.setTag(R.id.textFunctie, v.findViewById(R.id.textFunctie));
			v.setTag(R.id.textAdres, v.findViewById(R.id.textAdres));
			v.setTag(R.id.textTelefoonnummer,
					v.findViewById(R.id.textTelefoonnummer));
			v.setTag(R.id.textEmail, v.findViewById(R.id.textEmail));
			v.setTag(R.id.imageCard, v.findViewById(R.id.imageCard));
		}

		/** alle Views worden geinstantieerd */
		textBedrijf = (TextView) v.getTag(R.id.textBedrijf);
		textNaam = (TextView) v.getTag(R.id.textNaam);
		textFunctie = (TextView) v.getTag(R.id.textFunctie);
		textAdres = (TextView) v.getTag(R.id.textAdres);
		textTelefoonnummer = (TextView) v.getTag(R.id.textTelefoonnummer);
		textEmail = (TextView) v.getTag(R.id.textEmail);
		img = (ImageView) v.findViewById(R.id.imageCard);

		/** card wordt opgehaald uit de CardProvider */
		Card card = (Card) getItem(i);
		View root = v.findViewById(R.id.rootCard);
		GradientDrawable gDrawable = (GradientDrawable) root.getBackground()
				.mutate();

		/** Achtergrond van de kaart wordt gezet */
		gDrawable.setColorFilter(
				v.getResources().getColor(card.getAchtergrondKleur()),
				PorterDuff.Mode.MULTIPLY);
		gDrawable.setStroke(3, v.getResources().getColor(R.color.black));

		/** Informatie van de kaart wordt in de Views gezet */
		if(!card.getAfbeeldingString().equals(""))
		{
			img.setImageBitmap(Card.decodeBase64(card.getAfbeeldingString()));
		}
		else if(card.getHeeftAfbeelding() == true)
		{
			/** alpha (zichtbaarheid) wordt veranderd anders wordt er bij elk kaartje een photo weergegeven */
			img.setAlpha(1f);
			img.setImageDrawable(v.getResources().getDrawable(
					card.getAfbeelding()));
		}
		else if(card.getHeeftAfbeelding() == false)
		{
			img.setAlpha(0f);
		}

		textBedrijf.setText(card.getBedrijf());
		textNaam.setText(card.getNaam());
		textFunctie.setText(card.getFunctie());
		textAdres.setText(card.getAdres());
		textTelefoonnummer.setText(card.getTelnummer());
		textEmail.setText(card.getEmail());

		return v;
	}

}

package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.businesscardexchager.R.color;

public class MyGridAdapter extends BaseAdapter {

	private List<Card> cards = new ArrayList<Card>();
	private LayoutInflater inflater;

	public MyGridAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		Card card1 = new Card("ME Software Inc.", "Eric de Regter",
				"Patrijshof 7", "0495-544302", "Managing Software Engineer",
				color.AliceBlue);
		Card card2 = new Card("ME Software Inc.", "Maikel Hoeks",
				"Magneestrat 101", "0612950493", "Software Engineer",
				color.Beige);

		cards.add(card1);
		cards.add(card2);
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

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View v = view;
		ImageView imageView;
		TextView textView;

		if (v == null) {
			v = inflater.inflate(R.layout.row_grid, viewGroup, false);
			v.setTag(R.id.item_image, v.findViewById(R.id.item_image));
			v.setTag(R.id.item_text, v.findViewById(R.id.item_text));
		}

		imageView = (ImageView) v.getTag(R.id.item_image);
		textView = (TextView) v.getTag(R.id.item_text);

		Card card = (Card) getItem(i);
		imageView.setBackgroundColor(card.achtergrondKleur);
		textView.setText(card.getNaam());

		return v;
	}

}

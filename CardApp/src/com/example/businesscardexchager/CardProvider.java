package com.example.businesscardexchager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/** CardProvider heeft alle kaarten die in de app staan */
public class CardProvider {
	private static List<Card> cards;
	private Context context;
	
	public CardProvider(Context context)
	{
		this.context = context;
		if(cards == null)
		{
			cards = new ArrayList<Card>();
		}
	}
	
	public List<Card> getCards()
	{
		return cards;
	}
	
	public Card getCard(int index)
	{
		if(index >= cards.size())
		{
			return null;
		}
		return cards.get(index);
	}
	
	public void addCard(Card card)
	{
		cards.add(card);
	}
	
	public Card getCardByName(String naam)
	{
		for(Card card : cards)
		{
			if(card.getNaam().equals(naam))
			{
				return card;
			}
		}
		return null;
	}
}

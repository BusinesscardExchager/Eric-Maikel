package com.example.businesscardexchager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/** CardProvider heeft alle kaarten die in de app staan */
public class CardProvider {
	private static List<Card> cards;
	private Context context;
	private static String fileName = "savedCards.ser";
	
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
	
	public static void saveCards()
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		File folder = new File(Environment.getExternalStorageDirectory() + "/BCExchange");
		Log.d("EDR", folder.toString());
		if(!folder.exists())
		{
			boolean check = folder.mkdir();
			Log.d("EDR", check + "");
		}
		File file = new File(folder, fileName);
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch(IOException ex)
			{
				Log.d("EDr", ex.getMessage());
			}
		}
		try{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(cards);
			out.close();
			fos.close();
		}
		catch(IOException ex)
		{
			Log.d("EDR", ex.getMessage());
		}
	}
	
	public static List<Card> loadCards()
	{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		File folder = new File(Environment.getExternalStorageDirectory() + "/BCExchange");
		if(folder.exists())
		{
			File file = new File(folder, fileName);
			try
			{
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				cards = (List<Card>) in.readObject();
				in.close();
				fis.close();
			}
			catch(IOException ex)
			{
				Log.d("EDR", ex.getMessage());
			} catch (ClassNotFoundException ex) {
				Log.d("EDR", ex.getMessage());
			}
		}
		return cards;
	}
}

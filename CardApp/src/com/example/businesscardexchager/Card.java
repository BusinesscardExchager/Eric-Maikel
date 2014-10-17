package com.example.businesscardexchager;

import android.graphics.Color;
import android.media.Image;

public class Card {
	String bedrijf;
	String naam;
	String adres;
	String telefoonnummer;
	String functie;
	
	Image afbeelding;
	
	int achtergrondKleur;
	
	public Card()
	{
		this.achtergrondKleur = Color.MAGENTA;
	}
	
	public String getBedrijf()
	{
		return bedrijf;
	}
	
	public String getNaam()
	{
		return naam;
	}
	
	public String getAdres()
	{
		return adres;
	}
	
	public String getTelnummer()
	{
		return telefoonnummer;
	}
	
	public int getAchtergrondKleur()
	{
		return achtergrondKleur;
	}
	
	public String getFunctie()
	{
		return functie;
	}
	
	public void setBedrijf(String bedrijf)
	{
		this.bedrijf = bedrijf;
	}
	
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	
	public void setAdres(String adres)
	{
		this.adres = adres;
	}
	
	public void setTelnummer(String telnummer)
	{
		this.telefoonnummer = telnummer;
	}
	
	public void setAchtergrondKleur(int kleur)
	{
		this.achtergrondKleur = kleur;
	}
	
	public void setFunctie(String functie)
	{
		this.functie = functie;
	}
}

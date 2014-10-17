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

	public Card() {
		this.achtergrondKleur = Color.MAGENTA;
	}

	// Test constructor
	public Card(String bedrijf, String naam, String adres,
			String telefoonnummer, String functie, int achtergrondkleur) {
		this.bedrijf = bedrijf;
		this.naam = naam;
		this.adres = adres;
		this.telefoonnummer = telefoonnummer;
		this.functie = functie;
		this.achtergrondKleur = achtergrondkleur;
	}

	public String getBedrijf() {
		return bedrijf;
	}

	public String getNaam() {
		return naam;
	}

	public String getAdres() {
		return adres;
	}

	public String getTelnummer() {
		return telefoonnummer;
	}

	public int getAchtergrondKleur() {
		return achtergrondKleur;
	}

	public String getFunctie() {
		return functie;
	}

	public void setBedrijf(String bedrijf) {
		this.bedrijf = bedrijf;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public void setTelnummer(String telnummer) {
		this.telefoonnummer = telnummer;
	}

	public void setAchtergrondKleur(int kleur) {
		this.achtergrondKleur = kleur;
	}

	public void setFunctie(String functie) {
		this.functie = functie;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.bedrijf + "\n");
		sb.append(this.naam + "\n");
		String cardString;
		cardString = sb.toString();
		//afmaken
		return cardString;
	}
}

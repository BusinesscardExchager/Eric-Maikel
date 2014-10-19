package com.example.businesscardexchager;

import android.graphics.Bitmap;

public class Card {
	private String bedrijf;
	private String naam;
	private String adres;
	private String telefoonnummer;
	private String functie;

	private Bitmap afbeelding;

	private int achtergrondKleur;

	public Card() {
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
	
	public Bitmap getAfbeelding()
	{
		return afbeelding;
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
	
	public void setAfbeelding(Bitmap afbeelding)
	{
		this.afbeelding = afbeelding;
	}
}

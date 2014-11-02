package com.example.businesscardexchager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Card {
	private String bedrijf;
	private String naam;
	private String adres;
	private String telefoonnummer;
	private String functie;
	private String email;
	private String locatie;
	private String reden;
	private String afbeeldingString;
	private int afbeelding;
	private boolean heeftAfbeelding;

	private int achtergrondKleur;

	public Card() {
	}

	// Test constructor
	public Card(String bedrijf, String naam, String adres,
			String telefoonnummer, String functie, String email,
			int achtergrondkleur) {
		this.bedrijf = bedrijf;
		this.naam = naam;
		this.adres = adres;
		this.telefoonnummer = telefoonnummer;
		this.functie = functie;
		this.email = email;
		this.achtergrondKleur = achtergrondkleur;
		this.afbeelding = -1;
		this.locatie = "";
		this.reden = "";
		this.afbeeldingString = "";
		this.heeftAfbeelding = false;
	}

	public Card(String bedrijf, String naam, String adres,
			String telefoonnummer, String functie, String email,
			String afbeelding, String locatie, String reden) {
		this.bedrijf = bedrijf;
		this.naam = naam;
		this.adres = adres;
		this.telefoonnummer = telefoonnummer;
		this.functie = functie;
		this.email = email;
		this.afbeeldingString = afbeelding;
		this.afbeelding = -2;
		this.locatie = locatie;
		this.reden = reden;
		this.achtergrondKleur = R.color.Coral;
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

	public int getAfbeelding() {
		return afbeelding;
	}

	public String getEmail() {
		return email;
	}

	public String getLocatie() {
		return locatie;
	}

	public String getReden() {
		return reden;
	}

	public boolean getHeeftAfbeelding() {
		return heeftAfbeelding;
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

	public void setAfbeelding(int afbeelding) {
		this.afbeelding = afbeelding;
		this.heeftAfbeelding = true;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}

	public void setReden(String reden) {
		this.reden = reden;
	}

	@Override
	public String toString() {
		return this.bedrijf + ":" + this.naam + ":" + this.adres + ":"
				+ this.telefoonnummer + ":" + this.functie + ":" + this.email
				+ ":" + this.afbeeldingString + ":" + this.locatie + ":"
				+ this.reden;
	}

	public String getAfbeeldingString() {
		return afbeeldingString;
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
}

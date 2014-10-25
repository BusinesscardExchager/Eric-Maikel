package com.example.businesscardexchager;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
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

	public Bitmap getAfbeelding() {
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

	public void setAfbeelding(Bitmap afbeelding) {
		this.afbeelding = afbeelding;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(bedrijf);
		dest.writeString(naam);
		dest.writeString(adres);
		dest.writeString(telefoonnummer);
		dest.writeString(functie);
		dest.writeInt(achtergrondKleur);
	}

	//Wordt aangeroepen door createFromParcel
	private Card(Parcel in) {
		this.bedrijf = in.readString();
		this.naam = in.readString();
		this.adres = in.readString();
		this.telefoonnummer = in.readString();
		this.functie = in.readString();
		this.achtergrondKleur = in.readInt();
	}

	public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
		
		//Roept private Card(parcel in) aan.
		@Override 
		public Card createFromParcel(Parcel source)
		{
			return new Card(source);
		}

		
		//Auto-generated
		@Override
		public Card[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}

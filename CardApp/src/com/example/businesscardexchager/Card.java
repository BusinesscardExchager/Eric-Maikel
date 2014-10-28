package com.example.businesscardexchager;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
	private String bedrijf;
	private String naam;
	private String adres;
	private String telefoonnummer;
	private String functie;
	private String email;
	private String locatie;
	private String reden;

	private int afbeelding;

	private int achtergrondKleur;

	public Card() {
	}

	// Test constructor
	public Card(String bedrijf, String naam, String adres,
			String telefoonnummer, String functie, String email, int achtergrondkleur) {
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
	
	public String getEmail()
	{
		return email;
	}
	
	public String getLocatie()
	{
		return locatie;
	}
	
	public String getReden()
	{
		return reden;
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
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setLocatie(String locatie)
	{
		this.locatie = locatie;
	}
	
	public void setReden(String reden)
	{
		this.reden = reden;
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
		dest.writeInt(afbeelding);
		dest.writeString(email);
		dest.writeString(locatie);
		dest.writeString(reden);
	}

	//Wordt aangeroepen door createFromParcel
	private Card(Parcel in) {
		this.bedrijf = in.readString();
		this.naam = in.readString();
		this.adres = in.readString();
		this.telefoonnummer = in.readString();
		this.functie = in.readString();
		this.achtergrondKleur = in.readInt();
		this.afbeelding = in.readInt();
		this.email = in.readString();
		this.locatie = in.readString();
		this.reden = in.readString();
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

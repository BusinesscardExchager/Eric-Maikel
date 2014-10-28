package com.example.businesscardexchager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.example.businesscardexchager.R.id;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditCardActivity extends Activity {

	SharedPreferences sharedprefs;
	public static final String MY_PREFERENCES = "MyPrefs";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	String photo = "";
	Bitmap imageBitmap;

	@Override
	public void onResume() 
	{
		super.onResume();
		EditText etNaam = (EditText) findViewById(id.naamContact);
		EditText etAdres = (EditText) findViewById(id.adresContact);
		EditText etBedrijf = (EditText) findViewById(id.BedrijfContact);
		EditText etTelefoon = (EditText) findViewById(id.telefoonContact);
		EditText etFunctie = (EditText) findViewById(id.functieContact);
		EditText etMail = (EditText) findViewById(id.emailContact);
		ImageView iv = (ImageView) findViewById(id.PhotoContact);
		if(sharedprefs.contains("naamCard"))
		{
			etNaam.setText(sharedprefs.getString("naamCard", "Naam"));
		}
		if(sharedprefs.contains("AdresCard"))
		{
			etAdres.setText(sharedprefs.getString("AdresCard", "Adres"));
		}
		if(sharedprefs.contains("bedrijfCard"))
		{
			etBedrijf.setText(sharedprefs.getString("bedrijfCard", "Bedrijf"));
		}
		if(sharedprefs.contains("telefoonCard"))
		{
			etTelefoon.setText(sharedprefs.getString("telefoonCard", "Nummer"));
		}
		if(sharedprefs.contains("functieCard"))
		{
			etFunctie.setText(sharedprefs.getString("functieCard", "Functie"));
		}
		if(sharedprefs.contains("mailCard"))
		{
			etMail.setText(sharedprefs.getString("mailCard", "mail"));
		}
		if(sharedprefs.contains("PhotoCard"))
		{
			iv.setImageBitmap(decodeBase64(sharedprefs.getString("PhotoCard", "photo")));
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        imageBitmap = (Bitmap) extras.get("data");
	        imageBitmap = getResizedBitmap(imageBitmap, 100, 60);
	        ImageView iv = (ImageView)findViewById(id.PhotoContact);
	        iv.setImageBitmap(imageBitmap);
	        
			sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
			Editor editor = sharedprefs.edit();
	        editor.putString("PhotoCard", encodeTobase64(imageBitmap));
			editor.commit();
	    }
				


	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_card);

		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
			if (sharedprefs.contains("achtergrondkleur")) {
				String color = sharedprefs.getString("achtergrondkleur", "");
				View layout = findViewById(R.id.activity_edit_card);

				if (color.equals("Rood")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.red));
				} else if (color.equals("Blauw")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.blue));
				} else if (color.equals("Groen")) {
					layout.setBackgroundColor(getResources().getColor(
							R.color.green));
				}

			}

	}
	public void SaveMyCard(View view) {
		sharedprefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedprefs.edit();
		EditText etBedrijf = (EditText) findViewById(id.BedrijfContact);
		editor.putString("bedrijfCard", etBedrijf.getText().toString());
		
		EditText etNaam = (EditText) findViewById(id.naamContact);
		editor.putString("naamCard", etNaam.getText().toString());
		
		EditText etAdres = (EditText) findViewById(id.adresContact);
		editor.putString("AdresCard", etAdres.getText().toString());
		
		EditText etTelefoon = (EditText) findViewById(id.telefoonContact);
		editor.putString("telefoonCard", etTelefoon.getText().toString());
		
		EditText etFunctie = (EditText) findViewById(id.functieContact);
		editor.putString("functieCard", etFunctie.getText().toString());
		
		EditText etMail = (EditText) findViewById(id.emailContact);
		editor.putString("mailCard", etMail.getText().toString());
		
		
		editor.commit();
		finish();
	}
	
	public void TakePicCard(View view) {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	public static String encodeTobase64(Bitmap image) {
	    Bitmap immage = image;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

	    Log.d("Image Log:", imageEncoded);
	    return imageEncoded;
}
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
}

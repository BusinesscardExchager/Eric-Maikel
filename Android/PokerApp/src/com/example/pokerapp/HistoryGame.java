package com.example.pokerapp;

public class HistoryGame {
	private String name;
	private int place;
	private String startTime;
	private String prize;
	
	public HistoryGame(String name, int place, String startTime, String prize)
	{
		this.name = name;
		this.place = place;
		this.startTime = startTime;
		this.prize = prize;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getPlace()
	{
		return this.place;
	}
	
	public String getStartTime()
	{
		return this.startTime;
	}
	
	public String getPrize()
	{
		return this.prize;
	}
}

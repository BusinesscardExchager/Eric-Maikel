package com.example.pokerapp;

public class Game {
	
	private String name;
	private String buyin;
	private int prize;
	private int participants;
	private int maxParticipants;
	private String gameDescription;
	private String startTime;
	
	public Game(String name, String buyin, int prize, int participants, int maxParticipants, String gameDescription, String startTime)
	{
		this.name = name;
		this.buyin = buyin;
		this.prize = prize;
		this.participants = participants;
		this.maxParticipants = maxParticipants;
		this.gameDescription = gameDescription;
		this.startTime = startTime;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getBuyin()
	{
		return this.buyin;
	}
	
	public int getPrize()
	{
		return this.prize;
	}
	
	public int getNumberOfParticipants()
	{
		return this.participants;
	}
	
	public int getMaxParticipants()
	{
		return this.maxParticipants;
	}
	
	public String getGameDescription()
	{
		return this.gameDescription;
	}
	
	public String getStartTime()
	{
		return this.startTime;
	}

}

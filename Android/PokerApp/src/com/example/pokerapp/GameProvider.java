package com.example.pokerapp;

import java.util.ArrayList;
import java.util.List;

public class GameProvider {

	private List<Game> games;
	
	public GameProvider(String kindOfGame)
	{
		games = new ArrayList<Game>();
		if(kindOfGame.equals("Tournament"))
		{
			Game game1 = new Game("Terminator Xtreme", "6", 100, 5, 100, "Knock eachother out of the tournament to win money.", "22:00");
			Game game2 = new Game("The Basement", "1.50",250, 35, 400, "Enter now for only $1.50.", "22:20");
			Game game3 = new Game("Freerol($50)", "0", 50, 300, 500, "Win real money for free!", "22:40");
			Game game4 = new Game("Terminator", "3.50", 100, 10, 50, "Get 25% of your buyin back when you knock someone out of the tournament.", "23:00");
			Game game5 = new Game("Life's a Beach", "1.50", 250, 40, 400, "Fast tournament to win a lot of money.", "23:30");
			Game game6 = new Game("Low Roller", "1.50", 750, 30, 100, "Win up to $300.", "0:00");
			
			games.add(game1);
			games.add(game2);
			games.add(game3);
			games.add(game4);
			games.add(game5);
			games.add(game6);
		}
		else if(kindOfGame.equals("Ring Game"))
		{
			Game game1 = new Game("Ring Game #1", "0.60", 3, 3, 6, "1 Table tournament. Get first or second to win the money.", "22:00");
			Game game2 = new Game("Ring Game #2", "0.60", 3, 5, 6, "1 Table tournament. Get first or second to win the money.", "22:20");
			Game game3 = new Game("Ring Game #3", "1.50", 9, 0, 6, "1 Table tournament. Get first or second to win the money.", "22:40");
			Game game4 = new Game("Ring Game #4", "6", 30, 4, 6, "1 Table tournament. Get first or second to win the money.", "23:00");
			Game game5 = new Game("Ring Game #5", "12", 70, 0, 8, "1 Table tournament. Get first or second to win the money.", "23:30");
			Game game6 = new Game("Ring Game #6", "12", 70, 0, 8, "1 Table tournament. Get first or second to win the money.", "0:00");
			
			games.add(game1);
			games.add(game2);
			games.add(game3);
			games.add(game4);
			games.add(game5);
			games.add(game6);
		}
		else if(kindOfGame.equals("Sit and Go"))
		{
			Game game1 = new Game("Table #1", "0.50", 0, 3, 6, "Play and win directly with real money.", "22:00");
			Game game2 = new Game("Table #2", "0.50", 0, 2, 6, "Play and win directly with real money.", "22:20");
			Game game3 = new Game("Table #3", "1", 0, 3, 6, "Play and win directly with real money.", "22:40");
			Game game4 = new Game("Table #4", "1.50", 0, 6, 6, "Play and win directly with real money.", "23:00");
			Game game5 = new Game("Table #5", "6", 0, 3, 6, "Play and win directly with real money.", "23:30");
			Game game6 = new Game("Table #6", "10", 0, 1, 8, "Play and win directly with real money.", "0:00");
			
			games.add(game1);
			games.add(game2);
			games.add(game3);
			games.add(game4);
			games.add(game5);
			games.add(game6);
		}
	}
	
	public List<Game> getGames()
	{
		return this.games;
	}
}

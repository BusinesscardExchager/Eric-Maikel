package com.example.pokerapp;

import java.util.ArrayList;
import java.util.List;

public class HistoryProvider {
	private List<HistoryGame> games;

	public HistoryProvider() {
		games = new ArrayList<HistoryGame>();
		
		HistoryGame game1 = new HistoryGame("Terminator Xtreme", 8, "22:00", "$0.00");
		HistoryGame game2 = new HistoryGame("The Basement", 1, "22:20", "$44.04");
		HistoryGame game3 = new HistoryGame("Table #4", 2, "22:20", "$6.00");
		HistoryGame game4 = new HistoryGame("Life's a Beach", 4, "23:30", "$12.03");
		HistoryGame game5 = new HistoryGame("Ring Game 3", 6, "22:40", "0.00");
		HistoryGame game6 = new HistoryGame("Ring Game 6", 1, "0:00", "$50.00");
		
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(game4);
		games.add(game5);
		games.add(game6);
	}

	public List<HistoryGame> getGames() {
		return this.games;
	}
}

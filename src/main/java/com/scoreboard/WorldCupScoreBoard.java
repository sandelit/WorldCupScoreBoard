package com.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class WorldCupScoreBoard {

    private final List<Game> games;

    public WorldCupScoreBoard() {
        games = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.removeIf(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (Game game : games) {
            if (game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam)) {
                game.setHomeScore(homeScore);
                game.setAwayScore(awayScore);
                break;
            }
        }
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public List<Game> getSummary() {
        return games.stream().sorted((g1, g2) -> {
            int totalScoreComparison = Integer.compare(g2.getTotalScore(), g1.getTotalScore());
            if (totalScoreComparison == 0) {
                return Integer.compare(games.indexOf(g1), games.indexOf(g2));
            }
            return totalScoreComparison;
        })
        .toList();
    }
}
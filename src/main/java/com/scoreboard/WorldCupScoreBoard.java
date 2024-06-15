package com.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorldCupScoreBoard {

    private final List<Game> games;

    public WorldCupScoreBoard() {
        games = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.removeIf(game -> game.homeTeam().equals(homeTeam) && game.awayTeam().equals(awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Game> gameToUpdate = games.stream()
                .filter(game -> game.homeTeam().equals(homeTeam) && game.awayTeam().equals(awayTeam))
                .findFirst();

        gameToUpdate.ifPresent(game -> {
            int index = games.indexOf(game);
            games.set(index, game.withScores(homeScore, awayScore));
        });
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public List<Game> getSummary() {
        return games.stream()
                .sorted(Comparator
                        .comparingInt(Game::getTotalScore)
                        .reversed()
                        .thenComparing(game -> -games.indexOf(game)))
                .collect(Collectors.toList());
    }

    public String getFormattedSummary() {
        List<Game> summary = getSummary();

        StringBuilder formattedSummary = new StringBuilder();
        for (int i = 0; i < summary.size(); i++) {
            formattedSummary.append(i + 1)
                    .append(". ")
                    .append(summary.get(i))
                    .append("\n");
        }

        return formattedSummary.toString().trim();
    }
}
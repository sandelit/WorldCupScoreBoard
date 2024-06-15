package com.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Manages a scoreboard for the World Cup, allowing starting, finishing, and updating games, as well as summarizing all games.
 */
public class WorldCupScoreBoard {

    private final List<Game> games;

    /**
     * Constructs a new WorldCupScoreBoard with empty list of games.
     */
    public WorldCupScoreBoard() {
        games = new ArrayList<>();
    }

    /**
     * Starts a new game with the specified teams.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     */
    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    /**
     * Finishes the game between the specified teams.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     */
    public void finishGame(String homeTeam, String awayTeam) {
        games.removeIf(game -> game.homeTeam().equals(homeTeam) && game.awayTeam().equals(awayTeam));
    }

    /**
     * Updates the score of the game between the specified teams.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @param homeScore the new home score
     * @param awayScore the new away score
     */
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Game> gameToUpdate = games.stream()
                .filter(game -> game.homeTeam().equals(homeTeam) && game.awayTeam().equals(awayTeam))
                .findFirst();

        gameToUpdate.ifPresent(game -> {
            int index = games.indexOf(game);
            games.set(index, game.withScores(homeScore, awayScore));
        });
    }

    /**
     * Returns a list of all games currently in progress.
     *
     * @return a list of games
     */
    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    /**
     * Returns a summary of games sorted by total score and by last in first out order for games with the same score.
     *
     * @return a list of games sorted by total score
     */
    public List<Game> getSummary() {
        return games.stream()
                .sorted(Comparator
                        .comparingInt(Game::getTotalScore)
                        .reversed()
                        .thenComparing(game -> -games.indexOf(game))) // LIFO for games with same score
                .collect(Collectors.toList());
    }

    /**
     * Formats a summary of all games, each prefixed with a number.
     *
     * @return a formatted string of the game summary
     */
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
package com.scoreboard;

/**
 * Represents a football game with home team name and score, as well as away team name and score.
 */
public record Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    /**
     * Constructs new game with specified teams and score of 0-0.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     */
    public Game(String homeTeam, String awayTeam) {
        this(homeTeam, awayTeam, 0, 0);
    }

    /**
     * Gets total score of game.
     *
     * @return the total score
     */
    public int getTotalScore() {
        return homeScore + awayScore;
    }

    /**
     * Returns a new game with updated scores.
     *
     * @param homeScore the new home score
     * @param awayScore the new away score
     * @return a new game with the updated scores
     */
    public Game withScores(int homeScore, int awayScore) {
        return new Game(homeTeam, awayTeam, homeScore, awayScore);
    }

    /**
     * Return the team names and scores separated by - for summary.
     *
     * @return a string representation of the game
     */
    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}

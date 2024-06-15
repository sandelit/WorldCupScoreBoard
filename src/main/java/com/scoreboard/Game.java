package com.scoreboard;

public record Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    public Game(String homeTeam, String awayTeam) {
        this(homeTeam, awayTeam, 0, 0);
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public Game withScores(int homeScore, int awayScore) {
        return new Game(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}

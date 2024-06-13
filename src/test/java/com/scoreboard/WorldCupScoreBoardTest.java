package com.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldCupScoreBoardTest {

    private WorldCupScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new WorldCupScoreBoard();
    }

    @Test
    public void testStartGame() {
        scoreBoard.startGame("Argentina", "Algeria");
        assertEquals(1, scoreBoard.getGames().size());
        assertEquals("Argentina", scoreBoard.getGames().get(0).getHomeTeam());
        assertEquals("Algeria", scoreBoard.getGames().get(0).getAwayTeam());
    }

    @Test
    public void testFinishGame() {
        scoreBoard.startGame("Brazil", "Belgium");
        scoreBoard.finishGame("Brazil", "Belgium");
        assertEquals(0, scoreBoard.getGames().size());
    }

    @Test
    public void testUpdateScore() {
        scoreBoard.startGame("Cameroon", "Chile");
        scoreBoard.updateScore("Cameroon", "Chile", 4, 2);
        assertEquals(4, scoreBoard.getGames().get(0).getHomeScore());
        assertEquals(2, scoreBoard.getGames().get(0).getAwayScore());
    }

    @Test
    public void testGetSummary() {
        scoreBoard.startGame("Denmark", "Dominican Republic");
        scoreBoard.startGame("England", "Estonia");
        scoreBoard.updateScore("England", "Estonia", 4, 3);
        scoreBoard.updateScore("Denmark", "Dominican Republic", 5, 2);
        assertEquals("England",scoreBoard.getSummary().get(-1).getHomeTeam());
        assertEquals("Estonia",scoreBoard.getSummary().get(-1).getAwayTeam());
        assertEquals(4, scoreBoard.getSummary().get(-1).getHomeScore());
        assertEquals(3, scoreBoard.getSummary().get(-1).getAwayScore());
    }
}

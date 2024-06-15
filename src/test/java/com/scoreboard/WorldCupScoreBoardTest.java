package com.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertEquals("Argentina", scoreBoard.getGames().getFirst().homeTeam());
        assertEquals("Algeria", scoreBoard.getGames().getFirst().awayTeam());
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
        assertEquals(4, scoreBoard.getGames().getFirst().homeScore());
        assertEquals(2, scoreBoard.getGames().getFirst().awayScore());
    }

    @Test
    public void testGetSummary() {
        scoreBoard.startGame("Denmark", "Dominican Republic");
        scoreBoard.startGame("England", "Estonia");
        scoreBoard.updateScore("England", "Estonia", 4, 3);
        scoreBoard.updateScore("Denmark", "Dominican Republic", 5, 2);

        List<Game> summary = scoreBoard.getSummary();

        assertEquals("England",summary.getFirst().homeTeam());
        assertEquals("Estonia",summary.getFirst().awayTeam());
        assertEquals(4, summary.getFirst().homeScore());
        assertEquals(3, summary.getFirst().awayScore());
    }
}

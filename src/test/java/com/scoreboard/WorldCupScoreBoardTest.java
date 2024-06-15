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

    @Test
    public void testGetFormattedSummary() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        String expectedFormattedString = """
                1. Uruguay 6 - Italy 6
                2. Spain 10 - Brazil 2
                3. Mexico 0 - Canada 5
                4. Argentina 3 - Australia 1
                5. Germany 2 - France 2""";

        assertEquals(expectedFormattedString, scoreBoard.getFormattedSummary());
    }
}

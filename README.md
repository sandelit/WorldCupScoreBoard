# WorldCupScoreBoard

## Overview

The `WorldCupScoreBoard` is a simple Java library that manages a scoreboard for football (soccer) matches. It allows you to start new games, finish ongoing games, update scores, and get a summary of games sorted by total score and insertion order. This implementation uses Java records for immutability and conciseness.

## Features

- **Start a game**: Add a new game to the scoreboard with initial scores of 0-0.
- **Finish a game**: Remove an ongoing game from the scoreboard.
- **Update score**: Update the scores of an ongoing game.
- **Get summary**: Retrieve a summary of all games, sorted by total score and by insertion order for games with the same score.
- **Formatted summary**: Get a formatted string summary of games, each prefixed with a number.

## Installation

Clone the repository and include the `WorldCupScoreBoard` class and `Game` record in your Java project.

```sh
git clone https://github.com/sandelit/WorldCupScoreBoard.git
```

## Usage
Creating a Scoreboard

```java
WorldCupScoreBoard scoreBoard = new WorldCupScoreBoard();
```

Starting a Game

```java
scoreBoard.startGame("Mexico", "Canada");
```

Updating Scores

```java
scoreBoard.updateScore("Mexico", "Canada", 3, 2);
```

Finishing a Game

```java
scoreBoard.finishGame("Mexico", "Canada");
```

Getting a List of Games

```java
List<Game> games = scoreBoard.getGames();
```

Getting a Summary of Games in list
```java
List<Game> summary = scoreBoard.getSummary();
```

Getting a Formatted Summary text block

```java
String formattedSummary = scoreBoard.getFormattedSummary();
```

Example

```java
public class Main {
    public static void main(String[] args) {
        WorldCupScoreBoard scoreBoard = new WorldCupScoreBoard();
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

        String summary = scoreBoard.getFormattedSummary();
        System.out.println(summary);
    }
}
```

Output


```markdown
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
```
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TenisGameScoreSystemTest {

    TenisGameScoreSystem tenisGameScoreSystem = new TenisGameScoreSystem();

    @Test
    public void mustAddAPointToThePlayer(){
        int numberMaxOfPoints = 10;
        int expected, actual;
        for(int numberOfPointsAddedToAPlayer=1;numberOfPointsAddedToAPlayer < numberMaxOfPoints;numberOfPointsAddedToAPlayer++) {
            tenisGameScoreSystem.addScoreToFirstPlayer();

            expected = numberOfPointsAddedToAPlayer;
            actual = tenisGameScoreSystem.firstPlayerScore;

            assertEquals(expected, actual);
        }
    }

    @ParameterizedTest
    @CsvSource({"0, 4", "8, 6", "4, 0"})
    void mustDecideIfAnyPlayerHasFourPoints(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        String expected = tenisGameScoreSystem.showPlayerWithMorePoints();
        String actual = tenisGameScoreSystem.decideWinner();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "4, 3", "5, 5"})
    void mustDecideIfTheGameHasNotYetBennDefined(int firstPlayerScore, int secondPlayerScore){
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        String expected = "The game is not decided yet.";
        String actual = tenisGameScoreSystem.decideWinner();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"0, 0, Player 1: 0 Player 2: 0", "0, 2, Player 1: 0 Player 2: 30", "2, 3, Player 1: 30 Player 2: 40", "6, 5, Player 1: 40 Player 2: 40"})
    void mustShowTheGameScore(int firstPlayerScore, int secondPlayerScore, String expected) {
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);
        tenisGameScoreSystem.updateGameScore();

        String actual = tenisGameScoreSystem.gameScore;

        assertEquals(expected, actual);
    }

    @Test
    void mustTranslateThePointsToTenisSystem(){
        String[] pointsTable = new String[] {"0", "15", "30", "40"};
        String expected, actual;

        int numberMaxOfPossiblePoints = 4;
        for(int interactionInPointsTable=0;interactionInPointsTable<numberMaxOfPossiblePoints;interactionInPointsTable++)
        {
            expected = pointsTable[interactionInPointsTable];
            actual = tenisGameScoreSystem.translatePointsToTenisSystem(interactionInPointsTable);

            assertEquals(expected, actual );
        }
    }

    @ParameterizedTest
    @CsvSource({"4, 4", "4, 5"})
    void mustDecideIfTheDouceIsNotOverYet(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        String expected = "The game is not decided yet.";
        String actual = tenisGameScoreSystem.decideWinner();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"4, 6", "4, 8"})
    void mustDecideWhoIsTheWinnerOfTheDouce(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        String expected = tenisGameScoreSystem.showPlayerWithMorePoints();
        String actual = tenisGameScoreSystem.decideWinner();

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @CsvSource({"4, 0, 1", "0, 4, 2"})
    void mustDecideWhoWonTheGame(int firstPlayerScore, int secondPlayerScore, String playerWhoWon) {
        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        String expected = "The winner is player number " + playerWhoWon;
        String actual = tenisGameScoreSystem.showPlayerWithMorePoints();

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @CsvSource({"3, 3", "5, 5", "6, 7", "8, 8"})
    void mustDecideIfIsADouce(int firstPlayerScore, int secondPlayerScore) {
        String actual = "The game is not decided yet.";
        String expected = tenisGameScoreSystem.decideWinner();

        tenisGameScoreSystem.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(expected, actual);
    }

    @Test
    void mustResetTheGameScore() {
        int expected = 0;

        tenisGameScoreSystem.resetGameScore();

        assertEquals(expected, tenisGameScoreSystem.firstPlayerScore);
    }
}
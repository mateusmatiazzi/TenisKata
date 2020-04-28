import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TenisGameScoreSystemTest {

    TenisGameScoreSystem tenisGameScoreSystemTest = new TenisGameScoreSystem();

    @Test
    public void mustAddAPointToThePlayer(){
        for(int i=1;i<4;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();

            assertEquals(i, tenisGameScoreSystemTest.firstPlayerScore);

            tenisGameScoreSystemTest.addScoreToSecondPlayer();

            assertEquals(i, tenisGameScoreSystemTest.secondPlayerScore);
        }
    }

    @ParameterizedTest
    @CsvSource({"0, 4", "8, 6"})
    void mustDecideTheWinnerWith4Points(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(tenisGameScoreSystemTest.showPlayerWithMorePoints(), tenisGameScoreSystemTest.decideWinner());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "4, 3", "5, 5"})
    void mustDecideIfTheGameHasNotYetBennDefined(int firstPlayerScore, int secondPlayerScore){
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
    }

    @ParameterizedTest
    @CsvSource({"0, 0, Player 1: 0 Player 2: 0", "0, 2, Player 1: 0 Player 2: 30", "2, 3, Player 1: 30 Player 2: 40", "6, 5, Player 1: 40 Player 2: 40"})
    void mustShowTheGameScore(int firstPlayerScore, int secondPlayerScore, String result) {
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(result, tenisGameScoreSystemTest.showGameScore());
    }

    @Test
    void mustTranslateThePointsToTenisSystem(){
        String[] pointsTable = new String[] {"0", "15", "30", "40"};
        for(int i=0;i<4;i++)
        {
            assertEquals(pointsTable[i], tenisGameScoreSystemTest.translatePointsToTenisSystem(i));
        }
    }

    @ParameterizedTest
    @CsvSource({"4, 4", "4, 5"})
    void mustDecideIfTheDouceIsNotOverYet(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWhoWonTheDeuce());
    }

    @ParameterizedTest
    @CsvSource({"4, 6", "4, 8"})
    void mustDecideWhoIsTheWinnerOfTheDouce(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertEquals(tenisGameScoreSystemTest.showPlayerWithMorePoints(), tenisGameScoreSystemTest.decideWhoWonTheDeuce());
    }

    @Test
    void mustDecideWhoWonTheGame() {
        String msg = "The winner is player number ";

        tenisGameScoreSystemTest.setPlayersScore(4, 0);

        assertEquals(msg+"1", tenisGameScoreSystemTest.showPlayerWithMorePoints());

        tenisGameScoreSystemTest.setPlayersScore(0, 4);

        assertEquals(msg+"2", tenisGameScoreSystemTest.showPlayerWithMorePoints());
    }

    @ParameterizedTest
    @CsvSource({"3, 3", "5, 5", "6, 7", "8, 8"})
    void mustDecideIfIsADouce(int firstPlayerScore, int secondPlayerScore) {
        tenisGameScoreSystemTest.setPlayersScore(firstPlayerScore, secondPlayerScore);

        assertTrue(tenisGameScoreSystemTest.decideIfIsDeuce());
    }

    @Test
    void mustDecideIfAnyPlayerWas4Points() {
        for(int i=0;i<3;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();

            assertFalse(tenisGameScoreSystemTest.decideIfTheGameIfOver());
        }
        tenisGameScoreSystemTest.addScoreToFirstPlayer();

        assertTrue(tenisGameScoreSystemTest.decideIfTheGameIfOver());
    }

    @Test
    void mustResetTheGameScore() {
        tenisGameScoreSystemTest.setPlayersScore(1, 1);
        tenisGameScoreSystemTest.resetGameScore();

        assertEquals(0, tenisGameScoreSystemTest.firstPlayerScore);
        assertEquals(0, tenisGameScoreSystemTest.secondPlayerScore);
    }
}
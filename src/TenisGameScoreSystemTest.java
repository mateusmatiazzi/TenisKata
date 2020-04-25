import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenisGameScoreSystemTest {

    TenisGameScoreSystem tenisGameScoreSystemTest = new TenisGameScoreSystem();

    @BeforeEach
    public void mustResetThePlayersScore(){
        tenisGameScoreSystemTest.resetGameScore();
    }

    @Test
    public void mustAddAPointToThePlayer(){
        for(int i=1;i<4;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();
            assertEquals(i, tenisGameScoreSystemTest.firstPlayerScore);
            tenisGameScoreSystemTest.addScoreToSecondPlayer();
            assertEquals(i, tenisGameScoreSystemTest.secondPlayerScore);
        }
    }

    @Test
    void mustDecideTheWinnerWith4Points() {
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(0, 4);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(4, 3);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(5, 5);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(8, 6);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWinner());
    }

    @Test
    void mustShowTheGameScore() {
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertEquals("Player 1: 0, Player 2: 0", tenisGameScoreSystemTest.showGameScore());
        tenisGameScoreSystemTest.setPlayersScore(0, 2);
        assertEquals("Player 1: 0, Player 2: 30", tenisGameScoreSystemTest.showGameScore());
        tenisGameScoreSystemTest.setPlayersScore(2, 3);
        assertEquals("Player 1: 30, Player 2: 40", tenisGameScoreSystemTest.showGameScore());
        tenisGameScoreSystemTest.setPlayersScore(6, 5);
        assertEquals("Player 1: 40, Player 2: 40", tenisGameScoreSystemTest.showGameScore());
    }

    @Test
    void mustTranslateThePointsToTenisSystem(){
        String[] pointsTable = new String[] {"0", "15", "30", "40"};
        for(int i=0;i<4;i++)
        {
            assertEquals(pointsTable[i], tenisGameScoreSystemTest.translatePointsToTenisSystem(i));
        }
    }

    @Test
    void mustDecideIfTheDouceIsOverAndWhoIsTheWinner() {
        tenisGameScoreSystemTest.setPlayersScore(4, 4);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWhoWonTheDeuce());
        tenisGameScoreSystemTest.setPlayersScore(4, 5);
        assertEquals(tenisGameScoreSystemTest.showMessageThatTheGameHasNotYetBeenDefined(), tenisGameScoreSystemTest.decideWhoWonTheDeuce());
        tenisGameScoreSystemTest.setPlayersScore(4, 6);
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

    @Test
    void mustDecideIfIsADouce() {
        tenisGameScoreSystemTest.setPlayersScore(2, 3);
        assertFalse(tenisGameScoreSystemTest.decideIfIsDeuce());
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertFalse(tenisGameScoreSystemTest.decideIfIsDeuce());
        tenisGameScoreSystemTest.setPlayersScore(3, 3);
        assertTrue(tenisGameScoreSystemTest.decideIfIsDeuce());
    }

    @Test
    void mustDecideIfAnyPlayerWas4Points() {
        for(int i=0;i<3;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();
            assertFalse(tenisGameScoreSystemTest.decideIfAnyPlayerWas4Points());
        }
        tenisGameScoreSystemTest.addScoreToFirstPlayer();
        assertTrue(tenisGameScoreSystemTest.decideIfAnyPlayerWas4Points());
    }

    @Test
    void mustResetTheGameScore() {
        tenisGameScoreSystemTest.setPlayersScore(1, 1);
        tenisGameScoreSystemTest.resetGameScore();
        assertEquals(0, tenisGameScoreSystemTest.firstPlayerScore);
        assertEquals(0, tenisGameScoreSystemTest.secondPlayerScore);
    }
}
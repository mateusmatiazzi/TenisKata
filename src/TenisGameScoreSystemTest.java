import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenisGameScoreSystemTest {

    TenisGameScoreSystem tenisGameScoreSystemTest = new TenisGameScoreSystem();

    @Test
    public void addScore(){
        for(int i=1;i<4;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();
            assertEquals(i, tenisGameScoreSystemTest.firstPlayerScore);
            tenisGameScoreSystemTest.addScoreToSecondPlayer();
            assertEquals(i, tenisGameScoreSystemTest.secondPlayerScore);
        }
    }

    @Test
    void winnerWith4Points() {
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(0, 4);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(4, 3);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(5, 5);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.decideWinner());
        tenisGameScoreSystemTest.setPlayersScore(8, 6);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.decideWinner());
    }

    @Test
    void gameScore() {
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertEquals("Player 1: 0, Player 2: 0", tenisGameScoreSystemTest.gameScore());
        tenisGameScoreSystemTest.setPlayersScore(0, 2);
        assertEquals("Player 1: 0, Player 2: 30", tenisGameScoreSystemTest.gameScore());
        tenisGameScoreSystemTest.setPlayersScore(2, 3);
        assertEquals("Player 1: 30, Player 2: 40", tenisGameScoreSystemTest.gameScore());
        tenisGameScoreSystemTest.setPlayersScore(6, 5);
        assertEquals("Player 1: 40, Player 2: 40", tenisGameScoreSystemTest.gameScore());
    }

    @Test
    void translatePoints(){
        String[] pointsTable = new String[] {"0", "15", "30", "40"};
        for(int i=0;i<4;i++)
        {
            assertEquals(pointsTable[i], tenisGameScoreSystemTest.translatePoints(i));
        }
    }

    @Test
    void tiebreaker() {
        tenisGameScoreSystemTest.setPlayersScore(4, 4);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.tiebreaker());
        tenisGameScoreSystemTest.setPlayersScore(4, 5);
        assertEquals(tenisGameScoreSystemTest.notDecided(), tenisGameScoreSystemTest.tiebreaker());
        tenisGameScoreSystemTest.setPlayersScore(4, 6);
        assertEquals(tenisGameScoreSystemTest.showPlayerWithMorePoints(), tenisGameScoreSystemTest.tiebreaker());
    }

    @Test
    void showWinner() {
        String msg = "The winner is player number ";
        tenisGameScoreSystemTest.setPlayersScore(4, 0);
        assertEquals(msg+"1", tenisGameScoreSystemTest.showPlayerWithMorePoints());
        tenisGameScoreSystemTest.setPlayersScore(0, 4);
        assertEquals(msg+"2", tenisGameScoreSystemTest.showPlayerWithMorePoints());
    }

    @Test
    void isDeuce() {
        tenisGameScoreSystemTest.setPlayersScore(2, 3);
        assertFalse(tenisGameScoreSystemTest.isDeuce());
        tenisGameScoreSystemTest.setPlayersScore(0, 0);
        assertFalse(tenisGameScoreSystemTest.isDeuce());
        tenisGameScoreSystemTest.setPlayersScore(3, 3);
        assertTrue(tenisGameScoreSystemTest.isDeuce());
    }

    @Test
    void anyPlayerWas4Points() {
        tenisGameScoreSystemTest.resetGameScore();
        for(int i=0;i<3;i++) {
            tenisGameScoreSystemTest.addScoreToFirstPlayer();
            assertFalse(tenisGameScoreSystemTest.anyPlayerWas4Points());
        }
        tenisGameScoreSystemTest.addScoreToFirstPlayer();
        assertTrue(tenisGameScoreSystemTest.anyPlayerWas4Points());
    }

    @Test
    void resetGameScore() {
        tenisGameScoreSystemTest.addScoreToFirstPlayer();
        tenisGameScoreSystemTest.addScoreToSecondPlayer();
        tenisGameScoreSystemTest.resetGameScore();
        assertEquals(0, tenisGameScoreSystemTest.firstPlayerScore);
        assertEquals(0, tenisGameScoreSystemTest.secondPlayerScore);
    }
}
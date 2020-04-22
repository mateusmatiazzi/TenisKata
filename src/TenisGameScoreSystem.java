import static java.lang.Math.abs;

public class TenisGameScoreSystem {
    int firstPlayerScore;
    int secondPlayerScore;

    public TenisGameScoreSystem() {
        firstPlayerScore = 0;
        secondPlayerScore = 0;
    }

    public String decideWinner(){
        if(isDeuce())
            tiebreaker();
        else
            if (anyPlayerWas4Points())
                showPlayerWithMorePoints();
        return notDecided();
    }

    public String gameScore(){
        return "Player 1: " + translatePoints(firstPlayerScore) + ", Player 2: " + translatePoints(secondPlayerScore);
    }

    public String tiebreaker() {
        if(abs(firstPlayerScore - secondPlayerScore) == 2)
            return showPlayerWithMorePoints();
        return "The game is not decided yet.";
    }

    public String showPlayerWithMorePoints(){
        String msg = "The winner is player number ";
        if(firstPlayerScore > secondPlayerScore)
            return msg + "1";
        return msg + "2";
    }

    public String notDecided () {
        return "The game is not decided yet.";
    }

    public boolean isDeuce() {
        return (firstPlayerScore == secondPlayerScore) && firstPlayerScore >= 3;
    }

    public boolean anyPlayerWas4Points() {
        return firstPlayerScore == 4 || secondPlayerScore == 4;
    }

    public void addScoreToFirstPlayer(){
        firstPlayerScore++;
    }

    public void addScoreToSecondPlayer(){
        secondPlayerScore++;
    }

    public void resetGameScore(){
        setPlayersScore(0, 0);
    }

    public void setPlayersScore(int firstPlayerScore, int secondPlayerScore)
    {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public String translatePoints(int playerScore){
        String[] pointsTable = new String[] {"0", "15", "30", "40"};
        if (playerScore > 3)
            return "40";
        return pointsTable[playerScore];
    }
}

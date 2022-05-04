package main;

public class GameScore {
    private int score=0;
    public GameScore(){}

    public int getScore() {
        return score;
    }

    public int addScore(){
        score ++;
        return score;
    }
    public void clearScore(){
        score = 0;
    }
}

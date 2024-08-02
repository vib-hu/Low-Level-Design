package ballgame;

public class Player {
    public String Name;
    public int Six;
    public int Four;
    public int Wide;
    public int TotalBalls;
    public int Score;
    public boolean IsOut;
    public int TeamId;

    public Player(String name){
        Name = name;
    }

    public void updateScore(int run){
        if(run==4)
            Four++;
        else if(run==6)
            Six++;

        Score+=run;
        TotalBalls++;
    }

    public void setOut(){
        IsOut = true;
        TotalBalls++;
    }
}

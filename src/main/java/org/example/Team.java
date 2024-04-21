package org.example;

import java.util.*;
public class Team {
    public int Id;
    public String FirstPlayer;
    public String SecondPlayer;
    public String CurrentStriker;
    public LinkedHashMap<String,Player> PlayersMap;
    public int WideBalls;

    public Team(int id){
        Id = id;
    }

    public void addPlayer(String name){
        if(PlayersMap==null)
            PlayersMap = new LinkedHashMap<>();
        if(PlayersMap.containsKey(name))
            return;
        PlayersMap.put(name, new Player(name));

        if (FirstPlayer==null){
            FirstPlayer = name;
            CurrentStriker = FirstPlayer;
        }
        else if(SecondPlayer==null)
            SecondPlayer = name;
    }


    public boolean playBall(Ball ball){
        if(FirstPlayer==null || SecondPlayer==null)
            return false;

        if(ball.Run =="Wd"){
            WideBalls++;
            return true;
        }
        if(ball.Run =="W"){
            PlayersMap.get(CurrentStriker).setOut();
            return findNextPlayer(CurrentStriker);
        }
        int runInt = Integer.parseInt(ball.Run);
        PlayersMap.get(CurrentStriker).updateScore(runInt);
        if(runInt%2!=0){
                strikeChange();
            }
        return true;
    }

    public String totalOvers(){
        int totalBalls = 0;
        for(Player player: PlayersMap.values()){
            totalBalls += player.TotalBalls;
        }
        int completeOvers = totalBalls/6;
        int partialOvers = totalBalls%6;
        if(partialOvers>0)
            return completeOvers+"."+partialOvers;
        return String.valueOf(completeOvers);
    }

    public int totalScore(){
        int totalScore = 0;
        for(Player player: PlayersMap.values()){
            totalScore += player.Score;
        }
        return totalScore+WideBalls;
    }

    public int totalWickets(){
        int totalWickets = 0;
        for(Player player: PlayersMap.values()){
            totalWickets += player.IsOut?1:0;
        }
        return totalWickets;
    }

    public void strikeChange(){
        if(CurrentStriker.equals(FirstPlayer))
            CurrentStriker = SecondPlayer;
        else
            CurrentStriker = FirstPlayer;
    }

    private boolean findNextPlayer(String currentOut){
        for(Player player: PlayersMap.values()){
            if(!player.IsOut && !player.Name.equals(FirstPlayer) && !player.Name.equals(SecondPlayer)){
                if(currentOut.equals(FirstPlayer))
                    FirstPlayer = player.Name;
                else
                    SecondPlayer = player.Name;

                CurrentStriker = player.Name;
                return true;
            }
        }

        if(currentOut.equals(FirstPlayer))
            FirstPlayer = null;
        else
            SecondPlayer = null;
        return false;
    }
}

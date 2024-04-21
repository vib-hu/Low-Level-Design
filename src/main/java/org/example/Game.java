package org.example;

import java.util.ArrayList;

public class Game {
    public Team TeamOne;
    public Team TeamTwo;

    public boolean addTeam(int teamId){
        if(TeamOne!=null && TeamTwo!=null)
            return false;

        if((TeamOne!=null && TeamOne.Id==teamId) ||(TeamTwo!=null && TeamTwo.Id==teamId))
            return false;

        var team = new Team(teamId);
        if(TeamOne==null)
            TeamOne = team;
        else if(TeamTwo ==null)
            TeamTwo = team;
        return true;
    }

    public boolean addPlayer(String playerName, int teamId){
        Team team = getTeamFromId(teamId);
        if(team==null)
            return false;
        team.addPlayer(playerName);
        return true;
    }

    public boolean playOver(int teamId, Over over){
        Team team = getTeamFromId(teamId);
        if(team==null)
            return false;
        for(String ball: over.Balls){
            team.playBall(new Ball(ball));
        }
        if(over.Balls.size()>=6){
            team.strikeChange();
        }
        return true;
    }

    public ScoreCard scorecard(int teamId){
        Team team = getTeamFromId(teamId);
        if(team==null)
            return null;

        var scoreCard = new ScoreCard();
        scoreCard.PlayerDetails = playerScorecard(team);
        scoreCard.Overs = team.totalOvers();
        scoreCard.Total = team.totalScore()+"/"+team.totalWickets();
        return scoreCard;
    }

    private static ArrayList<String> playerScorecard(Team team) {
        ArrayList<String> playerScorecard = new ArrayList<>();
        playerScorecard.add("Player_Name Score 4s 6s Balls");

        for(Player player: team.PlayersMap.values()){
            String playerName = player.Name;
            if(playerName.equals(team.FirstPlayer) || playerName.equals(team.SecondPlayer))
                playerName+="*";
            String Score = String.valueOf(player.Score);
            String fours = String.valueOf(player.Four);
            String sixes = String.valueOf(player.Six);
            String balls = String.valueOf(player.TotalBalls);
            String playerDetail = playerName+" "+Score+" "+fours+" "+sixes+" "+balls;
            playerScorecard.add(playerDetail);
        }
        return playerScorecard;
    }

    public String finalResult(){
        if(TeamOne==null || TeamTwo==null)
            return null;
        int teamOneScore = TeamOne.totalScore();
        int teamTwoScore = TeamTwo.totalScore();
        if(teamOneScore>teamTwoScore)
            return "Team 1 won the match by "+(teamOneScore-teamTwoScore)+" runs";
        else if(teamTwoScore>teamOneScore)
            return "Team 2 won the match by "+(teamTwoScore-teamOneScore)+" runs";
        else
            return "Match draw";
    }

    private Team getTeamFromId(int teamId){
        if(TeamOne!=null && TeamOne.Id==teamId)
            return TeamOne;
        if(TeamTwo!=null && TeamTwo.Id==teamId)
            return TeamTwo;
        return null;
    }
}

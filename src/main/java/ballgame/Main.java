package ballgame;

public class Main {
    public static void main(String[] args) {

        var game = new Game();
        game.addTeam(1);
        game.addPlayer("P1", 1);
        game.addPlayer("P2", 1);
        game.addPlayer("P3", 1);
        game.addPlayer("P4", 1);
        game.addPlayer("P5", 1);

        var over = new Over();
        over.Balls.add("1");
        over.Balls.add("1");
        over.Balls.add("1");
        over.Balls.add("1");
        over.Balls.add("1");
        over.Balls.add("2");
        game.playOver(1, over);

        printScore(game, 1);

        over = new Over();
        over.Balls.add("W");
        over.Balls.add("4");
        over.Balls.add("4");
        over.Balls.add("Wd");
        over.Balls.add("W");
        over.Balls.add("1");
        over.Balls.add("6");
        game.playOver(1, over);

        printScore(game, 1);

        game.addTeam(2);
        game.addPlayer("P6", 2);
        game.addPlayer("P7", 2);
        game.addPlayer("P8", 2);
        game.addPlayer("P9", 2);
        game.addPlayer("P10", 2);

        over = new Over();
        over.Balls.add("4");
        over.Balls.add("6");
        over.Balls.add("W");
        over.Balls.add("W");
        over.Balls.add("1");
        over.Balls.add("1");
        game.playOver(2, over);

        printScore(game, 2);

        over = new Over();
        over.Balls.add("6");
        over.Balls.add("1");
        over.Balls.add("W");
        over.Balls.add("W");
        game.playOver(2, over);

        printScore(game, 2);
        System.out.println(game.finalResult());
    }

    private static void printScore(Game game, int teamId){
        var scorecard = game.scorecard(teamId);
        for(String playerDetail : scorecard.PlayerDetails){
            System.out.println(playerDetail);
        }
        System.out.println(scorecard.Total);
        System.out.println(scorecard.Overs);
    }
}
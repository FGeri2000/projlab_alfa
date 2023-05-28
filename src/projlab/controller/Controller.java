package projlab.controller;

import jdk.jshell.spi.ExecutionControl;
import projlab.graphics.*;
import projlab.sivatag.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Controller {

    public static Game game;
    public static PlayerGraphic selectedPlayer;
    public static ArrayList<PlayerGraphic> playerObjects;
    public static ArrayList<PipelineGraphic> pipelineObjects;
    public static ArrayList<PipeGraphic> pipeObjects;

    //public static MoveButton moveButton;
    //public static BreakButton breakButton;
    //public static RepairButton repairButton;
    //public static PlaceButton placeButton;
    //public static PickupButton pickupButton;
    public static StickyButton stickyButton;
    public static SlipperyButton slipperyButton;
    public static JComboBox<PipelineGraphic> objectDropDown;
    public static String winningTeam;
    private static java.util.Timer timer;
    public static AbstractAction setOutputButton;

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("TODO");
    }

    private static void startGame(){
        game = new Game();
        if(playerObjects == null || pipelineObjects == null || pipeObjects == null) throw new NullPointerException();
        game.newGame();
        //TODO tárolók feltöltése
        //TODO menü
        timer = new java.util.Timer();
        startTimer();
    }
    private static void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                while (game.getGameTimeLeft() >= 0){
                    //pipelineObjects.forEach(pipelineGraphic -> pipelineGraphic.draw());
                    //pipeObjects.forEach(pipeGraphic -> pipeGraphic.draw());
                    //playerObjects.forEach(playerGraphic -> playerGraphic.draw());
                    winningTeam = getWinningTeam();
                }
                //TODO winning team kijelzése (?)
                game.endGame();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private static String getWinningTeam(){
        if(game == null) throw new NullPointerException();
        int[] teamPoints = game.getTeamPoints();
        if(teamPoints == null) return null;
        if(teamPoints[0] == 0 && teamPoints[1] == 0) return null;
        if(teamPoints[0] > teamPoints[1]) return "plumbers";
        if(teamPoints[1] > teamPoints[0]) return "saboteurs";
        return "even";
    }
}

package projlab.controller;

import jdk.jshell.spi.ExecutionControl;
import projlab.graphics.*;
import projlab.sivatag.*;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TimerTask;

public class Controller {
    private static JFrame window;
    private static JPanel drawPanel;

    public static Game game;
    public static PlayerGraphic selectedPlayer;
    public static ArrayList<PlayerGraphic> playerObjects;
    public static ArrayList<PipelineGraphic> pipelineObjects;
    public static ArrayList<PipeGraphic> pipeObjects;

    public static MoveButton moveButton;
    //public static BreakButton breakButton;
    //public static RepairButton repairButton;
    public static PlaceButton placeButton;
    public static PickupButton pickupButton;
    public static StickyButton stickyButton;
    public static SlipperyButton slipperyButton;
    public static JComboBox<String> objectDropDown;
    public static String winningTeam;
    private static java.util.Timer timer;
    public static AbstractAction setInputButton;
    public static AbstractAction setOutputButton;

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        createWindow();
        
        
//        throw new ExecutionControl.NotImplementedException("TODO");
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
    
    private static void createWindow() {
    	window = new JFrame("Sivatagi csőhálózat - Alfa");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new JPanel();
        drawPanel.setPreferredSize(new Dimension(800,600));
        window.getContentPane().add(drawPanel);
        window.pack();
        window.setVisible(true);
    }
    
    private static void createMapGraphics() {
    	JunctionGraphic junc;
    	PipeGraphic pipe;
    	
    	//források
    	junc = new SourceGraphic((Source)game.getPipeElement("source1"));
    	junc.changeCoordinates(100, 200);
    	pipelineObjects.add(junc);
    	junc = new SourceGraphic((Source)game.getPipeElement("source2"));
    	junc.changeCoordinates(100, 400);
    	pipelineObjects.add(junc);
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe1"));
    	pipeObjects.add(pipe);
    	

    	//cisternák
    	junc = new CisternGraphic((Cistern)game.getPipeElement("cistern1"));
    	junc.changeCoordinates(700, 200);
    	pipelineObjects.add(junc);
    	junc = new CisternGraphic((Cistern)game.getPipeElement("cistern2"));
    	junc.changeCoordinates(700, 400);
    	pipelineObjects.add(junc);
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe15"));
    	pipeObjects.add(pipe);
    	
    	//bal oldali pumpák
    	junc = new PumpGraphic((Pump)game.getPipeElement("pump1"));
    	junc.changeCoordinates(250, 200);
    	pipelineObjects.add(junc);
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe2"));
    	pipeObjects.add(pipe);
    	junc = new PumpGraphic((Pump)game.getPipeElement("pump2"));
    	junc.changeCoordinates(250, 400);
    	pipelineObjects.add(junc);
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe3"));
    	pipeObjects.add(pipe);
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe4"));
    	pipeObjects.add(pipe);
    	
    	//középső pumpák
    	
    	
    }
    /**
     * Kirajzolja a folyamatban levő játék elemeit az ablakba.
     */
    private static void drawMapGraphics() {
    	Graphics graphics = window.getGraphics();
    	
    	for	(PipeGraphic i : pipeObjects) {
    		i.draw(graphics);
    	}
    	for	(PipelineGraphic i : pipelineObjects) {
    		i.draw(graphics);
    	}
    	for	(PlayerGraphic i : playerObjects) {
    		i.draw(graphics);
    	}
    }
    
}

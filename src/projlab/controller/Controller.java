package projlab.controller;

import projlab.graphics.*;
import projlab.sivatag.*;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.TimerTask;

public class Controller {
    public static Object lock = new Object();

    private static JFrame frame;
    private static JPanel buttonPanel;
    private static JLabel titleLabel;
    private static JButton newGameButton;
    private static JButton loadGameButton;
    private static JButton quitButton;


    public static Game game = null;
    private static int selectedPlayerIndex = -1;
    public static PlayerGraphic selectedPlayer = null;
    public static ArrayList<PlayerGraphic> playerObjects = new ArrayList<PlayerGraphic>();
    public static ArrayList<JunctionGraphic> pipelineObjects = new ArrayList<JunctionGraphic>();
    public static ArrayList<PipeGraphic> pipeObjects = new ArrayList<PipeGraphic>();

    public static MoveButton moveButton = new MoveButton(null);
    public static BreakButton breakButton = new BreakButton(null);
    public static RepairButton repairButton = new RepairButton(null);
    public static PlaceButton placeButton = new PlaceButton(null);
    public static PickupButton pickupButton = new PickupButton(null);
    public static StickyButton stickyButton = new StickyButton(null);
    public static SlipperyButton slipperyButton = new SlipperyButton(null);
    public static SetInputButton setInputButton = new SetInputButton(null);
    public static SetOutputButton setOutputButton = new SetOutputButton(null);
    public static JComboBox<String> objectDropDown = new JComboBox<String>();
    
    private static BufferedImage background;
    
    public static String winningTeam;
    
    private static java.util.Timer timer = new java.util.Timer();
    

    public static void main(String[] args) {
		createWindow();
    }

    private static void startGame(){
    	frame.setVisible(false);
    	frame = new JFrame();
        frame = new JFrame("Desert Water Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(objectDropDown);
        menuBar.add(moveButton);
        menuBar.add(breakButton);
        menuBar.add(repairButton);
        menuBar.add(placeButton);
        menuBar.add(pickupButton);
        menuBar.add(stickyButton);
        menuBar.add(slipperyButton);
        menuBar.add(setInputButton);
        menuBar.add(setOutputButton);
        
        frame.setJMenuBar(menuBar);
        frame.add(new DrawPanel());
        
        frame.setVisible(true);

		createMapGraphics();
        startTimer();
        selectNextPlayer();
    }
    
    public static void newGame() {
        game = new Game();
        startGame();
    }
    public static void loadGame(String file) {
    	game = GameSerializer.loadGame(file);
    	startGame();
    }
    public static void exit() {
    	System.exit(0);
    }
    
    private static void startTimer(){
    	game.startGame();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                while (game.getGameTimeLeft() >= 0){
                    drawMapGraphics();
                    winningTeam = getWinningTeam();
                }
                //TODO winning team kijelzése (?)
                game.endGame();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);
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
        frame = new JFrame("Desert Water Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        titleLabel = new JLabel("Desert Water Network", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        newGameButton = new JButton("New Game");
        newGameButton.setForeground(Color.BLACK);
        newGameButton.setOpaque(true);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	newGame();
            }
        });

        loadGameButton = new JButton("Load Game");
        loadGameButton.setForeground(Color.BLACK);
        loadGameButton.setOpaque(true);
        loadGameButton.setContentAreaFilled(false);
        loadGameButton.setBorderPainted(false);
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO a fájlból olvasás és a fájlnak a kiválasztása, így jó-e
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadGame(selectedFile.getAbsolutePath());
                }
            }
        });

        quitButton = new JButton("Quit");
        quitButton.setForeground(Color.BLACK);
        quitButton.setOpaque(true);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(quitButton);

        frame.setContentPane(new JLabel(new ImageIcon("menu.jpg")));
        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        try {
            background = ImageIO.read(new File("menu.jpg"));
        }
        catch (Exception e) {
        	
        }
        
        frame.setVisible(true);
    }
    
    private static void createMapGraphics() {
    	PipeGraphic pipe;
    	
    	//források
    	JunctionGraphic s1 = new SourceGraphic((Source)game.getPipeElement("source1"));
    	s1.changeCoordinates(100, 200);
    	pipelineObjects.add(s1);
    	
    	JunctionGraphic s2 = new SourceGraphic((Source)game.getPipeElement("source2"));
    	s2.changeCoordinates(100, 400);
    	pipelineObjects.add(s2);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe1"));
    	pipe.setFirstObject(s1);
    	pipe.setSecondObject(s2);
    	pipeObjects.add(pipe);
    	    	
    	//bal oldali pumpák
    	JunctionGraphic p1 = new PumpGraphic((Pump)game.getPipeElement("pump1"));
    	p1.changeCoordinates(300, 200);
    	pipelineObjects.add(p1);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe2"));
    	pipe.setFirstObject(s1);
    	pipe.setSecondObject(p1);
    	pipeObjects.add(pipe);
    	
    	JunctionGraphic p2 = new PumpGraphic((Pump)game.getPipeElement("pump2"));
    	p2.changeCoordinates(300, 400);
    	pipelineObjects.add(p2);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe3"));
    	pipe.setFirstObject(s2);
    	pipe.setSecondObject(p2);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe4"));
    	pipe.setFirstObject(p1);
    	pipe.setSecondObject(p2);
    	pipeObjects.add(pipe);
    	
    	
    	//középső pumpák
    	JunctionGraphic p3 = new PumpGraphic((Pump)game.getPipeElement("pump3"));
        p3.changeCoordinates(500, 100);
    	pipelineObjects.add(p3);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe5"));
    	pipe.setFirstObject(p1);
    	pipe.setSecondObject(p3);
    	pipeObjects.add(pipe);
    	
    	JunctionGraphic p4 = new PumpGraphic((Pump)game.getPipeElement("pump4"));
    	p4.changeCoordinates(500, 300);
    	pipelineObjects.add(p4);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe6"));
    	pipe.setFirstObject(p1);
    	pipe.setSecondObject(p4);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe7"));
    	pipe.setFirstObject(p2);
    	pipe.setSecondObject(p4);
    	pipeObjects.add(pipe);
    	
    	JunctionGraphic p5 = new PumpGraphic((Pump)game.getPipeElement("pump5"));
    	p5.changeCoordinates(500, 500);
    	pipelineObjects.add(p5);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe8"));
    	pipe.setFirstObject(p2);
    	pipe.setSecondObject(p5);
    	pipeObjects.add(pipe);
    	
    	//jobb oldali pumpák
    	JunctionGraphic p6 = new PumpGraphic((Pump)game.getPipeElement("pump6"));
    	p6.changeCoordinates(700, 200);
    	pipelineObjects.add(p6);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe9"));
    	pipe.setFirstObject(p3);
    	pipe.setSecondObject(p6);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe10"));
    	pipe.setFirstObject(p4);
    	pipe.setSecondObject(p6);
    	pipeObjects.add(pipe);
    	
    	JunctionGraphic p7 = new PumpGraphic((Pump)game.getPipeElement("pump7"));
    	p7.changeCoordinates(700, 400);
    	pipelineObjects.add(p7);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe11"));
    	pipe.setFirstObject(p4);
    	pipe.setSecondObject(p7);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe12"));
    	pipe.setFirstObject(p5);
    	pipe.setSecondObject(p7);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe13"));
    	pipe.setFirstObject(p6);
    	pipe.setSecondObject(p7);
    	pipeObjects.add(pipe);
    	
    	
    	//cisternák
    	JunctionGraphic c1 = new CisternGraphic((Cistern)game.getPipeElement("cistern1"));
    	c1.changeCoordinates(900, 200);
    	pipelineObjects.add(c1);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe14"));
    	pipe.setFirstObject(p6);
    	pipe.setSecondObject(c1);
    	pipeObjects.add(pipe);
    	
    	JunctionGraphic c2 = new CisternGraphic((Cistern)game.getPipeElement("cistern2"));
    	c2.changeCoordinates(900, 400);
    	pipelineObjects.add(c2);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe15"));
    	pipe.setFirstObject(p7);
    	pipe.setSecondObject(c2);
    	pipeObjects.add(pipe);
    	
    	pipe = new PipeGraphic((Pipe)game.getPipeElement("pipe16"));
    	pipe.setFirstObject(c1);
    	pipe.setSecondObject(c2);
    	pipeObjects.add(pipe);
    	

    	playerObjects.add(new PlayerGraphic((Plumber)game.getPlayer("plumber1"), false));
    	playerObjects.add(new PlayerGraphic((Plumber)game.getPlayer("plumber2"), true));
    	playerObjects.add(new PlayerGraphic((Saboteur)game.getPlayer("saboteur1")));
    	playerObjects.add(new PlayerGraphic((Saboteur)game.getPlayer("saboteur2")));
    }
    /**
     * Kirajzolja a folyamatban levő játék elemeit az ablakba.
     */
    private static void drawMapGraphics() {
    	frame.getContentPane().repaint();
    }
    /**
     * 
     */
    public static void selectNextPlayer() {
    	selectedPlayerIndex++;
    	if (selectedPlayerIndex >= playerObjects.size() || selectedPlayerIndex < 0)
			selectedPlayerIndex = 0;
    	selectedPlayer = playerObjects.get(selectedPlayerIndex);
    	selectedPlayer.select();
    }
    
    public static class DrawPanel extends JPanel {

    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
        	g.drawImage(background, 0, 0, frame.getWidth(), frame.getHeight(), null);
        	
        	g.drawString(Integer.toString(game.getGameTimeLeft() / 60) + ":" + Integer.toString(game.getGameTimeLeft() % 60), 20, 50);

        	g.drawString("Plumber score: " + Integer.toString(game.getStoredWaterAmount()), 800, 50);
        	g.drawString("Saboteur score: " + Integer.toString(game.getSpilledWaterAmount()), 800, 75);
        	
        	for	(PipeGraphic i : pipeObjects) {
        		i.draw(g);
        	}
        	for	(PipelineGraphic i : pipelineObjects) {
        		i.draw(g);
        	}
        	for	(PlayerGraphic i : playerObjects) {
        		i.draw(g);
        	}
    	}
    }
}

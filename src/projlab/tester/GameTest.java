package projlab.tester;

import projlab.controller.GameSerializer;
import projlab.sivatag.*;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map.Entry;

public class GameTest {
	
	protected static void runList(String type) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();

		switch (type) {
		case "player":
			for (String i : Main.game.getPlayers().keySet()) {
				Main.push(i);
			}
			break;
		case "pipe":
			for (Entry<String, WaterFlow> i : elements.entrySet()) {
				if (i.getValue().getClass() == Pipe.class)
					Main.push(i.getKey());
			}
			break;
		case "source":
			for (Entry<String, WaterFlow> i : elements.entrySet()) {
				if (i.getValue().getClass() == Source.class)
					Main.push(i.getKey());
			}
			break;
		case "cistern":
			for (Entry<String, WaterFlow> i : elements.entrySet()) {
				if (i.getValue().getClass() == Cistern.class)
					Main.push(i.getKey());
			}
			break;
		case "pump":
			for (Entry<String, WaterFlow> i : elements.entrySet()) {
				if (i.getValue().getClass() == Pump.class)
					Main.push(i.getKey());
			}
			break;
		}
	}
	
	
	protected static void runSave(String path) {
		if (GameSerializer.saveGame(Main.game, path))
			Main.push("Loading successful.");
		else
			Main.push("Could not load game.");
	}
	protected static void runLoad(String path) {
		Main.game = GameSerializer.loadGame(path); 
		
		if (Main.game != null)
			Main.push("Loading successful.");
		else
			Main.push("Could not load game.");
	}
	
	
	protected static void runNewGame() {
		Main.game = new Game();
		Main.game.newGame();
		Main.push("New Game created, please select your team!");
	}
		
	protected static void runSelectTeam(String team) throws InvalidKeyException {
		if (team.equals("p")) {
			for (int i = 1; i <= 2; i++) {
				WaterFlow obj = Main.game.getPipeElements().get("source" + i);
				if (!obj.hasPlayer()) {
					Plumber p = new Plumber(obj);
					Main.game.addPlayer(p);
					Main.push("Your team is the Plumber");
					return;
				}
			}
		}
		else if (team.equals("s")) {
			for (int i = 1; i <= 2; i++) {
				WaterFlow obj = Main.game.getPipeElements().get("cistern" + i);
				if (!obj.hasPlayer()) {
					Saboteur p = new Saboteur(obj);
					Main.game.addPlayer(p);
					Main.push("Your team is the Saboteur");
					return;
				}
			}
		}
		Main.push("Player could not be placed.");
	}
}

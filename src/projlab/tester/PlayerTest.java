package projlab.tester;

import java.util.ArrayList;

import projlab.sivatag.Player;
import projlab.sivatag.WaterFlow;
import java.util.HashMap;
import java.util.Map.Entry;

public class PlayerTest {
	
	protected static void runStep(String playerid, String newposid) {
		//Get the player
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		//Determine the new position
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow newpos = elements.get(newposid);
		if (player == null || newpos == null) {
			Main.push("The player couldn’t move");
			return;
		}
		
		//Record the old position
		String oldposid = null;
		WaterFlow oldpos = player.getPosition();
		
		
		//Perform the move, actualpos may not be newpos
		WaterFlow actualpos = player.movePlayer(newpos);
		
		//Get ids to display
		for (Entry<String, WaterFlow> i : elements.entrySet()) {
			if (i.getValue() == oldpos)
				oldposid = i.getKey();
			if (i.getValue() == newpos)
				newposid = i.getKey();
		}
		
		//Player hasn't moved
		if (actualpos == oldpos)
			Main.push("The player couldn’t move");
		else
			Main.push("The player moved from " + oldposid + " to " + newposid);
	}
	
	
	
	protected static void runMakePipeSlippery(String playerid) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Couldn't make pipe slippery");
			return;
		}
		
		if (player.InputCallback_MakePipeSlippery()) {
			Main.push("Pipe successfully turned into slippery pipe");
		}
		else {
			Main.push("Couldn't make pipe slippery");
		}
	}
	protected static void runMakePipeSticky(String playerid) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Couldn't make pipe sticky");
			return;
		}
		
		if (player.InputCallback_MakePipeSticky()) {
			Main.push("Pipe successfully turned into sticky pipe");
		}
		else {
			Main.push("Couldn't make pipe sticky");
		}
	}
	
	
	
	protected static void runSetPumpInput(String playerid, int index) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Couldn’t set the pump's input.");
			return;
		}
		
		if (player.InputCallback_SetInput(index))
			Main.push("Pump’s input successfully set.");
		else 
			Main.push("Couldn’t set the pump's input.");
	}
	protected static void runSetPumpOutput(String playerid, int index) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Couldn’t set the pump's output.");
			return;
		}
		
		if (player.InputCallback_SetOutput(index))
			Main.push("Pump’s output successfully set.");
		else
			Main.push("Couldn’t set the pump's output.");
	}
	
	
	protected static void runRepairThroughPlayer(String playerid) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Repair failed. " + playerid + " is not a valid object.");
			return;
		}
			
		if (player.InputCallback_Break())
			Main.push("Repair successful.");
		else 
			Main.push("Repair failed. Possible reasons: " + playerid + " cannot repair, element is not broken, element cannot be repaired.");
	}
	protected static void runBreakThroughPlayer(String playerid) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Break failed. " + playerid + " is not a valid object.");
			return;
		}
		
		if (player.InputCallback_Break())
			Main.push("Break successful.");
		else 
			Main.push("Break failed. Possible reasons: element is already broken, element cannot be broken.");
	}
	
	
	
	protected static void runPickupObject(String playerid, int index) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Pickup failed. " + playerid + " is not a valid object.");
			return;
		}
		
		if (player.InputCallback_Pickup(index) != null)
			Main.push("Object picked up successfully.");
		else
			Main.push("Pickup failed. Possible reasons: neighborIndex invalid, " + playerid + " cannot pickup, " + playerid + " already carrying something");
	}
	protected static void runPlaceObject(String playerid) {
		HashMap<String, Player> players = Main.game.getPlayers();
		Player player = players.get(playerid);
		if (player == null) {
			Main.push("Place failed. " + playerid + " is not a valid object.");
			return;
		}
		
		if (player.InputCallback_Place())
			Main.push("Object placed successfully.");
		else
			Main.push("Place failed. Possible reasons: " + playerid + " is not carrying an object, neighbor association could not be created");
		
	}
}

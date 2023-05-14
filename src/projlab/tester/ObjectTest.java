package projlab.tester;

import projlab.sivatag.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map.Entry;

public class ObjectTest {

	protected static void runAddNeighbor(String id1, String id2) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj1 = elements.get(id1);
		WaterFlow obj2 = elements.get(id2);
		if (obj1 == null || obj2 == null) {
			Main.push("Couldn't add the neighbor");
			return;
		}
		
		if (obj1.addNeighbor(obj2))
			Main.push(id1 + " and " + id2 + " are neighbors");
		else
			Main.push("Couldn't add the neighbor");
	}
	protected static void runRemoveNeighbor(String id1, String id2) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj1 = elements.get(id1);
		WaterFlow obj2 = elements.get(id2);
		if (obj1 == null || obj2 == null) {
			Main.push("Couldn't add the neighbor");
			return;
		}
		
		obj1.removeNeighbor(obj2);
		Main.push(id1 + " and " + id2 + " aren't neighbors");
	}
	protected static void runRemoveNeighbors(String id) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj = elements.get(id);
		if (obj == null) {
			Main.push("Couldn’t remove the neighbors");
			return;
		}
		
		obj.removeNeighbors();
		Main.push(id + " have 0 neighbor");
	}
	protected static void runPumpRC() {
		if (Main.rcDisable) {
			Main.push("The corruption failed");
			return;
		}
		
		ArrayList<Pump> arr = new ArrayList<Pump>();
		for (Entry<String, WaterFlow> i : Main.game.getPipeElements().entrySet())
			if (i.getValue().getClass() == Pump.class)
				arr.add((Pump)i.getValue());
		
		if (arr.size() == 0) {
			Main.push("The corruption failed");
			return;
		}
				
		int index = new Random().nextInt(arr.size());
		if (Main.rcDisable)
			index = 0;
		
		arr.get(index).breakObject(true);
	}
	
	
	protected static void runCreatePump(String cisternid) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		
		Cistern cistern = null;
		if (cisternid != null) {
			//get cistern from id
			WaterFlow obj = elements.get(cisternid);
			if (obj.getClass() == Cistern.class)
				cistern = (Cistern)obj;
		}
		else {
			//pick random cistern
			//filter elements to cisterns
			ArrayList<Cistern> arr = new ArrayList<Cistern>();
			for (Entry<String, WaterFlow> i : elements.entrySet())
				if (i.getValue().getClass() == Cistern.class)
					arr.add((Cistern)i.getValue());
			
			if (arr.size() == 0) {
				Main.push("Couldn’t create pipe");
				return;
			}
			
			//pick randomly, if deterministic behavior is disabled 
			int index = new Random().nextInt(arr.size());
			if (Main.rcDisable)
				index = 0;
			
			cistern = arr.get(index);
		}
		
		//create and add pump
		Pump pump = new Pump();
		Main.game.addPipeElement(pump);
		if (cistern.addNeighbor(pump))
			Main.push("Pump successfully added to " + cisternid + ".");
		else
			Main.push("Couldn’t create pump");
	}
	protected static void runCreatePipe(String cisternid) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		
		Cistern cistern = null;
		if (cisternid != null) {
			//get cistern from id
			WaterFlow obj = elements.get(cisternid);
			if (obj.getClass() == Cistern.class)
				cistern = (Cistern)obj;
		}
		else {
			//pick random cistern
			//filter elements to cisterns
			ArrayList<Cistern> arr = new ArrayList<Cistern>();
			for (Entry<String, WaterFlow> i : elements.entrySet())
				if (i.getValue().getClass() == Cistern.class)
					arr.add((Cistern)i.getValue());
			
			if (arr.size() == 0) {
				Main.push("Couldn’t create pipe");
				return;
			}
			
			//pick randomly, if deterministic behavior is disabled 
			int index = new Random().nextInt(arr.size());
			if (Main.rcDisable)
				index = 0;
			
			cistern = arr.get(index);
		}
		
		//create and add pipe
		Pipe pipe = new Pipe();
		Main.game.addPipeElement(pipe);
		if (cistern.addNeighbor(pipe))
			Main.push("Pipe successfully added to " + cisternid + ".");
		else
			Main.push("Couldn’t create pipe");
	}
	
	protected static void runSetInput(String objid, int index) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj = elements.get(objid);
		if (obj == null)
			Main.push("Couldn't set the " + objid + "'s input.");
		
		if (obj.setInput(new int[] { index }))
			Main.push(objid + "'s input successfully set.");
		else
			Main.push("Couldn't set the " + objid + "'s input.");
	}
	protected static void runSetOutput(String objid, int index) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj = elements.get(objid);
		if (obj == null)
			Main.push("Couldn't set the " + objid + "'s output.");
		
		if (obj.setOutput(index))
			Main.push(objid + "'s output successfully set.");
		else
			Main.push("Couldn't set the " + objid + "'s output.");
	}
	

	protected static void runRepairObject(String objid) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj = elements.get(objid);
		if (obj == null)
			Main.push("Repair failed. " + obj + " is not a valid object.");
		
		if (obj.repairObject())
			Main.push("Repair successful.");
		else 
			Main.push("Repair failed. Possible reasons: element is not broken, element cannot be repaired.");
	}
	protected static void runBreakObject(String objid) {
		HashMap<String, WaterFlow> elements = Main.game.getPipeElements();
		WaterFlow obj = elements.get(objid);
		if (obj == null)
			Main.push("Break failed. " + obj + " is not a valid object.");
		
		if (obj.breakObject(true))
			Main.push("Break successful.");
		else 
			Main.push("Break failed. Possible reasons: element is already broken, element cannot be broken.");
	}
}

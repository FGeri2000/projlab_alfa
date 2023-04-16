package projlab.sivatag;

import java.util.LinkedList;

/**
 * Absztrakt ősosztály a csőhálózat elemeire, amely lehetővé teszi a játékosok mozgását és a víz folyását a csőhálózat elemei között.
 * @author fgreg
 *
 */
public abstract class WaterFlow implements Movable, PlayerMovement, Repairable {
	/**
	 * A csőhálózat elemének szomszédjai.
	 */
	public LinkedList<WaterFlow> neighbors = new LinkedList<WaterFlow>();
	/**
	 * A csőhálózati elemen jelenleg álló játékosok listája.
	 */
	public LinkedList<Player> players = new LinkedList<Player>();
	/**
	 * Azon szomszédoknak az indexei, amelyekből ebbe az elembe folyik a víz.
	 */
	public LinkedList<Integer> input = new LinkedList<Integer>();
	/**
	 * Annak a szomszédnak az indexe, ahova ebből az elemből a víz folyik.
	 */
	public int output = -1;
	/**
	 * Az objektum által tárolt víz mennyisége.
	 */
	public int buffer = 0;
	/**
	 * Az objektum által tárolható víz maximális mennyisége. Negatív ha végtelen.
	 */
	public int bufferCapacity = -1;
	/**
	 * Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	public int transferCapacity = 20;
	
	
	
	/**
	 * Visszatér az adott csőhálózati elem szomszédaival.
	 * @return A csőhálózati elem szomszédainak listája, másolt struktúra.
	 */
	public LinkedList<WaterFlow> GetNeighbors() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "GetNeighbors()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "neighbors");
		return neighbors;
	}
	/**
	 * Hozzáadja az adott csőhálózati elemet ehhez az elemhez, mint annak a szomszédja.<br>
	 * Biztosítja a kölcsönös szomszédi kapcsolatot.
	 * @return Igaz, ha a szomszédság kialakult, különben hamis.
	 */
	public boolean AddNeighbor(WaterFlow neighbor) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "AddNeighbor(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(neighbor) + ")");

		projlab.skeleton.ConditionQuerier.SetDefaultBoolean(false);
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Null a paraméter?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		
		neighbors.add(neighbor);
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		neighbor.GetNeighbors();
		
		projlab.skeleton.ConditionQuerier.SetDefaultBoolean(true);
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Tartalmazza már a szomszéd ezt az elemet?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.PushCaller(this);
			neighbor.AddNeighbor(this);
			
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
	}
	/**
	 * Eltávolítja az adott csőhálózati elemet erről az elemről, mint annak a szomszédja, akár tartalmazza, akár nem.
	 * @param neighbor Az eltávolítandó szomszéd.
	 */
	public void RemoveNeighbor(WaterFlow neighbor) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "RemoveNeighbor(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(neighbor) + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
	}
	/**
	 * Eltávolítja az adott csőhálózati elem összes szomszédját.
	 */
	public void RemoveNeighbors() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "RemoveNeighbors()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
	}
	/**
	 * Beállítja az adott index-ekkel jelölt szomszédokat, mint bemeneti elemek.
	 * @implSpec Alapértelmezetten csak egy hosszú halmazokat fogad el.
	 * @param inputs Az elem bemeneteiként beállítandó elemek halmaza.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetInput(int[] inputs) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "SetInput(int[" + inputs.length + "])");
		
		if (inputs.length > 1) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		
		if (neighbors.size() <= inputs[0] || inputs[0] < 0) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		
		input.clear();
		input.add(inputs[0]);
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
		return true;
	}
	/**
	 * Beállítja az adott index-el jelölt szomszédot, mint kimeneti elem.
	 * @param output A kimeneti elemként használni kívánt szomszéd indexe.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetOutput(int output) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "SetOutput(" + output + ")");

		if (neighbors.size() <= output) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}

		this.output = output;
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
		return true;
	}
	/**
	 * Adott időközönként meghívódik a kontroller által.<br>Biztosítja a víz áramlását a hálózatban.
	 */
	public abstract void FlowTick();
	
	/**
	 * Átvesz az adott szomszédtól egy adott mennyiségű vizet, amennyiben van neki hely (és pumpa esetén nincs lerobbanva).<br>
	 * Visszaadja az átvett víz mennyiségét.
	 * @param from A szomszéd, ami átadni kívánja a vizet.
	 * @param amount Az átadni kívánt víz mennyisége.
	 * @return A ténylegesen átvett víz mennyisége.
	 */
	public int ReceiveWater(WaterFlow from, int amount) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "ReceiveWater(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(from) + "," + amount + ")");
		
		for (int i : input) {
			if (neighbors.get(i) == from) {
				int bufferSize = projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mi a fogadó elem pufferének kapacitása?");
				int buffer = projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz van a fogadó elem pufferében?");
				int result = Math.min(bufferSize - buffer, amount);
				projlab.skeleton.CallHierarchyWriter.ExitFunction(this, Integer.toString(result));
				return result;
			}
		}
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "0");
		return 0;
	}
	/**
	 * @implNote Működése azonos minden elemtípuson.
	 */
	@Override
	public WaterFlow MovePlayer(Player player, int neighbor) {		
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "MovePlayer(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(player) + "," + neighbor + ")");
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		if (neighbors.size() <= neighbor) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, projlab.skeleton.CallHierarchyWriter.GetIdentifier(this));
			return this;
		}
		
		boolean result = neighbors.get(neighbor).PutPlayer(player);

		if (result) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, projlab.skeleton.CallHierarchyWriter.GetIdentifier(neighbors.get(neighbor)));
			return neighbors.get(neighbor);
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, projlab.skeleton.CallHierarchyWriter.GetIdentifier(this));	
			return this;
		}
	}
	/**
	 * @implNote Az alapértelmezett implementáció mindig igazat ad vissza.
	 */
	@Override
	public boolean PutPlayer(Player player) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "PutPlayer(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(player) + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean PickUp(WaterFlow oldNeighbor) {
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean PutDown(WaterFlow newNeighbor) {
		return false;
	}
}

package projlab.sivatag;

import java.util.LinkedList;

/**
 * Absztrakt ősosztály a csőhálózat elemeire, amely lehetővé teszi a játékosok mozgását és a víz folyását a csőhálózat elemei között.
 * @author fgreg
 *
 */
public abstract class WaterFlow {
	/**
	 * A csőhálózat elemének szomszédjai.
	 */
	protected LinkedList<WaterFlow> neighbors = new LinkedList<WaterFlow>();
	/**
	 * A csőhálózati elemen jelenleg álló játékosok listája.
	 */
	protected LinkedList<Player> players = new LinkedList<Player>();
	/**
	 * Azon szomszédoknak az indexei, amelyekből ebbe az elembe folyik a víz.
	 */
	protected LinkedList<Integer> input = new LinkedList<Integer>();
	/**
	 * Annak a szomszédnak az indexe, ahova ebből az elemből a víz folyik.
	 */
	protected int output = -1;
	/**
	 * Az objektum által tárolt víz mennyisége.
	 */
	public int buffer = 0;
	/**
	 * Az objektum által tárolható víz maximális mennyisége. Negatív ha végtelen.
	 */
	protected int bufferCapacity = -1;
	/**
	 * Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	protected int transferCapacity = 20;
	/**
	 * Igaz, ha az elem jelenleg egy játékos kezében van.
	 */
	protected boolean carried = false;
	
	/**
	 * Létrehoz egy WaterFlow objektumot az alapértelmezett kapacitásokkal.
	 */
	public WaterFlow() {
		
	}
	/**
	 * Létrehoz egy WaterFlow objektumot a megadott kapacitásokkal.
	 * @param bufferCapacity Az objektum által tárolható víz maximális mennyisége. Negatív ha végtelen.
	 * @param transferCapacity Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	public WaterFlow(int bufferCapacity, int transferCapacity) {
		this.bufferCapacity = bufferCapacity;
		this.transferCapacity = transferCapacity;
	}
	
	
	/**
	 * Visszatér az adott csőhálózati elem szomszédaival.
	 * @return A csőhálózati elem szomszédainak listája, másolt struktúra.
	 */
	public LinkedList<WaterFlow> GetNeighbors() {
		return neighbors;
	}
	/**
	 * Hozzáadja az adott csőhálózati elemet ehhez az elemhez, mint annak a szomszédja.<br>
	 * Biztosítja a kölcsönös szomszédi kapcsolatot.
	 * @return Igaz, ha a szomszédság kialakult, különben hamis.
	 */
	public boolean AddNeighbor(WaterFlow neighbor) {
		if (neighbor == null)
			return false;
		
		neighbors.add(neighbor);
		
		LinkedList<WaterFlow> otherNeighbors = neighbor.GetNeighbors();
		if (!otherNeighbors.contains(this)) {
			neighbor.AddNeighbor(this);
		}
		return true;
	}
	/**
	 * Eltávolítja az adott csőhálózati elemet erről az elemről, mint annak a szomszédja, akár tartalmazza, akár nem.
	 * @param neighbor Az eltávolítandó szomszéd.
	 */
	public void RemoveNeighbor(WaterFlow neighbor) {
		if (neighbors.contains(neighbor)) {
			neighbors.remove(neighbor);
			neighbor.RemoveNeighbor(this);
		}
	}
	/**
	 * Eltávolítja az adott csőhálózati elem összes szomszédját.
	 */
	public void RemoveNeighbors() {
		for (int i = 0; i < neighbors.size(); ) {
			RemoveNeighbor(neighbors.get(i));
		}
	}
	/**
	 * Beállítja az adott index-ekkel jelölt szomszédokat, mint bemeneti elemek.
	 * @implSpec Alapértelmezetten csak egy hosszú halmazokat fogad el.
	 * @param inputs Az elem bemeneteiként beállítandó elemek halmaza.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetInput(int[] inputs) {
		if (inputs.length != 1) {
			return false;
		}
		else if (neighbors.size() <= inputs[0] || inputs[0] < 0) {
			return false;
		}
		
		input.clear();
		input.add(inputs[0]);
		return true;
	}
	/**
	 * Beállítja az adott index-el jelölt szomszédot, mint kimeneti elem.
	 * @param output A kimeneti elemként használni kívánt szomszéd indexe.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetOutput(int output) {
		if (neighbors.size() <= output || output < 0) {
			return false;
		}

		this.output = output;
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
		if (neighbors.get(output) == from) {
			return Math.min(this.bufferCapacity - buffer, amount);
		}
		return 0;
	}
	/**
	 * @implNote Működése azonos minden elemtípuson.
	 */
	public WaterFlow MovePlayer(Player player, int neighbor) {
		if (neighbors.size() <= neighbor) {
			return this;
		}
		else if (neighbors.get(neighbor).PutPlayer(player)) {
			players.remove(player);
			return neighbors.get(neighbor);
		}
		
		return this;
	}
	/**
	 * @implNote Az alapértelmezett implementáció mindig igazat ad vissza.
	 */
	public boolean PutPlayer(Player player) {
		players.add(player);
		return true;
	}

	/**
	 * Meghívódik, ha egy játékos az adott csőhálózati elemet megjavítja.
	 * @return Igaz, ha a javítás sikeres, hamis ha sikertelen vagy a tárgy nem törött.
	 */
	public abstract boolean Repair();
	/**
	 * Meghívódik, ha az adott csőhálózati elemet tönkreteszik.
	 * @param controller Igaz, ha a tönkretételt a kontroller kezdeményezi játékos helyett.
	 * @return Igaz, ha a tönkretétel sikeres, hamis ha sikertelen vagy a tárgy már törött.
	 */
	public abstract boolean Break(boolean controller);
	
	/**
	 * 
	 */
	public boolean PickUp(WaterFlow oldNeighbor) {
		return false;
	}

	/**
	 * Az objektumpéldány kölcsönös szomszédsági viszonyba kerül egy másik WaterFlow példánnyal, majd beállítja a megfelelő be- és kimenetet.
	 * @param newNeighbor A hívó tartózkodási helye
	 * @return Igaz, ha sikeres a kapcsolat kialakítása, és a bekötés.
	 */
	public boolean PutDown(WaterFlow newNeighbor) {
		if (!newNeighbor.AddNeighbor(this)) {
			return false;
		}

		/*
		int idx = neighbors.indexOf(newNeighbor);

		boolean success = false;
		if (!input.isEmpty() && output <= 0) {
			success = SetInput(new int[] { idx });
		} else if (input.isEmpty() && output >= 0) {
			success = SetOutput(idx);
		}

		if (!success) {
			RemoveNeighbor(newNeighbor);
			return false;
		}
		*/

		return true;
	}

}

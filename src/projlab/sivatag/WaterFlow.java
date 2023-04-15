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
	protected int buffer = 0;
	/**
	 * Az objektum által tárolható víz maximális mennyisége. Negatív ha végtelen.
	 */
	protected int bufferCapacity = -1;
	/**
	 * Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	protected int transferCapacity = 20;
	
	
	
	/**
	 * Visszatér az adott csőhálózati elem szomszédaival.
	 * @return A csőhálózati elem szomszédainak listája, másolt struktúra.
	 */
	public WaterFlow[] GetNeighbors() {
		System.out.print("WaterFlow[] WaterFlow.GetNeighbors()");

		return new WaterFlow[] {};
	}
	/**
	 * Hozzáadja az adott csőhálózati elemet ehhez az elemhez, mint annak a szomszédja.<br>
	 * Biztosítja a kölcsönös szomszédi kapcsolatot.
	 * @return Igaz, ha a szomszédság kialakult, különben hamis.
	 */
	public boolean AddNeighbor(WaterFlow neighbor) {
		System.out.print("boolean WaterFlow.AddNeighbors()");

		return false;
	}
	/**
	 * Eltávolítja az adott csőhálózati elemet erről az elemről, mint annak a szomszédja, akár tartalmazza, akár nem.
	 * @param neighbor Az eltávolítandó szomszéd.
	 */
	public void RemoveNeighbor(WaterFlow neighbor) {
		System.out.print("void WaterFlow.RemoveNeighbor(WaterFlow)");
	}
	/**
	 * Eltávolítja az adott csőhálózati elem összes szomszédját.
	 */
	public void RemoveNeighbors() {
		System.out.print("void WaterFlow.RemoveNeighbors()");
	}
	/**
	 * Beállítja az adott index-ekkel jelölt szomszédokat, mint bemeneti elemek.
	 * @implSpec Alapértelmezetten csak egy hosszú halmazokat fogad el.
	 * @param inputs Az elem bemeneteiként beállítandó elemek halmaza.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetInput(int[] inputs) {
		System.out.print("boolean WaterFlow.SetInput(int[])");

		return false;
	}
	/**
	 * Beállítja az adott index-el jelölt szomszédot, mint kimeneti elem.
	 * @param output A kimeneti elemként használni kívánt szomszéd indexe.
	 * @return Igaz, ha a beállítás sikeres, egyébként hamis.
	 */
	public boolean SetOutput(int output) {
		System.out.print("boolean WaterFlow.SetOutput(int)");

		return false;
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
		System.out.print("int WaterFlow.ReceiveWater(WaterFlow, int)");

		return 0;
	}
	/**
	 * @implNote Működése azonos minden elemtípuson.
	 */
	@Override
	public WaterFlow MovePlayer(Player player, int neighbor) {
		System.out.print("WaterFlow WaterFlow.MovePlayer(Player, int)");
		
		return null;
	}
	/**
	 * @implNote Az alapértelmezett implementáció mindig igazat ad vissza.
	 */
	@Override
	public boolean PutPlayer(Player player) {
		System.out.print("boolean WaterFlow.PutPlayer(Player)");
		
		return false;
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

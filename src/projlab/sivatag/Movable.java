package projlab.sivatag;

/**
 * Interfész a csőhálózat elemeire, amely lehetővé teszi az elem játékosok általi felvételét és és mozgatását.
 * @author fgreg
 * 
 */
public interface Movable {
	/**
	 * Meghívódik, ha a játékos az adott csőhálózati elemet megkísérli felvenni.
	 * @param oldNeighbor A szomszéd, ami mellől a felvett elem el lett távolítva.
	 * @return Igaz, ha a felvétel sikeres, egyébként hamis. 
	 */
	public boolean PickUp(WaterFlow oldNeighbor);
	/**
	 * Meghívódik, ha a játékos az adott csőhálózati elemet megkísérli letenni.
	 * @param newNeighbor A szomszéd, ami mellé az elem le lett helyezve. 
	 * @return Igaz, ha a letétel sikeres, egyébként hamis.
	 */
	public boolean PutDown(WaterFlow newNeighbor);
}

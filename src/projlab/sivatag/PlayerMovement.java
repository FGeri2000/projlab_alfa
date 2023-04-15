package projlab.sivatag;

/**
 * Interfész a csőhálózat elemeire, amely lehetővé teszi a játékosok szomszédos csőelemekre való mozgatását.
 * @author fgreg
 *
 */
public interface PlayerMovement {
	/**
	 * A játékosi bemenetre meghívott nyilvános függvény.<br>
	 * Ellenőrzi az indexet, és megkísérli a karakter az adott indexű szomszédjára helyezését.
	 * @param player Az áthelyezni kívánt játékos.
	 * @param neighbor A jelenlegi elem szomszédaira érvényes index, ami meghatározza a játékos új kívánt pozícióját.
	 * @return Az új pozíció, ha az áthelyezés sikeres, a régi pozíció, ha sikertelen.
	 */
	public PlayerMovement MovePlayer(Player player, int neighbor);
	/**
	 * A csőelem által annak a szomszédján meghívott függvény, ami megpróbálja áthelyezni rá a játékost. 
	 * @param player Az áthelyezni kívánt játékos.
	 * @return Igaz, ha a játékos áthelyeződött, hamis, ha nem helyeződött át.
	 */
	public boolean PutPlayer(Player player);
}

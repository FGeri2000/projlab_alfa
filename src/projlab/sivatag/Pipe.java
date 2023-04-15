package projlab.sivatag;

/**
 * A csőhálózat eleme. Lehetővé teszi a víz folyását pumpák, források és ciszternák között.
 * @author fgreg
 *
 */
public class Pipe extends WaterFlow {
	/**
	 * Igaz, ha a cső lyukas és kifolyik belőle a víz, hamis, ha nem.
	 */
	private boolean punctured = false;
	
	/**
	 * A csőelem által annak a szomszédján meghívott függvény, ami megpróbálja áthelyezni rá a játékost.
	 * Igazzal tér vissza, ha a csövön nem áll játékos, különben hamissal.
	 */
	@Override
	public boolean PutPlayer(Player player) {
		System.out.print("boolean Pipe.PutPlayer(Player)");
		
		return false;
	}
	/**
	 * Meghívódik, ha egy játékos az adott csövet megjavítja.
	 */
	@Override
	public boolean Repair() {
		System.out.print("boolean Pipe.Repair()");
		
		return false;
	}
	/**
	 * Meghívódik, ha az adott csövet tönkreteszik.
	 * Csak akkor teszi tönkre a csövet, ha a paraméter hamis.
	 */
	@Override
	public boolean Break(boolean controller) {
		System.out.print("boolean Pipe.Break(boolean)");
		
		return false;
	}
	/**
	 * Ha a cső lyukas, törli a benne lévő vizet, különben továbbítja a kimeneti elemének.
	 */
	@Override
	public void FlowTick() {
		System.out.print("void Pipe.FlowTick()");
	}
}

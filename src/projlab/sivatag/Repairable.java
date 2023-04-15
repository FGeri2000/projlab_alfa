package projlab.sivatag;

/**
 * Interfész a csőhálózat elemeire, amely lehetővé teszi a csőhálózat elemeinek megjavítását és tönkretételét.
 * @author fgreg
 *
 */
public interface Repairable {
	/**
	 * Meghívódik, ha egy játékos az adott csőhálózati elemet megjavítja.
	 * @return Igaz, ha a javítás sikeres, hamis ha sikertelen vagy a tárgy nem törött.
	 */
	public boolean Repair();
	/**
	 * Meghívódik, ha az adott csőhálózati elemet tönkreteszik.
	 * @param controller Igaz, ha a tönkretételt a kontroller kezdeményezi játékos helyett.
	 * @return Igaz, ha a tönkretétel sikeres, hamis ha sikertelen vagy a tárgy már törött.
	 */
	public boolean Break(boolean controller);
}

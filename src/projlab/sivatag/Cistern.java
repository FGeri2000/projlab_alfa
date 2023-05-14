package projlab.sivatag;

/**
 * A csőhálózat célpontja. Méri a sikeresen belefolyt víz mennyiségét.
 * @author fgreg
 *
 */
public class Cistern extends WaterFlow {

	/**
	 * Létrehoz egy Cistern objektumot az alapértelmezett kapacitásokkal.
	 */
	public Cistern() {

	}

	/**
	 * @implNote Ciszterna típusú elem esetén érvénytelen, tehát hamisat ad vissza.
	 * @return Hamis értékkel tér vissza.
	 */
	@Override
	public boolean repairObject () {
		return false;
	}

	/**
	 * @implNote Ciszterna típusú elem esetén érvénytelen, tehát hamisat ad vissza.
	 * @return Hamis értékkel tér vissza.
	 */
	@Override
	public boolean breakObject (boolean controller) {
		return false;
	}

	/**
	 * @implNote Bármilyen hosszú halmazt elfogad.
	 * @param inputs Az elem bemeneteiként beállítandó elemek halmaza
	 * @return Igaz, ha a beállítás sikeres volt, egyébként hamis.
	 */
	@Override
	public boolean setInput(int[] inputs) {

		if (neighbors.size() <= inputs[0] || inputs[0] < 0) {
			return false;
		}

		input.clear();
		input.add(inputs[0]);
		return true;
	}

	/**
	 * Ciszterna típusú elemen érvénytelen, tehát nem csinál semmit.
	 */
	@Override
	public void flowTick() {

	}

	/**
	 * A ciszterna minden vele szomszédos elemtől képes elfogadni végtelen mennyiségű vizet.
	 * @return Az elfogadott víz mennyisége, ciszterna esetén megegyezik az <b>amount</b> paraméterrel.
	 */
	@Override
	public int receiveWater(WaterFlow from, int amount) {
		return Math.min(this.bufferCapacity - buffer, amount);
	}
}

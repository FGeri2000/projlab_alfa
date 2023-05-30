package projlab.sivatag;

public class Source extends WaterFlow {
	
	/**
	 *  Létrehoz egy Source objektumot az alapértelmezett kapacitással.
	 */
	public Source() {
		
	}

	/**
	 * Létrehoz egy Source objektumot a megadott átviteli kapacitással. Figyel arra, hogy ne lehessen 0, vagy annál kisebb értéket megadni neki.
	 * @param transferCapacity Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	
	public Source(int transferCapacity) {
		if (transferCapacity >= 0) {
			this.transferCapacity = transferCapacity;
		}
	}

	/**
	 * Megjavítja az adott forrást, viszont forrást nem lehet javítani.
	 * @return Hamissal tér vissza, hiszen forrást nem lehet javítani.
	 */
	@Override
	public boolean repairObject () {
		return false;
	}
	
	
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamissal tér vissza, hiszen forrást nem lehet tönkretenni.
	 */
	@Override
	public boolean breakObject (boolean controller) {
		return false;
	}
	
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis érték a visszatérése.
	 */
	@Override
	public boolean setInput(int[] inputs) {
		return false;
	}
	
	/**
	 * Végtelen (buffer által nem korlátozott) mennyiségű vizet továbbít a forrás kimeneti elemébe.
	 */
	@Override
	public void flowTick() {
		if (output < 0 || output >= neighbors.size()) 
			return;
		neighbors.get(output).receiveWater(this, transferCapacity);
	}
}

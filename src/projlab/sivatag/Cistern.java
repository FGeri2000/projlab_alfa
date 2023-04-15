package projlab.sivatag;

/**
 * A csőhálózat célpontja. Méri a sikeresen belefolyt víz mennyiségét.
 * @author fgreg
 *
 */
public class Cistern extends WaterFlow {
	/**
	 * @implNote Ciszterna típusú elem esetén érvénytelen, tehát hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean Repair() {
		System.out.println("boolean Cistern.Repair()");
		
		return false;
	}
	/**
	 * @implNote Ciszterna típusú elem esetén érvénytelen, tehát hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean Break(boolean controller) {
		System.out.print("boolean Cistern.Break(bool)");
				
		return false;
	}
	/**
	 * @implNote Bármilyen hosszú halmazt elfogad.
	 * @return Igaz, ha a beállítás sikeres volt, egyébként hamis.
	 */
	@Override
	public boolean SetInput(int[] inputs) {
		System.out.print("boolean Cistern.SetInput(int[])");

		return false;
	}
	/**
	 * Ciszterna típusú elemen érvénytelen, tehát nem csinál semmit.
	 */
	@Override
	public void FlowTick() {
		System.out.print("void Cistern.FlowTick()");
	}
	/**
	 * A ciszterna minden vele szomszédos elemtől képes elfogadni végtelen mennyiségű vizet.
	 * @return Az elfogadott víz mennyisége, ciszterna esetén megegyezik az <b>amount</b> paraméterrel.
	 */
	@Override
	public int ReceiveWater(WaterFlow from, int amount) {
		System.out.print("int Cistern.ReceiveWater(WaterFlow, int)");

		return 0;
	}
}

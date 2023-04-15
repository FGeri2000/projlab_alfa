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
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Repair()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * @implNote Ciszterna típusú elem esetén érvénytelen, tehát hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean Break(boolean controller) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Break(" + controller + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * @implNote Bármilyen hosszú halmazt elfogad.
	 * @return Igaz, ha a beállítás sikeres volt, egyébként hamis.
	 */
	@Override
	public boolean SetInput(int[] inputs) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "SetInput(int[" + inputs.length + "])");
		
		if (neighbors.size() <= inputs[0] || inputs[0] < 0) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
				
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
		return true;
	}
	/**
	 * Ciszterna típusú elemen érvénytelen, tehát nem csinál semmit.
	 */
	@Override
	public void FlowTick() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "FlowTick()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
	}
	/**
	 * A ciszterna minden vele szomszédos elemtől képes elfogadni végtelen mennyiségű vizet.
	 * @return Az elfogadott víz mennyisége, ciszterna esetén megegyezik az <b>amount</b> paraméterrel.
	 */
	@Override
	public int ReceiveWater(WaterFlow from, int amount) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "ReceiveWater(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(from) + "," + amount + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, Integer.toString(amount));
		return amount;
	}
}

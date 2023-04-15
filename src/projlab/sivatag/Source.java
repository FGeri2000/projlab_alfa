package projlab.sivatag;

public class Source extends WaterFlow {
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis. 
	 */
	@Override
	public boolean Repair() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Repair()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis. 
	 */
	@Override
	public boolean Break(boolean controller) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Break(" + controller + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean SetInput(int[] inputs) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "SetInput(" + inputs + ")");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * Végtelen (buffer által nem korlátozott) mennyiségű vizet továbbít a forrás kimeneti elemébe.
	 */
	@Override
	public void FlowTick() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "FlowTick()");

		if (this.output < 0 || output >= neighbors.size()) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
		}
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		int received = neighbors.get(output).ReceiveWater(this, projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz folyhat át egy csövön?"));
		
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
	}
}

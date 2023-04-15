package projlab.sivatag;

/**
 * A csőhálózat eleme. Mozgatja a vizet a csőhálózatban. Beállítható a bemenete és kimenete.
 * @author fgreg
 *
 */
public class Pump extends WaterFlow {
	/**
	 * Igaz, ha a pumpán keresztül nem folyhat a víz, egyébként hamis.
	 */
	private boolean broken = false;
	/**
	 * Igaz, ha a pumpát egyszer már áthelyezték.
	 */
	private boolean pickedUpOnce = false;
	
	/**
	 * Meghívódik, ha egy játékos az adott pumpát megjavítja. 
	 */
	@Override
	public boolean Repair() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Repair()");
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Törött a pumpa?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;	
		}
	}
	/**
	 * Meghívódik, ha az adott pumpát tönkreteszik.<br>
	 * Csak akkor teszi tönkre a pumpát, ha a paraméter igaz.
	 */
	@Override
	public boolean Break(boolean controller) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Break(" + controller + ")");
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Törött a pumpa?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;	
		}
	}
	/**
	 * Ha nincs tönkremenve a pumpa, áthelyezi a benne található vizet a kimeneti elemébe.
	 */
	@Override
	public void FlowTick() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "FlowTick()");
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Törött a pumpa?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
		}
		else {
			if (output < 0 || output >= neighbors.size()) {
				projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
			}
			
			projlab.skeleton.CallHierarchyWriter.PushCaller(this);
			int received = neighbors.get(output).ReceiveWater(this, Math.min(projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz van a pumpában?"), projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz folyhat át egy csövön?")));
			
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
		}
	}
	
	/**
	 * Meghívódik, ha a játékos az adott pumpát megkísérli felvenni.
	 * A pumpa csak egyszer áthelyezhető.
	 */
	@Override
	public boolean PickUp(WaterFlow oldNeighbor) {
		return false;
	}
}

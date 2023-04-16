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
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "PutPlayer(Player)");
		
		projlab.skeleton.ConditionQuerier.SetDefaultBoolean(false);
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Van már a csövön játékos?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;	
		}
	}
	/**
	 * Meghívódik, ha egy játékos az adott csövet megjavítja.
	 */
	@Override
	public boolean Repair() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Repair()");
		
		
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Lyukas a cső?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;	
		}
	}
	/**
	 * Meghívódik, ha az adott csövet tönkreteszik.
	 * Csak akkor teszi tönkre a csövet, ha a paraméter hamis.
	 */
	@Override
	public boolean Break(boolean controller) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "Break(" + controller + ")");
		
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Lyukas már a cső?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;	
		}
	}
	/**
	 * Ha a cső lyukas, törli a benne lévő vizet, különben továbbítja a kimeneti elemének.
	 */
	@Override
	public void FlowTick() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "FlowTick()");
		
		if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Lyukas a cső?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
		}
		else {
			if (this.output < 0 || output >= neighbors.size()) {
				projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
			}
			
			projlab.skeleton.CallHierarchyWriter.PushCaller(this);			
			int received = neighbors.get(output).ReceiveWater(this, Math.min(projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz van a csőben?"), projlab.skeleton.ConditionQuerier.QueryUserForInteger("Mennyi víz folyhat át egy csövön?")));
			
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "void");
		}
	}
	/**
	 * Meghívódik, ha a játékos az adott csőhálózati elemet megkísérli felvenni.
	 * @param oldNeighbor azt a szomszédot jelöli, amelyik mellől az elem el lett távolítva.
	 * @return Igaz, ha a felvétel sikeres, egyébként hamis.
	 */
	@Override
	public boolean PickUp(WaterFlow oldNeighbor){
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this,"PickUp(" + projlab.skeleton.CallHierarchyWriter.GetIdentifier(oldNeighbor) + ")");

		projlab.skeleton.ConditionQuerier.SetDefaultBoolean(false);
		if(projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Fogja valamelyik játékos a csövet?")){
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this,"false");
			return false;
		}
		else if (projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Van a csövön játékos?")) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this,"false");
			return false;
		}
		else{
			if(projlab.skeleton.ConditionQuerier.QueryUserForBoolean("Van szabad vége a csőnek?")){
				projlab.skeleton.CallHierarchyWriter.ExitFunction(this,"true");
				return true;
			}
			else{
				projlab.skeleton.CallHierarchyWriter.PushCaller(this);
				oldNeighbor.RemoveNeighbor(this);
				projlab.skeleton.CallHierarchyWriter.PushCaller(oldNeighbor);
				this.RemoveNeighbor(oldNeighbor);
				projlab.skeleton.CallHierarchyWriter.ExitFunction(this,"true");
				return true;
			}
		}
	}
}

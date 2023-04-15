package projlab.sivatag;

public class Saboteur extends Player {
	/**
	 * Létrehoz egy új szabotőr játékost, elhelyezve az adott csőhálózati elemen.
	 * @param position A csőhálózati elem, amire az új játékost kívánjuk helyezni.
	 */
	public Saboteur(WaterFlow position) {
		super(position);
	}
	
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Repair() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Repair()");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Break() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Repair()");
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		if (position.Break(false)) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
	}
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért null-t ad vissza.
	 * @return null.
	 */
	@Override
	public Movable InputCallback_Pickup(int neighbor) {
		System.out.print("Movable Saboteur.InputCallback_Pickup(int)");
		
		return null;
	}
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Place() {
		System.out.print("boolean Saboteur.InputCallback_Place()");
		
		return false;
	}

}

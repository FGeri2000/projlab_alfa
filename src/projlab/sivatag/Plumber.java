package projlab.sivatag;

/**
 * A szerelő csapat egy játékosa. Képes a csőhálózat javítására és módosítására.
 * @author fgreg
 *
 */
public class Plumber extends Player {
	/**
	 * Létrehoz egy új szerelő játékost, elhelyezve az adott csőhálózati elemen.
	 * @param position A csőhálózati elem, amire az új játékost kívánjuk helyezni.
	 */
	public Plumber(WaterFlow position) {
		super(position);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Repair() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Repair()");
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		if (position.Repair()) {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "true");
			return true;
		}
		else {
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "false");
			return false;
		}
	}
	/**
	 * @implNote Szerelő játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Break() {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Break");
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "boolean");
		return false;
	}
	/**
	 * 
	 */
	@Override
	public WaterFlow InputCallback_Pickup(int neighbor) {
//		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Pickup");
//		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "waterFlow");
		return null;
	}
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Place() {
//		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Place");
//		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "boolean");
		return false;
	}

}

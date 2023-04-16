package projlab.sivatag;

import java.util.LinkedList;

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
	 * Játékosi bemenetre meghívódik, és felveszi a tartózkodási helyéhez csatlakozó, az adott index-el meghatározott elemét.
	 * @param neighbor A tartózkodási elemre szomszédaira érvényes index, ami a felvevendő elemre mutat.
	 * @return Az elem, ha a felvétel sikeres, null, ha sikertelen.
	 */
	@Override
	public WaterFlow InputCallback_Pickup(int neighbor) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Pickup(" + neighbor + ")");
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		LinkedList<WaterFlow> neighborslist = position.GetNeighbors();

		if(neighborslist.size() <= neighbor){
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "null");
			return null;
		}

		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		if(neighborslist.get(neighbor).PickUp(position)){
			heldObject = neighborslist.get(neighbor);
			projlab.skeleton.CallHierarchyWriter.ExitFunction(this, projlab.skeleton.CallHierarchyWriter.GetIdentifier(neighborslist.get(neighbor)));
			return neighborslist.get(neighbor);
		}
		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, "null");
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

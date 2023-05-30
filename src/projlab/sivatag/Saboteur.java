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
		return false;
	}
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért null-t ad vissza.
	 * @return null.
	 */
	@Override
	public WaterFlow InputCallback_Pickup(int neighbor) {		
		return null;
	}
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Place() {
		return false;
	}

}

package projlab.sivatag;

/**
 * A szerelő csapat egy játékosa. Képes a csőhálózat javítására és módosítására.
 * @author fgreg
 *
 */
public class Plumber extends Player {
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Repair() {
		System.out.print("boolean Plumber.InputCallback_Repair()");
		/*System.out.println("\tPlumber >> WaterFlow\t| Repair()");
		position.Repair();*/
		
		return false;
	}
	/**
	 * @implNote Szerelő játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Break() {
		System.out.print("boolean Plumber.InputCallback_Break()");
		
		return false;
	}
	/**
	 * 
	 */
	@Override
	public WaterFlow InputCallback_Pickup(int neighbor) {
		System.out.print("WaterFlow Plumber.InputCallback_Pickup(int)");
		
		return null;
	}
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Place() {
		System.out.print("boolean Plumber.InputCallback_Place()");
		
		return false;
	}

}

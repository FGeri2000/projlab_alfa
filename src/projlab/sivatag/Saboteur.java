package projlab.sivatag;

public class Saboteur extends Player {
	/**
	 * @implNote Szabotőr játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_Repair() {
		System.out.print("boolean Saboteur.InputCallback_Repair()");
		
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean InputCallback_Break() {
		System.out.print("boolean Saboteur.InputCallback_Break()");
		
		return false;
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

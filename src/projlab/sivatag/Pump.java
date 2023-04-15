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
		System.out.print("boolean Pump.Repair()");
		
		return false;
	}
	/**
	 * Meghívódik, ha az adott pumpát tönkreteszik.<br>
	 * Csak akkor teszi tönkre a pumpát, ha a paraméter igaz.
	 */
	@Override
	public boolean Break(boolean controller) {
		System.out.print("boolean Pump.Break(boolean)");
		
		return false;
	}
	/**
	 * Ha nincs tönkremenve a pumpa, áthelyezi a benne található vizet a kimeneti elemébe.
	 */
	@Override
	public void FlowTick() {
		System.out.print("boolean Pump.FlowTick()");
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

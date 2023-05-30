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
	public Plumber(WaterFlow position)
	{
		super(position);
	}
	
	/**
	 * Játékosi bemenetre meghívódik, és megjavítja a tartózkodási helyének megfelelő elemet, amennyiben az engedi.
	 * @return Igaz, ha a javítás sikeres, hamis ha sikertelen vagy az elem nem törött.
	 */
	@Override
	public boolean InputCallback_Repair() {		
		return position.repairObject();
	}
	/**
	 * Játékosi bemenetre meghívódik, és felveszi a tartózkodási helyéhez csatlakozó, az adott index-el meghatározott elemét.
	 * @param neighbor A tartózkodási elemre szomszédaira érvényes index, ami a felvevendő elemre mutat.
	 * @return Az elem, ha a felvétel sikeres, null, ha sikertelen.
	 */
	@Override
	public WaterFlow InputCallback_Pickup(int neighbor) {
		if (heldObject != null)
			return null;
		
		LinkedList<WaterFlow> neighborslist = position.getNeighbors();

		if(neighborslist.size() <= neighbor)
		{
			return null;
		}
		
		if(neighborslist.get(neighbor).pickUp(position))
		{
			return neighborslist.get(neighbor);
		}
		
		return null;
	}
	/**
	 * Játékosi bemenetre meghívódik, és a játékos által hordozott elemet lehelyezi ahhoz az elemhez, ahol a játékos tartózkodik.
	 * @return Igaz, ha a lehelyezés sikerült
	 */
	@Override
	public boolean InputCallback_Place() {
		if (heldObject == null)
		{
			return false;
		}

		boolean success;
		LinkedList<WaterFlow> neighborsList = position.getNeighbors();
		
		if (neighborsList.size() == 2)
		{
			position.removeNeighbors();
			Pipe newPipe1 = new Pipe();
			Pipe newPipe2 = new Pipe();
			
			success = neighborsList.get(0).addNeighbor(newPipe1);
			success &= neighborsList.get(1).addNeighbor(newPipe2);
			success &= heldObject.putDown(newPipe1);
			success &= newPipe2.addNeighbor(heldObject);
			success &= heldObject.putPlayer(this) != null;
			
		}
		else
		{
			success = heldObject.putDown(position);
		}
		
		if (success) {
			heldObject = null;
		}

		return success;
	}
	/**
	 * @implNote Szerelő játékosok esetén érvénytelen, ezért hamisat ad vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean InputCallback_MakePipeSlippery()
	{
		return false;
	}

}

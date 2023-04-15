package projlab.sivatag;

/**
 * Ősosztály a játékosok karaktereinek, amely tárolja a tartózkodási helyét és lehetővé teszi a játékosi bemenetek fogadását.
 * @author fgreg
 *
 */
public abstract class Player {
	/**
	 * Létrehoz egy új játékost, elhelyezve az adott csőhálózati elemen.
	 * @param position A csőhálózati elem, amire az új játékost kívánjuk helyezni.
	 */
	public Player(WaterFlow position) {
		this.position = position;
	}
	
	/**
	 * A csőhálózati elem, amin a karakter jelenleg tartózkodik.
	 */
	protected WaterFlow position = null;
	/**
	 * A csőhálózat azon eleme, amelyet a játékos jelenleg mozgat.
	 */
	protected WaterFlow heldObject = null;
	
	/**
	 * Játékosi bemenetre meghívódik, és a karakter (amennyiben lehetséges) áthelyeződik a tartózkodási helyének a `neighbor` index-el meghatározott szomszédjára.
	 * @param neighbor A jelenlegi tartózkodási elem szomszédaira érvényes index, ami meghatározza a játékos új kívánt pozícióját.
	 * @return Az új pozíció, ha az áthelyezés sikeres, a régi pozíció, ha sikertelen.
	 */
	public PlayerMovement InputCallback_Move(int neighbor) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_Move(" + neighbor + ")");
		
		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		PlayerMovement pm = position.MovePlayer(this, neighbor);

		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, projlab.skeleton.CallHierarchyWriter.GetIdentifier(pm));
		return pm;
	}
	/**
	 * Játékosi bemenetre meghívódik, és a tartózkodási helyén (amennyiben az pumpa) beállítja a kimenei csövet az index által meghatározottra.
	 * @param inPipe A szomszédos elem indexe, amit a pumpa bemenetének kívánunk beállítani.
	 * @return Igaz, ha a beállítás sikeres volt, hamis, ha a valamilyen hiba folytán a beállítás változatlan maradt.
	 */
	public boolean InputCallback_SetInput(int inPipe) {
		projlab.skeleton.CallHierarchyWriter.EnterFunction(this, "InputCallback_SetInput(" + inPipe + ")");

		projlab.skeleton.CallHierarchyWriter.PushCaller(this);
		boolean success = position.SetInput(new int[] { inPipe });

		projlab.skeleton.CallHierarchyWriter.ExitFunction(this, success ? "true" : "false");
		return success;
	}
	/**
	 * Játékosi bemenetre meghívódik, és a tartózkodási helyén (amennyiben az pumpa) beállítja a kimenei csövet az index által meghatározottra.
	 * @param outPipe A szomszédos elem indexe, amit a pumpa kimenetének kívánunk beállítani.  
	 * @return Igaz, ha a beállítás sikeres volt, hamis, ha a valamilyen hiba folytán a beállítás változatlan maradt.
	 */
	public boolean InputCallback_SetOutput(int outPipe) {
		System.out.print("boolean Player.InputCallback_SetOutput(int)");
		
		return false;
	}
	
	/**
	 * Játékosi bemenetre meghívódik, és megjavítja a tartózkodási helyének megfelelő elemet, amennyiben az engedi és a játékos tud szerelni.
	 * @return Igaz, ha a javítás sikeres, hamis ha sikertelen vagy az elem nem törött.
	 */
	public abstract boolean InputCallback_Repair();

	/**
	 * Játékosi bemenetre meghívódik, és tönkreteszi a tartózkodási helyének megfelelő elemet, amennyiben az engedi és a játékos tud tönkretenni.
	 * @return Igaz, ha a tönkretétel sikeres, hamis ha sikertelen vagy az elem már törött.
	 */
	public abstract boolean InputCallback_Break();
	/**
	 * Játékosi bemenetre meghívódik, és felveszi a tartózkodási helyéhez csatlakozó, az adott index-el meghatározott elemét.
	 * @param neighbor A tartózkodási elemre szomszédaira érvényes index, ami a felvevendő elemre mutat.
	 * @return Az elem, ha a felvétel sikeres, null, ha sikertelen.
	 */
	public abstract Movable InputCallback_Pickup(int neighbor);
	/**
	 * Játékosi bemenetre meghívódik, lehelyezi és hozzáilleszti a tartózkodási helyéhez a hordott elemet.
	 * @return Igaz, ha a tárgy el lett helyezve, hamis, ha a játékos nem fog semmit, vagy a tárgy nem lett elhelyezve és a játékos még mindig fogja.
	 */
	public abstract boolean InputCallback_Place();
}
